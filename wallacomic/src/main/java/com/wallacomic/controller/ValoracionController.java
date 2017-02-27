package com.wallacomic.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class ValoracionController {

	@Autowired
	private ValoracionRepository valoracionRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostConstruct
	public void init(){
		valoracionRepository.save(new Valoracion(usuarioRepository.findById(1), "Comic muy bonito. Me ha gustado mucho.", 4));
		valoracionRepository.save(new Valoracion(usuarioRepository.findById(2), "Buen tío, fiable, comic en perfecto estado", 5));
	}
	
	@RequestMapping("/valoracion/{id}")
	@ResponseBody
	public ModelAndView valoracion(Model model, @PathVariable int id) throws Exception {
		
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("usuario");
	    
		Valoracion valoracion= valoracionRepository.findById(id);
		mav.addObject("usuario", valoracion);
	    return mav;
	}
	
	@RequestMapping("/guardarValoracion")
	public ModelAndView guardarValoracion(Model model, @RequestParam Usuario usuario, @RequestParam String comentario,
			@RequestParam int numEstrellas)throws Exception{
		
		Valoracion valoracion = new Valoracion(usuario, comentario, numEstrellas);
		
		valoracionRepository.save(valoracion);
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("usuario_guardado");
	    return mav;
	}
	@RequestMapping("/valoraciones")
	@ResponseBody
	public List<Valoracion> valoracion() throws Exception {
		
		List<Valoracion> valoraciones= valoracionRepository.findAll();
	    return valoraciones;
	}
	
}
