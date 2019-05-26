package com.tmi.emprendedores.dto;

public class MensajeDTO extends DTO{
	
	public enum TipoMensaje{
		SUCCESS, INFO, WARN, ERROR;

		public String getName() {
			return name().toLowerCase();
		}
	}
	
	private TipoMensaje tipo;

	private String detalle;

	public MensajeDTO(Integer id, TipoMensaje tipo, String detalle) {
		super(id);
		this.tipo = tipo;
		this.detalle = detalle;
	}
	
	public MensajeDTO(TipoMensaje tipo, String detalle) {
		this(null, tipo, detalle);
	}
	
	public String getTipo() {
		return tipo.getName();
	}

	public void setTipo(TipoMensaje tipo) {
		this.tipo = tipo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
}
