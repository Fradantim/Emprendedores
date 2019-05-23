package com.tmi.emprendedores.persistence.entities;

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
	
	@Column (name="BORRADP")
	protected boolean borrado = false;

	public Integer getId() {
		return id;
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
