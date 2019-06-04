package com.tmi.emprendedores.persistence.entities.ubicacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.ubicacion.LocalidadDTO;
import com.tmi.emprendedores.persistence.entities.AbsEntity;

@Entity
@Table(name="LOCALIDAD")
public class Localidad extends AbsEntity implements DTOTransformable<LocalidadDTO>{
	
	@Column (name="NOMBRE")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="PROVINCIA_ID")
	private Provincia provincia;
	
	public Localidad() {
		super();
	}
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Override
	public LocalidadDTO toDTO() {
		return toMiniDTO();
	}

	@Override
	public LocalidadDTO toMiniDTO() {
		return new LocalidadDTO(id, fechaCreacion, nombre, provincia.toMiniDTO());
	}
}
