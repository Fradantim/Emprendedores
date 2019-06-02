package com.tmi.emprendedores.service;

import java.util.List;

import com.tmi.emprendedores.persistence.entities.ubicacion.Pais;
import com.tmi.emprendedores.persistence.entities.ubicacion.Provincia;

public interface ProvinciaService {
	
	public void save(Provincia provincia);

	public Provincia findById(Integer id);	
	
	public List<Provincia> findByPais(Pais pais);
}
