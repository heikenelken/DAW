package com.wallacomic.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.wallacomic.repository.AnuncioRepository;

@RestController
public class AnuncioController {

	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@PostConstruct
	public void init(){
		
	}
	//cargar en ComicController /comic/{id} los anuncios asociados, al igual que en
	// /usuario/{id} y en la interfaz perfil ¿con /usuario/{id}?
}
