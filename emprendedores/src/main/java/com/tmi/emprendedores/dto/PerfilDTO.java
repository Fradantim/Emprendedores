package com.tmi.emprendedores.dto;

import java.util.Date;

public class PerfilDTO extends DTO{
	
	private String nombre;

	public PerfilDTO(Integer id, Date fechaCreacion, String nombre) {
		super(id, fechaCreacion);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
