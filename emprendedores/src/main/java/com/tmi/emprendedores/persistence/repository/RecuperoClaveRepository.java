package com.tmi.emprendedores.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmi.emprendedores.persistence.entities.RecuperoClave;

public interface RecuperoClaveRepository extends JpaRepository<RecuperoClave, Integer> {
	
	public RecuperoClave findByIdUsuarioEncriptado(String idUsuarioEncriptado);
	
}