package com.tmi.emprendedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.persistence.entities.ubicacion.Provincia;
import com.tmi.emprendedores.persistence.repository.LocalidadRepository;
import com.tmi.emprendedores.service.LocalidadService;

@Service
public class LocalidadServiceImpl implements LocalidadService {
   
		
    @Autowired
    private LocalidadRepository locRepo;

	@Override
	public void save(Localidad localidad) {
		locRepo.save(localidad);		
	}

	@Override
	public Localidad findById(Integer id) {
		if(id == null) return null;
		return locRepo.findById(id).get();
	}

	@Override
	public List<Localidad> findByProvincia(Provincia provincia) {
		return locRepo.findByProvinciaOrderByNombreAsc(provincia);
	}
}