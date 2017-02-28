package com.wallacomic.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.repository.ComicRepository;

@RestController
public class ComicController {
	
	@Autowired
	private ComicRepository comicRepository;
	
	@PostConstruct
	public void init(){
		//creamos comic, anuncios de compra y venta y los asociamos al comic
		Comic c1 = new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1");
		c1.getAnunciosCompra().add(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable"));
		c1.getAnunciosVenta().add(new Anuncio(false, 5.0, "Comic hecho polvo que te sirve como alfombrilla de raton"));
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

	}
	
	@RequestMapping("/home")
	@ResponseBody
	public ModelAndView home(Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("home");
	    
		List<Comic> comics= comicRepository.findAll();
		mav.addObject("comics", comics);
	    return mav;
	}
	
	//Este método según los apuntes deberia devolver el valor de la lista comics junto con 
	//la vista home, pero en vez de eso devuelve y renderiza el string "home", y no se porq
	// hay q preguntar en clase.
	@RequestMapping("/home2")
	@ResponseBody
	public String home2(Model model) throws Exception {
	    
		List<Comic> comics= comicRepository.findAll();
		model.addAttribute("comics",comics);
	    return "home";
	}

	@RequestMapping("/comic/{id}")
	@ResponseBody
	public ModelAndView comic(Model model, @PathVariable int id) throws Exception {
		
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("comic");
	    
		Comic comic= comicRepository.findById(id);
		mav.addObject("comic", comic);
	    return mav;
	}
}
