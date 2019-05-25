package com.tmi.emprendedores.service;

import com.tmi.emprendedores.persistence.entities.Usuario;

public interface UsuarioService {
    
	public void save(Usuario user);
	
	/**
	 * Metodo para grabar nuevos usuarios, se aplican las reglas de negocio
	 */
	public void saveNew(Usuario user);

	public Usuario findByUsername(String username);
    
	public Usuario findByEmail(String email);
}
