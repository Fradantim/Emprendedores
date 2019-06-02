package com.tmi.emprendedores.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmi.emprendedores.persistence.entities.ubicacion.Pais;
import com.tmi.emprendedores.persistence.entities.ubicacion.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
	public List<Provincia> findByPais(Pais pais);
}