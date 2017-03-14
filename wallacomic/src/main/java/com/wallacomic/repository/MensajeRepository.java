package com.wallacomic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallacomic.domain.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
	Mensaje findById(long id);
}
