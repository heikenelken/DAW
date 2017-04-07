package com.wallacomic.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.wallacomic.domain.Conversacion;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.repository.ConversacionRepository;
import com.wallacomic.repository.UsuarioRepository;


@Service
public class ConversacionService {

	@Autowired
	private UsuarioComponent usuarioComponent;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ConversacionRepository conversacionRepository;
	
	public List<Conversacion> findAll(){
		
		return conversacionRepository.findAll();
	}
	
	public Conversacion findByUserSellerAndUserBuyer(Usuario user1, Usuario user2){
		return conversacionRepository.findByUserSellerAndUserBuyer(user1, user2);
	}
	
	public Conversacion findConversationWithAnUser(int id){
		
		Conversacion converWithSeller = conversacionRepository.findByUserSellerAndUserBuyer(usuarioRepository.findById(id), usuarioComponent.getLoggedUser());
		Conversacion converWithSeller2 = conversacionRepository.findByUserSellerAndUserBuyer(usuarioComponent.getLoggedUser(), usuarioRepository.findById(id));
		if(converWithSeller != null){
			return converWithSeller;
		}else if(converWithSeller2 != null){
			return converWithSeller2;
		}else{
			return null;
		}
	}
	
	public void save(Conversacion conv){
		conversacionRepository.save(conv);
	}
	
	public Conversacion findById(int id){
		return conversacionRepository.findById((int)id);	
	}
	
	public List<Conversacion> getMyConversations(){
		
		Usuario user = usuarioComponent.getLoggedUser();
		List<Conversacion> conversaciones = conversacionRepository.findByUserBuyerOrUserSeller(user, user);
		return conversaciones;
		
	}
}
