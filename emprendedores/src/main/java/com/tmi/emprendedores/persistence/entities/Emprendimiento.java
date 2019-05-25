package com.tmi.emprendedores.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.EmprendimientoDTO;

@Entity
@Table(name="EMPRENDIMIENTO")
public class Emprendimiento extends AbsEntity implements DTOTransformable<EmprendimientoDTO>{
	
	@Column (name="NOMBRE")
	private String nombre;
	
	@Column (name="DESCRIPCION")
	private String descripcion;
	
	@Column (name="LINK")
	private String link;
	
	@Column (name="CONTACTO")
	private String contacto;
	
	@OneToOne(mappedBy = "emprendimiento")
    private Usuario usuario;
	
	public Emprendimiento() {
		super();
	}
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	@Override
	public EmprendimientoDTO toDTO() {
		return new EmprendimientoDTO(id, nombre, descripcion, link, contacto);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
