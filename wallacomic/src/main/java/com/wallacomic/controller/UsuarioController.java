package com.wallacomic.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;
import com.wallacomic.repository.ComicRepository;
import com.wallacomic.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostConstruct
	public void init(){
		usuarioRepository.save(new Usuario("AdoptaUnAlien","123456", "Pequeña descripción sin sentido contando lo chupiguay que soy.", "adoptaunalien@gmail.com", "facebook/adoptaunalien", "@adoptaunalien","1"));
		usuarioRepository.save(new Usuario("PdrSnchz","123456", "Vendo Opel Corsa en perfecto estado", "adoptaunpdrsnchz@gmail.com", "facebook/pdrsnchz", "@pdrsnchz","2"));
	}
	
	@RequestMapping("/usuario/{id}")
	public String usuario(Model model, @PathVariable int id) throws Exception {
		
		Usuario usuario= usuarioRepository.findById(id);
		model.addAttribute("usuario", usuario);
	    return "usuario";
	}
	
	@RequestMapping("/guardarUsuario")
	public String guardarUsuario(Model model, @RequestParam String nombre, @RequestParam String contraseña,
			@RequestParam String correo)throws Exception{
		
		Usuario usuario = new Usuario(nombre, contraseña, "", correo, "", "", "");
		
		usuarioRepository.save(usuario);
		
	    return "usuario_guardado";
	}
	
	@RequestMapping("/usuarios")
	@ResponseBody
	public List<Usuario> usuario() throws Exception {
		
		List<Usuario> usuarios= usuarioRepository.findAll();
		
	    return usuarios;
	}
	
}
