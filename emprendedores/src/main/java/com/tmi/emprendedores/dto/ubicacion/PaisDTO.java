package com.tmi.emprendedores.dto.ubicacion;

import java.util.Date;
import java.util.Set;

import com.tmi.emprendedores.dto.DTO;

public class PaisDTO extends DTO {
	
	private String nombre;
	
	private Set<ProvinciaDTO> provincias;
	
	public PaisDTO(Integer id, Date fechaCreacion,  String nombre) {
		super(id, fechaCreacion);
		this.nombre = nombre;
	}
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<ProvinciaDTO> getProvincias() {
		return provincias;
	}

	public void setProvincias(Set<ProvinciaDTO> provincias) {
		this.provincias = provincias;
	}
}
