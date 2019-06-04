package com.tmi.emprendedores.persistence.entities.ubicacion;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.ubicacion.PaisDTO;
import com.tmi.emprendedores.persistence.entities.AbsEntity;

@Entity
@Table(name="PAIS")
public class Pais extends AbsEntity implements DTOTransformable<PaisDTO>{
	
	@Column (name="NOMBRE")
	private String nombre;
	
	@OneToMany(mappedBy = "pais", fetch= FetchType.LAZY)
	private Set<Provincia> provincias;
	
	public Pais() {
		super();
	}
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(Set<Provincia> provincias) {
		this.provincias = provincias;
	}

	@Override
	public PaisDTO toDTO() {
		PaisDTO dto = toMiniDTO();
		dto.setProvincias(provincias.stream().map(Provincia::toMiniDTO).collect(Collectors.toSet()));
		return null;
	}

	@Override
	public PaisDTO toMiniDTO() {
		return new PaisDTO(id, fechaCreacion, nombre);
	}
}
