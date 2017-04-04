package com.wallacomic.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.service.ValoracionService;

@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionRestController {

	@Autowired
	private ValoracionService valoracionService;
	
	//@Autowired
	//private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
	@RequestMapping(value = "/valoracion/{id}", method = RequestMethod.GET)
	public String valoracion(Model model, @PathVariable int id) throws Exception {
	    
		Valoracion valoracion= valoracionService.findById(id);
		model.addAttribute("usuario", valoracion);
	    return "usuario";
	}
	
	@RequestMapping(value = "/guardarValoracion", method = RequestMethod.POST)
	public String guardarValoracion(@RequestParam String comentario,
			@RequestParam int numEstrellas, @RequestParam long user_receive_id)throws Exception{
		
		valoracionService.guardarValoracion(user_receive_id, comentario, numEstrellas);
		
	    return "comentario_guardado";
	}
	
	@RequestMapping(value = "/valoraciones", method = RequestMethod.GET)
	@ResponseBody
	public List<Valoracion> valoraciones() throws Exception {
		
		List<Valoracion> valoraciones= valoracionService.findAll();
	    return valoraciones;
	}
	
}
