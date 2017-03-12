package com.wallacomic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.repository.ComicRepository;

@RestController
public class RestRequestsController {

	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
	private List<Comic> totalComics = new ArrayList<Comic>();
	
	@RequestMapping(value="/home/more", method=RequestMethod.GET)
	@ResponseBody
	public List<Comic> home(Pageable page) throws Exception {
		
		ModelAndView mV = new ModelAndView("home");
		
		List<Comic> comEven = new ArrayList<Comic>();
		List<Comic> comOdd = new ArrayList<Comic>();
		boolean numeroComics;
	
		//resto de paginas 
		List<Comic> listPrev = comicRepository.findAll(page).getContent();
		for(Comic c: listPrev){
			
			totalComics.add(c);
		}
		
		numeroComics = (comicRepository.findAll(page).hasNext());
		
		for(Comic com: totalComics){
			if(com.getId() % 2 == 0){//par
				comEven.add(com);
			}else{
				comOdd.add(com);
			}
		}
	
		mV.addObject("comEven", comEven);
		mV.addObject("comOdd", comOdd);
		mV.addObject("numComics", numeroComics);
		
		int nextPage = page.getPageNumber() + 1;
		mV.addObject("nextPage", nextPage);
		
		if(usuarioComponent.isLoggedUser()){
			Usuario user = usuarioComponent.getLoggedUser();
			mV.addObject("user", user);
			mV.setViewName("home_autenticado");
			return totalComics;
		}else{
			return totalComics;
		}
	}
}
