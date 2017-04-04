package com.wallacomic.controller;

import java.util.List;

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
import com.wallacomic.service.ValoracionService;

@Controller
public class ValoracionController {

	@Autowired
	private ValoracionService valoracionService;
	
	//@Autowired
	//private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
	@RequestMapping("/valoracion/{id}")
	public String valoracion(Model model, @PathVariable int id) throws Exception {
	    
		Valoracion valoracion= valoracionService.findById(id);
		model.addAttribute("usuario", valoracion);
	    return "usuario";
	}
	
	@RequestMapping("/guardarValoracion")
	public String guardarValoracion(Model model, @RequestParam String comentario,
			@RequestParam int numEstrellas, @RequestParam long user_receive_id)throws Exception{
		
		valoracionService.guardarValoracion(user_receive_id, comentario, numEstrellas);
		
		model.addAttribute("id", user_receive_id);
		
	    return "comentario_guardado";
	}
	
	@RequestMapping("/valoraciones")
	@ResponseBody
	public List<Valoracion> valoraciones() throws Exception {
		
		List<Valoracion> valoraciones = valoracionService.findAll();
	    return valoraciones;
	}
	
}
