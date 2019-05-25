package com.tmi.emprendedores.persistence.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbsEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="ID")
	protected Integer id;
	
	@Column (name="BORRADO")
	protected boolean borrado;
	
	@Column (name="INSERTO")
	protected Date insertDate;
	
	public AbsEntity() {
		borrado= false;
		insertDate= new Date();
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
	
	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
}
