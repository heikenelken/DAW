package com.wallacomic.controller;

import javax.annotation.PostConstruct;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import com.wallacomic.repository.AnuncioRepository;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.repository.UsuarioRepository;
import com.wallacomic.service.AnuncioService;
import com.wallacomic.repository.ComicRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnuncioController {

	/*@Autowired
	private AnuncioRepository anuncioRepository;*/
	@Autowired
	private AnuncioService anuncioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioComponent usuarioComponent;
	@Autowired
	private ComicRepository comicRepository;
	
	//cargar en ComicController /comic/{id} los anuncios asociados, al igual que en
	// /usuario/{id} y en la interfaz perfil ¿con /usuario/{id}?
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
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
		
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
			//anuncioRepository.save(anuncio);
			anuncioService.save(anuncio);
			model.addAttribute("user",user);
			
		}else{
			throw new BadCredentialsException("Error de creacion");
		}
		
		return "anuncio_guardado";
	}
	@RequestMapping("/borrarAnuncio/{id}")
	public String borrarAnuncio(Model model, @PathVariable long id)throws Exception{
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			//if (anuncioRepository.exists(id)) {
			if (anuncioService.existAd(id)) {
				//anuncioRepository.delete(id);
				anuncioService.delete(id);
			}
			Usuario user = usuarioComponent.getLoggedUser();
			model.addAttribute("user",user);
		}else{
			throw new BadCredentialsException("Error de creacion");
		}
		
		return "anuncio_borrado";
	}
	
}
