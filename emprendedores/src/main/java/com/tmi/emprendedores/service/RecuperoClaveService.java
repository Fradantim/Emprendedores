package com.tmi.emprendedores.service;

import com.tmi.emprendedores.persistence.entities.RecuperoClave;

public interface RecuperoClaveService {
    
	public RecuperoClave save(RecuperoClave recupero);
	
	public RecuperoClave findByIdUsuarioEncriptado(String idUsuarioEncriptado);

	public void delete(RecuperoClave recupero);
	
}
