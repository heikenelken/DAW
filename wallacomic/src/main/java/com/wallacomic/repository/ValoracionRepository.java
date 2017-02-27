package com.wallacomic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallacomic.domain.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
	Valoracion findById(long id);
}
