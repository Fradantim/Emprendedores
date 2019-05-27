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
	
	
	@Column (name="NOMBRE", nullable=false)
	private String nombre;
	
	@Column (name="DESCRIPCION", nullable=false)
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public EmprendimientoDTO toMiniDTO() {
		return new EmprendimientoDTO(id, nombre, descripcion, link, contacto);
	}
	
	@Override
	public EmprendimientoDTO toDTO() {
		EmprendimientoDTO dto = toMiniDTO();
		dto.setUsuario(usuario.toMiniDTO());
		return dto;
	}
	
	public void modificarEmprendimiento(Emprendimiento emprendimiento) {
		this.nombre = emprendimiento.getNombre();
		/*cuando el CKEDITOR manda el atributo descripcion modificado le mete espacios
		y saltos del linea al fondo, si lo llevo asi de nuevo a la pantalla despues 
		rompe el jsp y se va todo a la mierda, asi amortiguo las cosas
		*/
		this.descripcion = emprendimiento.getDescripcion().replace("\r", "").replace("\n", "").trim();
		this.link = emprendimiento.getLink();
		this.contacto = emprendimiento.getContacto();
	}
}
