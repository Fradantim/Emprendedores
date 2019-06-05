package com.tmi.emprendedores.persistence.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tmi.emprendedores.controller.view.HasOwner;
import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.EventoDTO;
import com.tmi.emprendedores.dto.EventoDTO.Estado;
import com.tmi.emprendedores.dto.EventoDTO.TipoInscripcion;
import com.tmi.emprendedores.dto.EventoDTO.TipoVisibilidad;
import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;

@Entity
@Table(name="EVENTO")
public class Evento extends AbsEntity implements HasOwner<Usuario>, DTOTransformable<EventoDTO>{
	
	@Column (name="NOMBRE", nullable=false)
	private String nombre;
	
	@Column (name="DESCRIPCION", nullable=false, length = 5000)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="LOCALIDAD_ID")
	private Localidad localidad;
	
	@ManyToOne
	@JoinColumn(name="USUARIO_ID")
    private Usuario creador;
	
	@Column (name="FECHA", nullable=false)
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private TipoInscripcion tipoInscripcion;
	
	@Enumerated(EnumType.STRING)
	private TipoVisibilidad tipoVisibilidad;
	
	@ManyToMany (fetch= FetchType.LAZY)
	@JoinTable(name = "EMPRENDIMIENTO_EVENTO",
		joinColumns = @JoinColumn(name = "EMPRENDEDOR_ID"),
		inverseJoinColumns = @JoinColumn(name = "EVENTO_ID"))
	private Set<Usuario> emprendedores;
	
	@Column (name="CANTIDAD_EMPRENDEDORES", nullable=false)
	private Integer cantidadEmprendedores;
	
	/**
	* Atributo usado para determinar si el usuario logueado está inscripto a este evento.
	*/
	@Transient
	private Boolean inscripto;
	
	public Evento() {
		super();
	}

	@Override
	public Usuario getOwner() {
		return creador;
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

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Estado getEstado() {
		if(fecha == null) {
			return Estado.PENDIENTE;
		} 
		return (new Date().compareTo(fecha) >0) ? Estado.FINALIZADO : Estado.PENDIENTE;
	}
	
	public boolean isFinalizado() {
		return getEstado()== Estado.FINALIZADO;
	}
	
	public boolean isAbierto() {
		return getTipoInscripcion().equals(TipoInscripcion.ABIERTA);
	}

	public TipoInscripcion getTipoInscripcion() {
		return tipoInscripcion;
	}

	public void setTipoInscripcion(TipoInscripcion tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}
	
	public void setTipoInscripcion(String tipoInscripcion) {
		setTipoInscripcion(TipoInscripcion.valueOf(tipoInscripcion));
	}

	public TipoVisibilidad getTipoVisibilidad() {
		return tipoVisibilidad;
	}

	public void setTipoVisibilidad(TipoVisibilidad tipoVisibilidad) {
		this.tipoVisibilidad = tipoVisibilidad;
	}
	
	public void setTipoVisibilidad(String tipoVisibilidad) {
		setTipoVisibilidad(TipoVisibilidad.valueOf(tipoVisibilidad));
	}

	public Set<Usuario> getEmprendedores() {
		return emprendedores;
	}
	
	public Integer getCantidadEmprendedores() {
		return cantidadEmprendedores;
	}

	public void setCantidadEmprendedores(Integer cantidadEmprendedores) {
		this.cantidadEmprendedores = cantidadEmprendedores;
	}

	public Boolean getInscripto() {
		return inscripto;
	}

	public void setInscripto(Boolean inscripto) {
		this.inscripto = inscripto;
	}

	public void setEmprendedores(Set<Usuario> emprendedores) {
		this.emprendedores = emprendedores;
		cantidadEmprendedores= emprendedores.size();
	}
	
	public void addEmprendedor(Usuario emprendedor) {
		if(emprendedores == null)
			emprendedores = new HashSet<>();
		emprendedores.add(emprendedor);
		cantidadEmprendedores= emprendedores.size();
	}
	
	public void removeEmprendedor(Usuario emprendedor) {
		if(emprendedores == null)
			emprendedores = new HashSet<>();
		emprendedores.remove(emprendedor);
		cantidadEmprendedores= emprendedores.size();
	}

	public void modificarEvento(Evento nuevo) {
		this.nombre=nuevo.nombre;
		this.descripcion=nuevo.descripcion;
		this.fecha=nuevo.fecha;
		this.localidad=nuevo.localidad;
		this.tipoInscripcion= nuevo.tipoInscripcion;
		this.tipoVisibilidad= nuevo.tipoVisibilidad;
	}
	
	@Override
	public EventoDTO toDTO() {
		EventoDTO dto = toMiniDTO();
		dto.setEmprendedores(emprendedores.stream().map(Usuario::toMiniDTO).collect(Collectors.toSet()));
		return dto;
	}

	@Override
	public EventoDTO toMiniDTO() {
		return new EventoDTO(id, fechaCreacion, nombre, descripcion, localidad.toMiniDTO(), creador.toMiniDTO(), fecha, tipoInscripcion, tipoVisibilidad, getEstado(),cantidadEmprendedores, inscripto);
	}
}
