package com.tmi.emprendedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.persistence.entities.Perfil;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.persistence.repository.UsuarioRepository;
import com.tmi.emprendedores.service.PerfilService;
import com.tmi.emprendedores.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Usuario saveNew(Usuario usuario) {
    	usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
    	usuario.addPerfil(PerfilService.CLIENTE); //fuerzo el perfil de cliente
    	return usuarioRepo.save(usuario);
    }
    
    @Override
    public Usuario save(Usuario usuario) {
    	return usuarioRepo.save(usuario);
    }
    
    @Override
    public Usuario saveAndEncodePassword(Usuario usuario) {
    	usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
    	return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario findByNick(String nick) {
        return usuarioRepo.findByNickIgnoreCase(nick);
    }

	@Override
	public Usuario findByEmail(String email) {
		 return usuarioRepo.findByEmailIgnoreCase(email);
	}

	@Override
	public Usuario findById(Integer id) {
		if (id == null) return null;
		return usuarioRepo.findById(id).get();
	}
	
	@Override
	public List<Usuario> getByPerfil(Perfil p) {
		if (p == null) return null;
		return usuarioRepo.getByPerfil(p, PageRequest.of(0, 10));
	}
}