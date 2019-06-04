package com.tmi.emprendedores.dto;

import java.util.Date;

public class DTO implements Comparable<DTO>{
	
	protected Integer id;
	
	protected Date fechaCreacion;

	public DTO() {	}
	
	public DTO(Integer id, Date fechaCreacion) {
		this.id=id;
		this.fechaCreacion=fechaCreacion;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other.getClass() ==  this.getClass()))return false;
	    DTO otherMyClass = (DTO)other;
	    if(otherMyClass.getId().equals(this.getId())) return true;
	    return false;
	}
	
	@Override
	public int compareTo(DTO o) {
		return id.compareTo(o.getId());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}