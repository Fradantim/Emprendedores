package com.tmi.emprendedores.service;

import java.util.Arrays;
import java.util.List;

import com.tmi.emprendedores.persistence.entities.Perfil;

public interface PerfilService {
	
	public static Perfil CLIENTE = new Perfil("CLIENTE");
	public static Perfil EMPRENDEDOR = new Perfil("EMPRENDEDOR");
	public static Perfil MODERADOR = new Perfil("MODERADOR");
	public static Perfil ADMINISTADOR = new Perfil("ADMINISTRADOR");
	
	public void save(Perfil perfil);

	public Perfil findByNombre(String nombre);
	
	public static List<Perfil> getPerfiles(){
		return Arrays.asList(CLIENTE,EMPRENDEDOR,MODERADOR,ADMINISTADOR);
	}
}
