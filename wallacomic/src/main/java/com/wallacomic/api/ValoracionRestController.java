package com.wallacomic.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.service.ValoracionService;

@RestController
@RequestMapping("/api")
public class ValoracionRestController {

	@Autowired
	private ValoracionService valoracionService;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
	@RequestMapping(value = "/valoracion/{id}", method = RequestMethod.GET)
	public Valoracion valoracion(@PathVariable int id) throws Exception {
	    
		Valoracion valoracion= valoracionService.findById(id);
	    return valoracion;
	}
	//falta obtener valoración de un usuario
	@RequestMapping(value = "/valoracion/usuario/{id}", method = RequestMethod.GET)
	public Collection<Valoracion> getValoracionesByUser(@PathVariable int id) throws Exception {
	    
	    return valoracionService.findByUser(Long.valueOf(id));
	}
	
	@RequestMapping(value = "/valoracion/usuario/{id}/media", method = RequestMethod.GET)
	public int getValoracionMediaUsuario(@PathVariable int id) throws Exception {
	    int media = 0;
	    Collection<Valoracion> valoracionesU = valoracionService.findByUser(Long.valueOf(id));
	    for(Valoracion v: valoracionesU){
	    	media += v.getNumEstrellas();
	    }
	    if(valoracionesU.size() == 0){
	    	return 0;
	    }else{
	    	return (int) media / valoracionesU.size();
	    }
	}
	
	@RequestMapping(value = "/valoracion/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Valoracion guardarValoracion(@RequestBody Valoracion val)throws Exception{
		
		valoracionService.save(val);
	    return val;
	}
	
	@RequestMapping(value = "/valoraciones", method = RequestMethod.GET)
	//@ResponseBody
	public Collection<Valoracion> valoraciones() throws Exception {
		
		Collection<Valoracion> valoraciones= valoracionService.findAll();
	    return valoraciones;
	}
	
}
