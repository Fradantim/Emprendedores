package com.tmi.emprendedores.service;

import java.util.List;

import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.persistence.entities.ubicacion.Provincia;

public interface LocalidadService {
	
	public void save(Localidad localidad);

	public Localidad findById(Integer id);	
	
	public List<Localidad> findByProvincia(Provincia provincia);
}
