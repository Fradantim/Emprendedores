package com.tmi.emprendedores.dto;

import java.util.List;

public class UsuarioDTO extends DTO{
	
	private String nombre;
		
	private String apellido;
		
	private String nick;
	
	private String email;
	
	private List<PerfilDTO> perfiles;
	
	private EmprendimientoDTO emprendimiento;
	
	private String pais;
	
	private String provincia;
	
	private String localidad;
	
	public UsuarioDTO(Integer id, String nombre, String apellido, String email, String nick, List<PerfilDTO> perfiles, String pais, String provincia, String localidad) {
		super(id);
		this.nombre = nombre;
		this.apellido = apellido;
		this.email= email;
		this.nick = nick;
		this.perfiles = perfiles;
		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
}
