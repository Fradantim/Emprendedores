package com.tmi.emprendedores.persistence.entities.ubicacion;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.ubicacion.ProvinciaDTO;
import com.tmi.emprendedores.persistence.entities.AbsEntity;

@Entity
@Table(name="PROVINCIA")
public class Provincia extends AbsEntity implements DTOTransformable<ProvinciaDTO>{
	
	@Column (name="NOMBRE")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="PAIS_ID")
	private Pais pais;
	
	@OneToMany(mappedBy = "provincia", fetch= FetchType.LAZY)
	private Set<Localidad> localidades;
	
	public Provincia() {
		super();
	}
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Set<Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(Set<Localidad> localidades) {
		this.localidades = localidades;
	}

	@Override
	public ProvinciaDTO toDTO() {
		ProvinciaDTO dto = toMiniDTO();
		dto.setLocalidades(localidades.stream().map(Localidad::toMiniDTO).collect(Collectors.toSet()));
		return null;
	}

	@Override
	public ProvinciaDTO toMiniDTO() {
		return new ProvinciaDTO(id, nombre, pais.toMiniDTO());
	}
}
