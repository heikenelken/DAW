package com.wallacomic.controller;

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
import com.wallacomic.repository.ConversacionRepository;
import com.wallacomic.repository.UsuarioRepository;

@Controller
public class MensajeController {
	
	@Autowired
	UsuarioComponent usuarioComponent;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ConversacionRepository conversacionRepository;
	
	/*@RequestMapping("/crearMensaje/{id}")
	public String conversacionConcreta(Model model, @PathVariable String id, @RequestParam String mensaje) throws Exception {
		
		Usuario user = usuarioComponent.getLoggedUser();
		model.addAttribute("user", user);
		int idcasted = Integer.parseInt(id);
		Conversacion conversacion = conversacionRepository.findById(idcasted);
		conversacion.getComentarios().add(mensaje);
		
		conversacionRepository.save(conversacion); //hay que sobreescribir
		model.addAttribute("conversacion", conversacion);
		List<Conversacion> conversaciones = conversacionRepository.findByUserBuyerOrUserSeller(user, user);
		model.addAttribute("conversaciones", conversaciones);
		
		return "conversacion_concreta";
	}*/
}
