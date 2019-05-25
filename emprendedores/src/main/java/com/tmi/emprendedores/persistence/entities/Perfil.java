package com.tmi.emprendedores.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.PerfilDTO;

@Entity
@Table(name="PERFIL")
public class Perfil extends AbsEntity implements DTOTransformable<PerfilDTO>{

	public Perfil () {
		super();
	}
	
	public Perfil(String nombre) {
		this();
		this.nombre = nombre;
	}
	
	@Column (name="NOMBRE", nullable=false, unique=true)
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Perfil))return false;
	    Perfil otherMyClass = (Perfil)other;
	    if(otherMyClass.getId().equals(this.getId())) return true;
	    return false;
	}
	
	@Override
	public PerfilDTO toDTO() {
		return new PerfilDTO(id, nombre);
	}
}
