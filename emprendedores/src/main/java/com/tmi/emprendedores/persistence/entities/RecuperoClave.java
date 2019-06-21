package com.tmi.emprendedores.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tmi.emprendedores.exception.CryptoException;
import com.tmi.emprendedores.util.CryptUtil;


@Entity
@Table(name="RECUPERO_CLAVE")
public class RecuperoClave extends AbsEntity{
	
	@Column (name="USUARIO_ID_ENC", nullable=false)
	private String idUsuarioEncriptado;
	
	@OneToOne
	@JoinColumn(name="USUARIO_ID")
    private Usuario usuario;
	
	public RecuperoClave() { }
	
	public RecuperoClave(Usuario usuario) throws CryptoException {
		super();
		this.usuario = usuario;
		this.idUsuarioEncriptado = CryptUtil.encrypt(String.valueOf(usuario.getId()));
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String getIdUsuarioEncriptado() {
		return idUsuarioEncriptado;
	}
}