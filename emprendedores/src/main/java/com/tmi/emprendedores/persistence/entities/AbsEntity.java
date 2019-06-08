package com.tmi.emprendedores.persistence.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbsEntity implements Comparable<AbsEntity>{
	
	public static final int LARGO_COLUMNA_CKEDITOR = 5000;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="ID")
	protected Integer id;
	
	@Column (name="BORRADO")
	protected boolean borrado;
	
	@Column (name="FECHA_CREACION")
	protected Date fechaCreacion;

	public AbsEntity() {
		borrado= false;
		fechaCreacion= new Date();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	/**
	 * Reimplementado exclusivamente con proposito de test
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+"{id:"+id+"}";
	}
	
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        return result;
    }
    
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other.getClass() ==  this.getClass()))return false;
	    AbsEntity otherMyClass = (AbsEntity)other;
	    if(otherMyClass.getId().equals(this.getId())) return true;
	    return false;
	}
	
	@Override
	public int compareTo(AbsEntity o) {
		return id.compareTo(o.getId());
	}
	
	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
