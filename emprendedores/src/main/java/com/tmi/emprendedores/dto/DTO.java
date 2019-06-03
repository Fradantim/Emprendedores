package com.tmi.emprendedores.dto;

public class DTO implements Comparable<DTO>{
	
	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public DTO() {	}
	
	public DTO(Integer id) {
		this.id=id;
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
}