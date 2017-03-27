package com.wallacomic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;
import com.wallacomic.repository.AnuncioRepository;


@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository anuncioRepository;
	
	public List<Anuncio> findAll(){
		return anuncioRepository.findAll();
	}
	
	public List<Anuncio> findByUserAndType(Usuario user, boolean type){
		return anuncioRepository.findByUserAndType(user, type);
	}
	
	public List<Anuncio> findByComicAndType(Comic comic, boolean type){
		return anuncioRepository.findByComicAndType(comic, type);
	}
	
	public void save(Anuncio ad){
		anuncioRepository.save(ad);
	}
	
	public Anuncio findById(long id){
		return anuncioRepository.findOne(id);
	}
	
	public void delete(long id){
		anuncioRepository.delete(id);
	}
	
	public boolean existAd(long id){
		return anuncioRepository.exists(id);
	}
	
}
