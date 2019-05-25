package com.tmi.emprendedores.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.UbicacionDTO;

@Entity
@Table(name="UBICACION")
public class Ubicacion extends AbsEntity implements DTOTransformable<UbicacionDTO>{

	@Column (name="pais", nullable=false)
	private String pais;
	
	@Column (name="nombre", nullable=false)
	private String provincia;
	
	@Column (name="localidad", nullable=false)
	private String localidad;
		
	public Ubicacion () {
		super();
	}
	
	public Ubicacion(String pais, String provincia, String localidad) {
		this();
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
	
	@Override
	public UbicacionDTO toDTO() {
		return toMiniDTO();
	}

	@Override
	public UbicacionDTO toMiniDTO() {
		return new UbicacionDTO(id, pais, provincia, localidad);
	}

}
