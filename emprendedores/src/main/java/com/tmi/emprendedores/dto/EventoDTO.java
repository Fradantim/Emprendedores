package com.tmi.emprendedores.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import com.tmi.emprendedores.dto.ubicacion.LocalidadDTO;
import com.tmi.emprendedores.util.DatePickerUtil;

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
	
	private Integer cantidadAsistencia;
	
	/**
	* Atributo usado para determinar si el usuario logueado esta inscripto a este evento.
	*/
	private Boolean inscripto;
	
	/**
	* Atributo usado para determinar si el usuario logueado indico que asistira a este evento.
	*/
	private Boolean asiste;
	
	private String descripcionLarga;
	
	private String mapa;
	
	private String fotoB64;
	
	private Integer cantidadMaxInscripcion;
	
	public EventoDTO(Integer id, Date fechaCreacion, String nombre, String descripcion, LocalidadDTO localidad, UsuarioDTO creador,
			Date fecha, TipoInscripcion tipoInscripcion, TipoVisibilidad tipoVisibilidad, Estado estado, Integer cantidadEmprendedores, String fotoB64, Integer cantidadAsistencia, Integer cantidadMaxInscripcion) {
		super(id, fechaCreacion);
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.localidad = localidad;
		this.creador = creador;
		this.fecha = fecha;
		this.tipoInscripcion = tipoInscripcion.name();
		this.tipoVisibilidad = tipoVisibilidad.name();
		this.estado = estado.name();
		this.cantidadEmprendedores = cantidadEmprendedores;
		this.fotoB64 = fotoB64;
		this.cantidadAsistencia = cantidadAsistencia;
		this.cantidadMaxInscripcion = cantidadMaxInscripcion;
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

	public String getFecha() {
		if(fecha == null) return null;
		return DatePickerUtil.getDateformater().format(fecha);
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
	
	public boolean isAbierto() {
		return getTipoInscripcion().equals(TipoInscripcion.ABIERTA.name());
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	public Boolean getAsiste() {
		return asiste;
	}

	public void setAsiste(Boolean asiste) {
		this.asiste = asiste;
	}
	
	public String getDatePickerFormatedDate() {
		LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String strMonth= (localDate.getMonthValue() < 10 ? "0" : "") + localDate.getMonthValue();
		String strDay= (localDate.getDayOfMonth() < 10 ? "0" : "") + localDate.getDayOfMonth();
		return "\""
				+localDate.getYear()
				+strMonth
				+strDay
				+"\"";
	}

	public String getFotoB64() {
		return fotoB64;
	}

	public void setFotoB64(String fotoB64) {
		this.fotoB64 = fotoB64;
	}

	public Integer getCantidadAsistencia() {
		return cantidadAsistencia;
	}

	public void setCantidadAsistencia(Integer cantidadAsistencia) {
		this.cantidadAsistencia = cantidadAsistencia;
	}

	public Integer getCantidadMaxInscripcion() {
		return cantidadMaxInscripcion;
	}

	public void setCantidadMaxInscripcion(Integer cantidadMaxInscripcion) {
		this.cantidadMaxInscripcion = cantidadMaxInscripcion;
	}
}
