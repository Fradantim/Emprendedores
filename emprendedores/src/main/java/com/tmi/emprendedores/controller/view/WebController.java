package com.tmi.emprendedores.controller.view;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.dto.MensajeDTO;
import com.tmi.emprendedores.dto.MensajeDTO.TipoMensaje;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.service.UsuarioService;

@Controller
public abstract class WebController {
    
	@Autowired
	protected UsuarioService usuarioService;
	
	/**
     * Agrega al model unicamente el usuario logueado.
     */
    protected void addUsuarioLogueado(Model model, Principal principal) {
    	addUsuarioLogueado(model, usuarioService.findByNick(principal.getName()));
    }
    
    /**
     * Retorna si el usuario esta logueado
     */
    protected boolean isUsuarioLogueado(Principal principal) {
    	return principal!= null && principal.getName() != null;
    }
    
    protected void addUsuarioLogueado(Model model, Usuario usuario) {
    	model.addAttribute("usuarioLogueado", usuario.toDTO());
    }
    
    /**
     * Agrega al model una coleccion de mensajes para ser vistos en la pantalla
     */
    protected void addMensajes(Model model, MensajeDTO... mensajes) {
    	model.addAttribute("mensajes", mensajes);
    }
    
    protected void addMensajeNoTienePermiso(Model model) {
    	addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No tiene permisos para ingresar aqui."));
    }
    
    protected void addMensajeDebeIniciarSesion(Model model) {
    	addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "Debe iniciar sesion para ingresar aqui."));
    }
    
    protected Page goToNoTienePermiso(Model model) {
    	addMensajeNoTienePermiso(model);
    	return Page.PORTAL;
    }
    
    protected Page goToDebeIniciarSesion(Model model) {
    	addMensajeDebeIniciarSesion(model);
    	return Page.LOGIN;	
    }   
}