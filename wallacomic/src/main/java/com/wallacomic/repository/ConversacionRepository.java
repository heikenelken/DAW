package com.wallacomic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallacomic.domain.Conversacion;
import com.wallacomic.domain.Usuario;

public interface ConversacionRepository extends JpaRepository<Conversacion, Long>{
//	List<Conversacion> findByUserBuyerAndUserSeller(Usuario u1, Usuario u2);
	List<Conversacion> findByUserBuyerOrUserSeller(Usuario u1, Usuario u2);
	List<Conversacion> findByUserBuyer(Usuario u);
	Conversacion findById(long id);
}
