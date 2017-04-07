package com.wallacomic.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Conversacion;
import com.wallacomic.domain.Mensaje;
import com.wallacomic.service.UsuarioService;
import com.wallacomic.service.ConversacionService;

@Controller
public class ConversacionController {
	
	@Autowired
	UsuarioComponent usuarioComponent;
	
	@Autowired
	UsuarioService usuarioService;
	
	
	@Autowired
	ConversacionService conversacionService;
	
	@RequestMapping("/conversacion")
	public String conversacion(Model model) throws Exception {
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			
			List<Conversacion> conversaciones = conversacionService.getMyConversations();
			Usuario user = usuarioComponent.getLoggedUser();
			model.addAttribute("user", user);
			model.addAttribute("conversaciones", conversaciones);
			
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
		
	    return "conversacion";
	}
	
	@RequestMapping("/conversacion/{id}")
	public String conversacionConcreta(Model model, @PathVariable String id) throws Exception {
		
		int idcasted = Integer.parseInt(id);
		Conversacion conversacion = conversacionService.findById(idcasted);
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions() && 
				(conversacion.getUserBuyer().getId() == usuarioComponent.getLoggedUser().getId() || 
						conversacion.getUserSeller().getId() == usuarioComponent.getLoggedUser().getId())){
			
			Usuario user = usuarioComponent.getLoggedUser();
			model.addAttribute("user", user);
			model.addAttribute("conversacion", conversacion);
			List<Conversacion> conversaciones = conversacionService.getMyConversations();
			model.addAttribute("conversaciones", conversaciones);
			
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
		
		return "conversacion_concreta";
	}
	
	@RequestMapping("/crearMensaje/{id}")
	public String conversacionConcreta(Model model, @PathVariable String id, @RequestParam String mensaje) throws Exception {
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
		
			Usuario user = usuarioComponent.getLoggedUser();
			model.addAttribute("user", user);
			int idcasted = Integer.parseInt(id);
			Conversacion conversacion = conversacionService.findById(idcasted);
			conversacion.getComentarios().add(new Mensaje(user, mensaje));
			conversacionService.save(conversacion); //hay que sobreescribir
			model.addAttribute("conversacion", conversacion);
			List<Conversacion> conversaciones = conversacionService.getMyConversations();
			model.addAttribute("conversaciones", conversaciones);
			
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
		
		return "conversacion_concreta";
	}
	
	@RequestMapping("/contactar/{id}")
	public String contactar(Model model, @PathVariable long id) throws Exception {
		
		if((usuarioComponent.isLoggedUser()) && usuarioComponent.hasAdminPermissions()){
			Usuario userMe = usuarioComponent.getLoggedUser();
			model.addAttribute("user", userMe);
			Conversacion converWithSeller = conversacionService.findConversationWithAnUser((int)id);
			if(converWithSeller != null){//hay conversacion previa
				model.addAttribute("conversaciones", conversacionService.getMyConversations());
				model.addAttribute("conversacion", converWithSeller);
			}else{//nueva conversacion
				Conversacion newConver = new Conversacion(userMe, usuarioService.findById(id), Arrays.asList());
				conversacionService.save(newConver);
				model.addAttribute("conversaciones", conversacionService.getMyConversations());
				model.addAttribute("conversacion", newConver);
			}
			
		}else{
			
			throw new BadCredentialsException("Error de acceso: no puedes acceder a una conversación contigo mismo");
		}
		
		return "conversacion_concreta";
	}
	
}
