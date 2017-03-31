package com.wallacomic.service;

import java.util.List;

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
	UsuarioComponent usuarioComponent;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ConversacionRepository conversacionRepository;
	
	public List<Conversacion> findAll(){
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			return conversacionRepository.findAll();
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
	}
	
	public Conversacion findByUserSellerAndUserBuyer(Usuario user1, Usuario user2){
		return conversacionRepository.findByUserSellerAndUserBuyer(user1, user2);
	}
	
	public Conversacion findConversationWithAnUser(int id){
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			Conversacion converWithSeller = conversacionRepository.findByUserSellerAndUserBuyer(usuarioRepository.findById(id), usuarioComponent.getLoggedUser());
			Conversacion converWithSeller2 = conversacionRepository.findByUserSellerAndUserBuyer(usuarioComponent.getLoggedUser(), usuarioRepository.findById(id));
			if(converWithSeller != null){
				return converWithSeller;
			}else if(converWithSeller2 != null){
				return converWithSeller2;
			}else{
				return null;
			}
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
	}
	
	public void save(Conversacion conv){
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			conversacionRepository.save(conv);
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
		
	}
	
	public Conversacion findById(int id){
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			return conversacionRepository.findById(id);
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
			
	}
	
	public List<Conversacion> getMyConversations(){
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			
			Usuario user = usuarioComponent.getLoggedUser();
			List<Conversacion> conversaciones = conversacionRepository.findByUserBuyerOrUserSeller(user, user);
			return conversaciones;
			
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
	}
}
