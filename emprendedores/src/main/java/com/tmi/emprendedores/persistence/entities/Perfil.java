package com.tmi.emprendedores.persistence.entities;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.PerfilDTO;

@Entity
@Table(name="PERFIL")
public class Perfil extends AbsEntity implements DTOTransformable<PerfilDTO>{

	public Perfil () { }
	
	private Perfil(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public static final Perfil CLIENTE = new Perfil(1,"Cliente");
	public static final Perfil EMPRENDEDOR = new Perfil(2,"Emprendedor");
	public static final Perfil MODERADOR = new Perfil(3,"Moderador");
	public static final Perfil ADMINISTADOR = new Perfil(4,"Administrador");
	
	@Column (name="NOMBRE", nullable=false)
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
	
	public static List<Perfil> getPerfiles(){
		return Arrays.asList(CLIENTE,EMPRENDEDOR,MODERADOR,ADMINISTADOR);
	}
	
	@Override
	public PerfilDTO toDTO() {
		return new PerfilDTO(id, nombre);
	}
}
