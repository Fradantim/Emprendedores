package com.tmi.emprendedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.persistence.entities.ubicacion.Pais;
import com.tmi.emprendedores.persistence.repository.PaisRepository;
import com.tmi.emprendedores.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService {
   
    @Autowired
    private PaisRepository paisRepo;

	@Override
	public void save(Pais pais) {
		paisRepo.save(pais);		
	}

	@Override
	public long count() {
		return paisRepo.count();
	}
	
	@Override
	public Pais findById(Integer id) {
		if(id == null) return null;
		return paisRepo.findById(id).orElse(null);
	}

	@Override
	public List<Pais> findAll() {
		return paisRepo.findAll();
	}

}