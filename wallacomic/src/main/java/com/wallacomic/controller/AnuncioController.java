package com.wallacomic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.service.AnuncioService;
import com.wallacomic.repository.ComicRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnuncioController {

	@Autowired
	private AnuncioService anuncioService;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
	@Autowired
	private ComicRepository comicRepository;
	
	@RequestMapping("/crearAnuncio")
	public String crearAnuncio(Model model)throws Exception{
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
		
			Usuario user = usuarioComponent.getLoggedUser();
			model.addAttribute("user",user);
			List<Comic> comics = comicRepository.findAll();
			model.addAttribute("comics",comics);
			
		}else{
			throw new BadCredentialsException("Error de creacion");
		}
		
	    return "crearAnuncio";
	}
	
	@RequestMapping("/guardarAnuncio")
	public String guardarAnuncio(Model model, @RequestParam String comic, @RequestParam String tipo,
			@RequestParam double precio, @RequestParam String observaciones)throws Exception{
		
		Usuario user = usuarioComponent.getLoggedUser();
		int idComic=Integer.parseInt(comic);
		Comic com = comicRepository.findById(idComic);
		boolean tipoAnuncio;
		if (tipo.equals("true")){
			tipoAnuncio=true;
		}else{
			tipoAnuncio=false;
		}
		Anuncio anuncio = new Anuncio(tipoAnuncio, precio, observaciones, user, com);
		anuncioService.save(anuncio);
		model.addAttribute("user",user);
		
		return "anuncio_guardado";
	}
	
	@RequestMapping("/borrarAnuncio/{id}")
	public String borrarAnuncio(Model model, @PathVariable long id)throws Exception{
		
		if (anuncioService.existAd(id)) {
			
			anuncioService.delete(id);
		}
		
		Usuario user = usuarioComponent.getLoggedUser();
		model.addAttribute("user",user);
		
		
		return "anuncio_borrado";
	}
	
}
