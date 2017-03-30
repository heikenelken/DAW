package com.wallacomic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Conversacion;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.repository.AnuncioRepository;
import com.wallacomic.repository.ConversacionRepository;


@Service
public class ConversacionService {

	@Autowired
	UsuarioComponent usuarioComponent;
	
	@Autowired
	ConversacionRepository conversacionRepository;
	
	public List<Conversacion> findAll(){
		return conversacionRepository.findAll();
	}
	
	public List<Conversacion> findByUserBuyerOrUserSeller(Usuario user1, Usuario user2){
		return conversacionRepository.findByUserBuyerOrUserSeller(user1,user2);
	}
	
	public Conversacion findByUserSellerAndUserBuyer(Usuario user1, Usuario user2){
		return conversacionRepository.findByUserSellerAndUserBuyer(user1, user2);
	}
	
	public void save(Conversacion conv){
		conversacionRepository.save(conv);
	}
	
	public Conversacion findById(int id){
		return conversacionRepository.findById(id);
	}
	
}
