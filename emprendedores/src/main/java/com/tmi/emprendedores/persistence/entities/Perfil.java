package com.tmi.emprendedores.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
	public PerfilDTO toDTO() {
		return toMiniDTO();
	}

	@Override
	public PerfilDTO toMiniDTO() {
		return new PerfilDTO(id, nombre);
	}
	
	public GrantedAuthority toGrantedAuthority() {
		return new SimpleGrantedAuthority(getNombre());
	}
}
