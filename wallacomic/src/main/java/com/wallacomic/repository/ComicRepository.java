package com.wallacomic.repository;


import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wallacomic.domain.Comic;

public interface ComicRepository extends JpaRepository<Comic, Long>{
	Comic findById(long id);
	Comic findByTitulo(String titulo);
	
}
