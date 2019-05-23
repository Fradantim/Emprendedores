package com.tmi.emprendedores.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.persistence.repository.PerfilRepository;
import com.tmi.emprendedores.persistence.repository.UsuarioRepository;
import com.tmi.emprendedores.service.UsuarioService;

import java.util.HashSet;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private PerfilRepository perfilRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Usuario usuario) {
    	usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
    	usuario.setPerfiles(new HashSet<>(perfilRepo.findAll()));
    	usuarioRepo.save(usuario);
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepo.findByUsername(username);
    }

	@Override
	public Usuario findByEmail(String email) {
		 return usuarioRepo.findByEmail(email);
	}
}