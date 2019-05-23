package com.tmi.emprendedores.persistence.entities;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.UsuarioDTO;

@Entity
@Table(name="USUARIO")
public class Usuario extends AbsEntity implements DTOTransformable<UsuarioDTO>{
	
	@Column (name="NOMBRE")
	private String nombre;
	
	@Column (name="APELLIDO")
	private String apellido;
	
	@Column (name="USERNAME")
	private String username;
	
	@Column (name="PASSWORD")
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Column (name="EMAIL")
	private String email;
	
	/*@ManyToMany(cascade = {CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinTable(name = "USUARIO_PERFIL",
		joinColumns = @JoinColumn(name = "USUARIO_ID"),
		inverseJoinColumns = @JoinColumn(name = "PERFIL_ID")
	)*/
	@ManyToMany
	private Set<Perfil> perfiles;
	
	public Usuario() { }
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public boolean poseePerfil(Perfil perfil) {
		for(Perfil miPerfil: perfiles) {
			if(perfil.equals(miPerfil)) {
				return true;
			}
		}
		return false;
	}
	
	public void agregarPerfil(Perfil perfil) {
		perfiles.add(perfil);
	}
	
	public void removerPerfil(Perfil perfil) {
		perfiles.remove(perfil);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public UsuarioDTO toDTO() {
		return new UsuarioDTO(id, nombre, apellido, email, username, getPerfiles().stream().map(Perfil::toDTO).collect(Collectors.toList()));
	}
}
