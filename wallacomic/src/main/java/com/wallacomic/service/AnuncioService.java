package com.wallacomic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.repository.AnuncioRepository;


@Service
public class AnuncioService {
	
	@Autowired
	UsuarioComponent usuarioComponent;

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
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			anuncioRepository.save(ad);
		}else{
			throw new BadCredentialsException("Error de creacion");
		}
		
	}
	
	public Anuncio findById(long id){
		return anuncioRepository.findOne(id);
	}
	
	public void delete(long id){
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			anuncioRepository.delete(id);
		}else{
			throw new BadCredentialsException("Error de creacion");
		}
		
	}
	
	public boolean existAd(long id){
		return anuncioRepository.exists(id);
	}
	
	
}
