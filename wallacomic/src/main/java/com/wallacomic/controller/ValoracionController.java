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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.repository.UsuarioRepository;
import com.wallacomic.repository.ValoracionRepository;

@Controller
public class ValoracionController {

	@Autowired
	private ValoracionRepository valoracionRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
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
	public String guardarValoracion(Model model, @RequestParam String comentario,
			@RequestParam int numEstrellas, @RequestParam long user_receive_id)throws Exception{
		
		// TO-DO: Comprobar que user_give != user_receive antes de crear y guardar
		// del form solo se recoje valoracion  y comentario
		Usuario user_receive = usuarioRepository.findOne(user_receive_id);
		
		if((usuarioComponent.isLoggedUser()) && (user_receive != null)){
			Usuario user_give = usuarioComponent.getLoggedUser();
			
			if(user_give != user_receive){
				Valoracion valoracion = new Valoracion(user_give, user_receive, comentario, numEstrellas);
				valoracionRepository.save(valoracion);
			}
		}else{
			throw new BadCredentialsException("User not found");
		}
		
		model.addAttribute("id", user_receive_id);
		
	    return "comentario_guardado";
	}
	@RequestMapping("/valoraciones")
	@ResponseBody
	public List<Valoracion> valoracion() throws Exception {
		
		List<Valoracion> valoraciones= valoracionRepository.findAll();
	    return valoraciones;
	}
	
}
