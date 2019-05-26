package com.tmi.emprendedores.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.persistence.entities.Perfil;
import com.tmi.emprendedores.persistence.repository.PerfilRepository;
import com.tmi.emprendedores.service.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService {
   
    @Autowired
    private PerfilRepository perfilRepo;
	
    @Override
	public void save(Perfil perfil) {
		perfilRepo.save(perfil);
	}
    
	@Override
	public Perfil findByNombre(String nombre) {
		return perfilRepo.findByNombre(nombre);
	}
}