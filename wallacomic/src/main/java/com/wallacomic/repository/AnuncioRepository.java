package com.wallacomic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{

	List<Anuncio> findByUserAndType(Usuario u, boolean t);
	
	List<Anuncio> findByComicAndType(Comic c, boolean t);
	
}
