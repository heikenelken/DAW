package com.wallacomic.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.wallacomic.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findById(long id);
	Usuario findByNombre(String nombre);
}
