package com.tmi.emprendedores.dto;

import java.util.Date;

public class MensajeDTO extends DTO{
	
	private static final String POSITION = "bottom right";
	
	public enum TipoMensaje{
		SUCCESS, INFO, WARN, ERROR;

		public String getName() {
			return name().toLowerCase();
		}
	}
	
	private TipoMensaje tipo;

	private String mensaje;

	public MensajeDTO(Integer id, Date fechaCreacion, TipoMensaje tipo, String detalle) {
		super(id, fechaCreacion);
		this.tipo = tipo;
		this.mensaje = detalle;
	}
	
	public MensajeDTO(TipoMensaje tipo, String detalle) {
		this(null, null, tipo, detalle);
	}
	
	public MensajeDTO(String detalle) {
		this(null, null, TipoMensaje.INFO, detalle);
	}
	
	public String getTipo() {
		return tipo.getName();
	}

	public void setTipo(TipoMensaje tipo) {
		this.tipo = tipo;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getBuild() {
		return "'"+getMensaje()+"', {position:'"+POSITION+"', className:'"+getTipo()+"'}" ;
	}
}
