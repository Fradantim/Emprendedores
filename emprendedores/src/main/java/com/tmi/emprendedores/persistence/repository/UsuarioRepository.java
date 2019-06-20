package com.tmi.emprendedores.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tmi.emprendedores.persistence.entities.Perfil;
import com.tmi.emprendedores.persistence.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Usuario findByNickIgnoreCase(String nick);
	
	public Usuario findByEmailIgnoreCase(String email);
	
	
	@Query("SELECT u FROM Usuario u WHERE :perfil member of u.perfiles")
	public List<Usuario> getByPerfil(@Param("perfil") Perfil perfil);
	
}