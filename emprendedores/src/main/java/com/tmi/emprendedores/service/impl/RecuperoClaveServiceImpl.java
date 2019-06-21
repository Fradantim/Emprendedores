package com.tmi.emprendedores.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.persistence.entities.RecuperoClave;
import com.tmi.emprendedores.persistence.repository.RecuperoClaveRepository;
import com.tmi.emprendedores.service.RecuperoClaveService;

@Service
public class RecuperoClaveServiceImpl implements RecuperoClaveService {

	@Autowired
    private RecuperoClaveRepository recuperoRepo;

	@Override
	public RecuperoClave save(RecuperoClave recupero) {
		return recuperoRepo.save(recupero);
	}
	
	@Override
	public void delete(RecuperoClave recupero) {
		recuperoRepo.delete(recupero);
	}

	@Override
	public RecuperoClave findByIdUsuarioEncriptado(String idUsuarioEncriptado) {
		return recuperoRepo.findByIdUsuarioEncriptado(idUsuarioEncriptado);
	}

  
}