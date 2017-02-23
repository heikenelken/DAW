package com.wallacomic.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wallacomic.domain.Comic;
import com.wallacomic.repository.ComicRepository;

@RestController
public class ComicController {
	
	@Autowired
	private ComicRepository comicRepository;
	
	@PostConstruct
	public void init(){
		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "img/1.jpg"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "img/2.jpg"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "img/3.jpg"));
		comicRepository.save(new Comic("Batman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "img/4.jpg"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "img/5.jpg"));
		comicRepository.save(new Comic("IronMan #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "img/6.jpg"));
		comicRepository.save(new Comic("Barrier #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "img/7.jpg"));

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

}
