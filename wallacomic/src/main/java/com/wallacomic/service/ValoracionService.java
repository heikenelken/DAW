package com.wallacomic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.repository.ValoracionRepository;


@Service
public class ValoracionService {
	
	//@Autowired
	//UsuarioService usuarioService;
	
	@Autowired
	UsuarioComponent usuarioComponent;

	@Autowired
	private ValoracionRepository valoracionRepository;
	
	public List<Valoracion> findAll(){
		return valoracionRepository.findAll();
	}
	
	public Valoracion findById(long id){
		return valoracionRepository.findById(id);
	}
	
	public Valoracion findOne(long id){
		return valoracionRepository.findOne(id);
	}
	
	public void guardarValoracion(long user_receive_id, String comentario, int numEstrellas) {
		
		/*Usuario user_receive = usuarioService.findOne(user_receive_id);
		
		if((usuarioComponent.isLoggedUser()) && (user_receive != null) && usuarioComponent.hasAdminPermissions()){
			Usuario user_give = usuarioComponent.getLoggedUser();
			
			if(user_give != user_receive){
				Valoracion valoracion = new Valoracion(user_give, user_receive, comentario, numEstrellas);
				this.save(valoracion);
			}
		}else{
			throw new BadCredentialsException("User not found");
		}*/
		
	}
	
	public void save(Valoracion val){
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			valoracionRepository.save(val);
		}else{
			throw new BadCredentialsException("Error de creacion");
		}
		
	}
	
}
