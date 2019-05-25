package com.tmi.emprendedores.dto;

import java.util.Collections;
import java.util.List;

public class UsuarioDTO extends DTO{
	
	private String nombre;
		
	private String apellido;
		
	private String username;
	
	private String email;
	
	private List<PerfilDTO> perfiles;
	
	private EmprendimientoDTO emprendimiento;
	
	public UsuarioDTO(Integer id, String nombre, String apellido, String email, String username, EmprendimientoDTO emprendimiento) {
		super(id);
		this.nombre = nombre;
		this.apellido = apellido;
		this.email= email;
		this.username = username;
		perfiles = Collections.emptyList();
		this.setEmprendimiento(emprendimiento);
	}

	public UsuarioDTO(Integer id, String nombre, String apellido, String email, String nick, EmprendimientoDTO emprendimiento, List<PerfilDTO> perfiles) {
		this(id,nombre,apellido, email, nick, emprendimiento);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
}
