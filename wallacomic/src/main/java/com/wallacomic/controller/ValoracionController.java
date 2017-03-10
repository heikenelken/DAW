package com.wallacomic.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.repository.UsuarioRepository;
import com.wallacomic.repository.ValoracionRepository;

@Controller
public class ValoracionController {

	@Autowired
	private ValoracionRepository valoracionRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostConstruct
	public void init(){
	}
	
	@RequestMapping("/valoracion/{id}")
	public String valoracion(Model model, @PathVariable int id) throws Exception {
	    
		Valoracion valoracion= valoracionRepository.findById(id);
		model.addAttribute("usuario", valoracion);
	    return "usuario";
	}
	
	@RequestMapping("/guardarValoracion")
	public String guardarValoracion(Model model, @RequestParam Usuario user_give, @RequestParam Usuario user_receive, @RequestParam String comentario,
			@RequestParam int numEstrellas)throws Exception{
		
		// TO-DO: Comprobar que user_give != user_receive antes de crear y guardar
		Valoracion valoracion = new Valoracion(user_give, user_receive, comentario, numEstrellas);
		
		valoracionRepository.save(valoracion);
		
	    return "usuario_guardado";
	}
	@RequestMapping("/valoraciones")
	@ResponseBody
	public List<Valoracion> valoracion() throws Exception {
		
		List<Valoracion> valoraciones= valoracionRepository.findAll();
	    return valoraciones;
	}
	
}
