package com.tmi.emprendedores.service;

import java.util.List;

import com.tmi.emprendedores.persistence.entities.Evento;

public interface EventoService {
    
	public Evento save(Evento evento);
	
	public Evento findById(Integer id);
	
	public List<Evento> findPublicos();
}
