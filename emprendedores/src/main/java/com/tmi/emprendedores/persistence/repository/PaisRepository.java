package com.tmi.emprendedores.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmi.emprendedores.persistence.entities.ubicacion.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer> {
	
}