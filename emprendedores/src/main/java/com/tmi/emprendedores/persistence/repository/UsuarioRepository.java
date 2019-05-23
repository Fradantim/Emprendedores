package com.tmi.emprendedores.persistence.repository;

import com.tmi.emprendedores.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Usuario findByUsername(String username);
	
	public Usuario findByEmail(String email);
	
}