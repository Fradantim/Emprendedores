package com.tmi.emprendedores.persistence.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tmi.emprendedores.controller.view.HasOwner;
import com.tmi.emprendedores.dto.DTOTransformable;
import com.tmi.emprendedores.dto.UsuarioDTO;
import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.service.PerfilService;

@Entity
@Table(name="USUARIO")
public class Usuario extends AbsEntity implements DTOTransformable<UsuarioDTO>{
	
	@Column (name="NOMBRE")
	private String nombre;
	
	@Column (name="APELLIDO")
	private String apellido;
	
	@Column (name="NICK", unique=true)
	private String nick;
	
	@Column (name="PASSWORD")
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Column (name="EMAIL", unique=true)
	private String email;
	
	@ManyToMany
	@JoinTable(name = "USUARIO_PERFIL",
		joinColumns = @JoinColumn(name = "USUARIO_ID"),
		inverseJoinColumns = @JoinColumn(name = "PERFIL_ID"))
	private Set<Perfil> perfiles;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPRENDIMIENTO_ID", referencedColumnName = "ID")
	private Emprendimiento emprendimiento;
	
	@ManyToOne
	@JoinColumn(name="LOCALIDAD_ID")
	private Localidad localidad;
		
	public Usuario() {
		super();
	}
		
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
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
	
	public Emprendimiento getEmprendimiento() {
		return emprendimiento;
	}

	public void setEmprendimiento(Emprendimiento emprendimiento) {
		this.emprendimiento = emprendimiento;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	public void addPerfil(Perfil... nuevosPerfiles) {
		if(perfiles == null)
			perfiles = new HashSet<>();
		for(Perfil perfil : nuevosPerfiles)
			perfiles.add(perfil);
	}
	
	public void removePerfil(Perfil... viejosPerfiles) {
		if(perfiles == null) 
			perfiles = new HashSet<>();
		else
			for(Perfil perfil : viejosPerfiles)
				perfiles.remove(perfil);
	}
	
	public void addPerfiles(Collection<Perfil> nuevosPerfiles) {
		addPerfil(nuevosPerfiles.stream().toArray(Perfil[]::new));
	}
	
	public void removePerfiles(Collection<Perfil> viejosPerfiles) {
		removePerfil(viejosPerfiles.stream().toArray(Perfil[]::new));
	}

	public void modificarPerfil(Usuario nuevo) {
		this.nombre=nuevo.nombre;
		this.apellido=nuevo.apellido;
		this.nick=nuevo.nick;
		this.email=nuevo.email;
	}
	
	@Override
	public UsuarioDTO toDTO() {
		UsuarioDTO dto = toMiniDTO();
		dto.setPerfiles(getPerfiles().stream().map(Perfil::toMiniDTO).collect(Collectors.toList()));
		if(emprendimiento != null)
			dto.setEmprendimiento(emprendimiento.toMiniDTO());
		if(localidad != null)
			dto.setLocalidad(localidad.toMiniDTO());
		return dto;
	}

	@Override
	public UsuarioDTO toMiniDTO() {
		UsuarioDTO dto = new UsuarioDTO(id, nombre, apellido, email, nick);
		return dto;
	}
	
	public boolean puedeEditar(AbsEntity entity) {
		if(poseePerfil(PerfilService.MODERADOR))
			return true;
		if(entity instanceof HasOwner<?>) {
			return this.equals(((HasOwner<?>) entity).getOwner());
		}
		return false;
	}
}
