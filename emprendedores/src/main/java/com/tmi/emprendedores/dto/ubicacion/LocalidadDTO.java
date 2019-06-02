package com.tmi.emprendedores.dto.ubicacion;

import com.tmi.emprendedores.dto.DTO;

public class LocalidadDTO extends DTO {
	
	private String nombre;
	
	private ProvinciaDTO provincia;
	
	public LocalidadDTO(Integer id, String nombre, ProvinciaDTO provincia) {
		super(id);
		this.nombre = nombre;
		this.provincia = provincia;
	}
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ProvinciaDTO getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaDTO provincia) {
		this.provincia = provincia;
	}
	
	public Integer getProvinciaId() {
		if(provincia != null)
			return provincia.getId();
		return null;
	}
	
	public Integer getPaisId() {
		if(provincia != null && provincia.getPais() != null)
			return provincia.getPais().getId();
		return null;
	}
	
	public String getProvinciaNombre() {
		if(provincia != null)
			return provincia.getNombre();
		return null;
	}
	
	public String getPaisNombre() {
		if(provincia != null && provincia.getPais() != null)
			return provincia.getPais().getNombre();
		return null;
	}

}
