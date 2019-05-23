package com.tmi.emprendedores.dto;

public class PerfilDTO extends DTO{
	
	private String nombre;

	public PerfilDTO(Integer id, String nombre) {
		super(id);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
