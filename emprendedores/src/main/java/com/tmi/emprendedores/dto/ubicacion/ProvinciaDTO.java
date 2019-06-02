package com.tmi.emprendedores.dto.ubicacion;

import java.util.Set;

import com.tmi.emprendedores.dto.DTO;

public class ProvinciaDTO extends DTO {
	
	private String nombre;
	
	private PaisDTO pais;
	
	private Set<LocalidadDTO> localidades;
	
	public ProvinciaDTO(Integer id, String nombre, PaisDTO pais) {
		super(id);
		this.nombre = nombre;
		this.pais = pais;
	}
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public PaisDTO getPais() {
		return pais;
	}

	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}

	public Set<LocalidadDTO> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(Set<LocalidadDTO> localidades) {
		this.localidades = localidades;
	}

}
