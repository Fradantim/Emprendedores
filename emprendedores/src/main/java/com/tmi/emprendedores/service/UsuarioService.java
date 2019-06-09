package com.tmi.emprendedores.service;

import com.tmi.emprendedores.persistence.entities.Usuario;

public interface UsuarioService {
    
	public Usuario save(Usuario user);
	
	/**
	 * Metodo para grabar nuevos usuarios, se aplican las reglas de negocio
	 */
	public Usuario saveNew(Usuario user);

	public Usuario findByNick(String nick);
    
	public Usuario findByEmail(String email);
	
	public Usuario findById(Integer id);

	public Usuario saveAndEncodePassword(Usuario usuario);
}
