package com.tmi.emprendedores.dto;

public class UbicacionDTO extends DTO{
	
	private String pais;
	
	private String provincia;
	
	private String localidad;

	public UbicacionDTO(Integer id, String pais, String provincia, String localidad) {
		super(id);
		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
}
