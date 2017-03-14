package com.wallacomic.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.domain.Conversacion;
import com.wallacomic.domain.Mensaje;
import com.wallacomic.repository.ConversacionRepository;
import com.wallacomic.repository.UsuarioRepository;

@Controller
public class ConversacionController {
	
	@Autowired
	UsuarioComponent usuarioComponent;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ConversacionRepository conversacionRepository;
	
	@RequestMapping("/conversacion")
	public String conversacion(Model model) throws Exception {
	    
		Usuario user = usuarioComponent.getLoggedUser();
		List<Conversacion> conversaciones = conversacionRepository.findByUserBuyerOrUserSeller(user, user);
		model.addAttribute("user", user);
		model.addAttribute("conversaciones", conversaciones);
	    return "conversacion";
	}
	
	@RequestMapping("/conversacion/{id}")
	public String conversacionConcreta(Model model, @PathVariable String id) throws Exception {
		
		Usuario user = usuarioComponent.getLoggedUser();
		model.addAttribute("user", user);
		int idcasted = Integer.parseInt(id);
		Conversacion conversacion = conversacionRepository.findById(idcasted);
		model.addAttribute("conversacion", conversacion);
		List<Conversacion> conversaciones = conversacionRepository.findByUserBuyerOrUserSeller(user, user);
		model.addAttribute("conversaciones", conversaciones);
		
		return "conversacion_concreta";
	}
	
	@RequestMapping("/crearMensaje/{id}")
	public String conversacionConcreta(Model model, @PathVariable String id, @RequestParam String mensaje) throws Exception {
		
		Usuario user = usuarioComponent.getLoggedUser();
		model.addAttribute("user", user);
		int idcasted = Integer.parseInt(id);
		Conversacion conversacion = conversacionRepository.findById(idcasted);
		conversacion.getComentarios().add(new Mensaje(user, mensaje));
		
		conversacionRepository.save(conversacion); //hay que sobreescribir
		model.addAttribute("conversacion", conversacion);
		List<Conversacion> conversaciones = conversacionRepository.findByUserBuyerOrUserSeller(user, user);
		model.addAttribute("conversaciones", conversaciones);
		
		return "conversacion_concreta";
	}
	
	@RequestMapping("/contactar/{id}")
	public String contactar(Model model, @PathVariable long id) throws Exception {
		
		if((usuarioComponent.isLoggedUser()) && (usuarioComponent.getLoggedUser().getId() != usuarioRepository.findById(id).getId())){
			
			Usuario userMe = usuarioComponent.getLoggedUser();
			model.addAttribute("user", userMe);
			Conversacion converWithSeller = conversacionRepository.findByUserSellerAndUserBuyer(usuarioRepository.findById(id), userMe);
			Conversacion converWithSeller2 = conversacionRepository.findByUserSellerAndUserBuyer(userMe, usuarioRepository.findById(id));
			if(converWithSeller != null){
				//hay conversacion previa	
				model.addAttribute("conversaciones", conversacionRepository.findByUserBuyerOrUserSeller(userMe, userMe));
				model.addAttribute("conversacion", converWithSeller);

			}else if(converWithSeller2 != null){//nueva conversacion
				
				model.addAttribute("conversaciones", conversacionRepository.findByUserBuyerOrUserSeller(userMe, userMe));
				model.addAttribute("conversacion", converWithSeller2);
					
			}else{
				
				Conversacion newConver = new Conversacion(userMe, usuarioRepository.findById(id), Arrays.asList());
				conversacionRepository.save(newConver);
				model.addAttribute("conversaciones", conversacionRepository.findByUserBuyerOrUserSeller(userMe, userMe));
				model.addAttribute("conversacion", newConver);
			
			}
			
		}else{
			
			throw new BadCredentialsException("Error de acceso: no puedes acceder a una conversación contigo mismo tontopollas!!");
		}
		
		return "conversacion_concreta";
	}
	
}
