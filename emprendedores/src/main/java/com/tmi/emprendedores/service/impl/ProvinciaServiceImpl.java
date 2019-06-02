package com.tmi.emprendedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.persistence.entities.ubicacion.Pais;
import com.tmi.emprendedores.persistence.entities.ubicacion.Provincia;
import com.tmi.emprendedores.persistence.repository.ProvinciaRepository;
import com.tmi.emprendedores.service.ProvinciaService;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {
   
    @Autowired
    private ProvinciaRepository provRepo;

	@Override
	public void save(Provincia provincia) {
		provRepo.save(provincia);		
	}

	@Override
	public Provincia findById(Integer id) {
		if(id == null) return null;
		return provRepo.findById(id).get();
	}

	@Override
	public List<Provincia> findByPais(Pais pais) {
		return provRepo.findByPais(pais);
	}
}