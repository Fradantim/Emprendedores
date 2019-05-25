package com.tmi.emprendedores.dto;

public class EmprendimientoDTO extends DTO{
	
	private String nombre;

	public EmprendimientoDTO(Integer id, String nombre, String descripcion, String link, String contacto) {
		super(id);
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.link = link;
		this.contacto = contacto;
	}

	private String descripcion;
	
	private String link;

	private String contacto;

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
}
