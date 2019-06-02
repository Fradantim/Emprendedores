package com.tmi.emprendedores.service;

import java.util.List;

import com.tmi.emprendedores.persistence.entities.ubicacion.Pais;

public interface PaisService {
	
	public void save(Pais pais);

	public long count();

	public Pais findById(Integer id);
	
	public List<Pais> findAll();	
}
