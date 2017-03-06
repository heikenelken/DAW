package com.wallacomic.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;
import com.wallacomic.repository.AnuncioRepository;
import com.wallacomic.repository.ComicRepository;
import com.wallacomic.repository.UsuarioRepository;

@Controller
public class ComicController {
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@PostConstruct
	public void init(){
		//creamos una serie de anuncios con usuarios
		Usuario u1 = new Usuario("AdoptaUnAlien","123456", "Pequeña descripción sin sentido contando lo chupiguay que soy.", "adoptaunalien@gmail.com", "facebook/adoptaunalien", "@adoptaunalien","1");
		Usuario u2 = new Usuario("PdrSnchz","123456", "Vendo Opel Corsa en perfecto estado", "adoptaunpdrsnchz@gmail.com", "facebook/pdrsnchz", "@pdrsnchz","2");

		Anuncio a1 = new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable");
		Anuncio a2 = new Anuncio(false, 5.0, "Comic hecho polvo que te sirve como alfombrilla de raton");
		
		a1.setUser(u1);
		a2.setUser(u2);
		
		usuarioRepository.save(u1);
		usuarioRepository.save(u2);
		/*
		u1.getAnuncios().add(a1);
		u2.getAnuncios().add(a2);
		
		
		
		anuncioRepository.save(a1);
		anuncioRepository.save(a2);*/
		
		//creamos comic, anuncios de compra y venta y los asociamos al comic

		Comic c1 = new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1");
		c1.getAnunciosCompra().add(a1);
		c1.getAnunciosVenta().add(a2);
		//guardamos comic con anuncios
		comicRepository.save(c1);
		//comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1"));
		//guardamos comic sin anuncios
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "2"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "3"));
		comicRepository.save(new Comic("Batman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "4"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("IronMan #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "6"));
		comicRepository.save(new Comic("Barrier #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "7"));

		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		
	}
	
	@RequestMapping("/home")
	public String home(Model model) throws Exception {
		
		/*List<Comic> comics= comicRepository.findAll();
		model.addAttribute("comics", comics);*/
		//comics paginados
		Page<Comic> comPaginados = comicRepository.findAll(new PageRequest(0,10));
		model.addAttribute("comPag", comPaginados);
		boolean numeroComics = (10 < comicRepository.findAll().size());
		model.addAttribute("numComics", numeroComics);
		
	    return "home";
	}
	
	//Este método según los apuntes deberia devolver el valor de la lista comics junto con 
	//la vista home, pero en vez de eso devuelve y renderiza el string "home", y no se porq
	// hay q preguntar en clase. SOLUCIONADO: no hay que usar las anotaciones @RestController
	//ni @ResponseBody
	@RequestMapping("/home2")
	public String home2(Model model) throws Exception {
	    
		List<Comic> comics= comicRepository.findAll();
		model.addAttribute("comics",comics);
	    return "home";
	}

	@RequestMapping("/comic/{id}")
	public String comic(Model model, @PathVariable int id) throws Exception {
		
		Comic comic= comicRepository.findById(id);
		model.addAttribute("comic", comic);
	    return "comic";
	}
}
