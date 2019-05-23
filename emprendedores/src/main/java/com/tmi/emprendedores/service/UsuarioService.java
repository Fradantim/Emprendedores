package com.tmi.emprendedores.service;

import com.tmi.emprendedores.persistence.entities.Usuario;

public interface UsuarioService {
    void save(Usuario user);

    Usuario findByUsername(String username);
    
    Usuario findByEmail(String email);
}
