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

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.repository.AnuncioRepository;
import com.wallacomic.repository.ComicRepository;
import com.wallacomic.repository.UsuarioRepository;
import com.wallacomic.repository.ValoracionRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@Autowired
	private ValoracionRepository valoracionRepository;
	
	@PostConstruct
	public void init(){
		usuarioRepository.save(new Usuario("AdoptaUnAlien","123456", "Pequeña descripción sin sentido contando lo chupiguay que soy.", "adoptaunalien@gmail.com", "facebook/adoptaunalien", "@adoptaunalien","1","ROLE_USER"));
		usuarioRepository.save(new Usuario("PdrSnchz","123456", "Vendo Opel Corsa en perfecto estado", "adoptaunpdrsnchz@gmail.com", "facebook/pdrsnchz", "@pdrsnchz","2","ROLE_USER"));
		
		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "2"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "3"));
		comicRepository.save(new Comic("Batman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "4"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("IronMan #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "6"));
		comicRepository.save(new Comic("Barrier #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "7"));
		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "8"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "9"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "10"));
		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1"));
		
		
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(1)));
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(2), comicRepository.findById(2)));
		anuncioRepository.save(new Anuncio(false, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(2), comicRepository.findById(6)));
		anuncioRepository.save(new Anuncio(false, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(2), comicRepository.findById(4)));
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(3)));
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(5)));
		anuncioRepository.save(new Anuncio(false, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(1)));
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(7)));
		
		valoracionRepository.save(new Valoracion(usuarioRepository.findById(1), usuarioRepository.findById(2), "Comic muy bonito. Me ha gustado mucho.", 4));
		valoracionRepository.save(new Valoracion(usuarioRepository.findById(2), usuarioRepository.findById(1),  "Buen tío, fiable, comic en perfecto estado", 5));
	}
	
	@RequestMapping("/usuario/{id}")
	public String usuario(Model model, @PathVariable int id) throws Exception {
		//obtener anuncios de un determinado usuario
		Usuario usuario= usuarioRepository.findById(id);
		model.addAttribute("usuario", usuario);
		
		model.addAttribute("adsLoCompro", anuncioRepository.findByUserAndType(usuario,true));
		model.addAttribute("adsLoVendo", anuncioRepository.findByUserAndType(usuario,false));
		//cargar valoraciones del usuario
		model.addAttribute("comments", valoracionRepository.findByuserReceive(usuario));
		//extraer valoracion media del usuario
		int totalValuations = 0;
		int cont = 0;
		List<Valoracion> valuations = valoracionRepository.findByuserReceive(usuario);
		for(Valoracion v: valuations){
			totalValuations += v.getNumEstrellas();
			cont += 1;
		}
		int averageValuation = totalValuations / cont;
		
		String [] averageStars = new String[5];
		for(int i=0; i < averageStars.length; i++){
			if(averageValuation-1 >= i){
				averageStars[i] = "";
			}else{
				averageStars[i] = "-empty";
			}
		}
		
		model.addAttribute("avS1", averageStars[0]);
		model.addAttribute("avS2", averageStars[1]);
		model.addAttribute("avS3", averageStars[2]);
		model.addAttribute("avS4", averageStars[3]);
		model.addAttribute("avS5", averageStars[4]);
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.getLoggedUser().getId()==id){
			return "miUsuario";
		}else{
			if(usuarioComponent.isLoggedUser()){
				Usuario user = usuarioComponent.getLoggedUser();
				model.addAttribute("user", user);
				return "usuario_autenticado";
			}else{
				return "usuario";
			}
		}
	    
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
	/*@RequestMapping("/login")
    public String login() {
    	return "login";
    }*/
	@RequestMapping("/loginerror")
    public String loginerror() {
    	return "loginerror";
    }
	
}
