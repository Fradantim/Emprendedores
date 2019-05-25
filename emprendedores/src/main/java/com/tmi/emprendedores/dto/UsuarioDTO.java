package com.tmi.emprendedores.dto;

import java.util.List;

public class UsuarioDTO extends DTO{
	
	private String nombre;
		
	private String apellido;
		
	private String nick;
	
	private String email;
	
	private List<PerfilDTO> perfiles;
	
	private EmprendimientoDTO emprendimiento;
	
	private UbicacionDTO ubicacion;
	
	public UsuarioDTO(Integer id, String nombre, String apellido, String email, String nick, List<PerfilDTO> perfiles) {
		super(id);
		this.nombre = nombre;
		this.apellido = apellido;
		this.email= email;
		this.nick = nick;
		this.perfiles = perfiles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public List<PerfilDTO> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<PerfilDTO> perfiles) {
		this.perfiles = perfiles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmprendimientoDTO getEmprendimiento() {
		return emprendimiento;
	}

	public void setEmprendimiento(EmprendimientoDTO emprendimiento) {
		this.emprendimiento = emprendimiento;
	}

	public UbicacionDTO getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionDTO ubicacion) {
		this.ubicacion = ubicacion;
	}
}
