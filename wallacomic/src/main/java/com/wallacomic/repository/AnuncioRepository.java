package com.wallacomic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wallacomic.domain.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{

}
