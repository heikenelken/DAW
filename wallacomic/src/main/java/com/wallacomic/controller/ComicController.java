package com.wallacomic.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
    private List<Comic> totalComics = new ArrayList<Comic>();
    
	@PostConstruct
	public void init(){
		List<Comic> comFirst = comicRepository.findAll(new PageRequest(0,10)).getContent();
		for(Comic com: comFirst){
			totalComics.add(com);
		}
	}
	
	@RequestMapping("/home")
	public String home(Model model, Pageable page) throws Exception {
		List<Comic> comEven = new ArrayList<Comic>();
		List<Comic> comOdd = new ArrayList<Comic>();
		//List<Comic> comPrimeros = comicRepository.findAll(new PageRequest(0,10)).getContent();//extraemos los primeros y ponemos flag a true para mostrar mas resultados
		
		//model.addAttribute("comPag", comPrimeros);
		boolean numeroComics = true;
		
		if(page.hasPrevious()){
			
			List<Comic> listPrev = comicRepository.findAll(page).getContent();
			for(Comic c: listPrev){
				
				totalComics.add(c);
			}
			
			numeroComics = (comicRepository.findAll(page).hasNext());
		}
		for(Comic com: totalComics){
			if(com.getId() % 2 == 0){//par
				comEven.add(com);
			}else{
				comOdd.add(com);
			}
		}
		model.addAttribute("comEven", comEven);
		model.addAttribute("comOdd", comOdd);
		model.addAttribute("numComics", numeroComics);
		
		int nextPage = page.getPageNumber() + 1;
		model.addAttribute("nextPage", nextPage);
		
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
