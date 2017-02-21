package com.wallacomic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallacomic.domain.Comic;

public interface ComicRepository extends JpaRepository<Comic, Long>{
	

}
