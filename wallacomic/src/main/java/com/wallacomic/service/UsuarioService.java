package com.wallacomic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioComponent usuarioComponent;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(long id){
		return usuarioRepository.findOne(id);
	}
	
	public void save(Usuario us){
		
		//if(usuarioRepository.findByNombre(us.getNombre())!=null){
		//	throw new BadCredentialsException("Error de creacion");
		//}else{
			usuarioRepository.save(us);
		//}
		
	}
}
