package com.tmi.emprendedores.dto;

import java.util.Date;
import java.util.Set;

import com.tmi.emprendedores.dto.ubicacion.LocalidadDTO;

public class EventoDTO extends DTO{
	
	public enum TipoInscripcion{ ABIERTA, CERRADA; }
	
	public enum TipoVisibilidad{ PUBLICA;/*TODO proximamente tmb PRIVADA */}
	
	public enum Estado{ PENDIENTE, FINALIZADO}
	
	private String nombre;
	
	private String descripcion;
	
	private LocalidadDTO localidad;
	
    private UsuarioDTO creador;
	
	private Date fecha;
	
	private String tipoInscripcion;
	
	private String tipoVisibilidad;
	
	private String estado;
	
	private Set<UsuarioDTO> emprendedores;
	
	private Integer cantidadEmprendedores;
	
	private Boolean inscripto;
	
	public EventoDTO(Integer id, Date fechaCreacion, String nombre, String descripcion, LocalidadDTO localidad, UsuarioDTO creador,
			Date fecha, TipoInscripcion tipoInscripcion, TipoVisibilidad tipoVisibilidad, Estado estado, Integer cantidadEmprendedores, Boolean inscripto) {
		super(id, fechaCreacion);
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.localidad = localidad;
		this.creador = creador;
		this.fecha = fecha;
		this.tipoInscripcion = tipoInscripcion.name();
		this.tipoVisibilidad = tipoVisibilidad.name();
		this.estado = estado.name();
		this.cantidadEmprendedores=cantidadEmprendedores;
		this.inscripto=inscripto;
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

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public UsuarioDTO getCreador() {
		return creador;
	}

	public void setCreador(UsuarioDTO creador) {
		this.creador = creador;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getEstado() {
		return estado;
	}

	public String getTipoInscripcion() {
		return tipoInscripcion;
	}

	public void setTipoInscripcion(String tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}
	
	public String getTipoVisibilidad() {
		return tipoVisibilidad;
	}

	public void setTipoVisibilidad(String tipoVisibilidad) {
		this.tipoVisibilidad = tipoVisibilidad;
	}

	public Set<UsuarioDTO> getEmprendedores() {
		return emprendedores;
	}

	public void setEmprendedores(Set<UsuarioDTO> emprendedores) {
		this.emprendedores = emprendedores;
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
	
	public boolean isFinalizado() {
		return getEstado().equals(Estado.FINALIZADO.name());
	}
}
