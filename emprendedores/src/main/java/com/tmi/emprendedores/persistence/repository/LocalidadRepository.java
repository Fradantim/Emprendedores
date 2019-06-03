package com.tmi.emprendedores.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.persistence.entities.ubicacion.Provincia;

public interface LocalidadRepository extends JpaRepository<Localidad, Integer> {
	public List<Localidad> findByProvinciaOrderByNombreAsc(Provincia provincia);
}