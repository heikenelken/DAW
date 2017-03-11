package com.wallacomic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
	Valoracion findById(long id);
	
	List<Valoracion> findByuserReceive (Usuario u);
}
