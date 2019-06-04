package com.tmi.emprendedores.controller.view;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.dto.MensajeDTO;
import com.tmi.emprendedores.dto.MensajeDTO.TipoMensaje;
import com.tmi.emprendedores.persistence.entities.Evento;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.service.EventoService;
import com.tmi.emprendedores.service.UsuarioService;

@Controller
public abstract class WebController {
    
	@Autowired
	protected UsuarioService usuarioService;
	
    @Autowired
    private EventoService eventoService;
    
	public String welcome(Model model, Principal principal) {
		List<Evento> eventos = eventoService.findPublicos();
    	if(isUsuarioLogueado(principal)) {
    		Usuario usuarioLogueado = getLoggedUser(principal);
    		addUsuarioLogueado(model, principal);
    		for(Evento evento: eventos) {
    			if(!evento.getCreador().equals(usuarioLogueado)) {
    				evento.setInscripto(evento.getEmprendedores().contains(usuarioLogueado));
    			} else {
    				evento.setInscripto(false);
    			}
    		}
    	}
    	
    	model.addAttribute("eventos", eventos.stream().map(Evento::toDTO).collect(Collectors.toList()));
        return Page.PORTAL.getFile();
    }
	
	protected Usuario getLoggedUser(Principal principal) {
		return usuarioService.findByNick(principal.getName());
	}
	
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
    
    protected void addMensajes(Model model, Collection<MensajeDTO> mensajes) {
    	addMensajes(model, mensajes.toArray(new MensajeDTO[mensajes.size()]));
    }
        
    protected void addMensajeNoTienePermisoAcceso(Model model) {
    	addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No tiene permisos para ingresar aqui."));
    }
    
    protected void addMensajeDebeIniciarSesion(Model model) {
    	addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "Debe iniciar sesion para ingresar aqui."));
    }
    
    protected void addMensajeNoTienePermisoEdicion(Model model) {
    	addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No tiene permisos para modificar este elemento."));
    }
    
    protected String goToNoTienePermisoAcceso(Model model, Principal principal) {
    	addMensajeNoTienePermisoAcceso(model);
    	return welcome(model, principal);
    }
    
    protected Page goToDebeIniciarSesion(Model model) {
    	addMensajeDebeIniciarSesion(model);
    	return Page.LOGIN;	
    }
    
    protected String goToNoTienePermisoEdicion(Model model, Principal principal) {
    	addMensajeNoTienePermisoEdicion(model);
    	return welcome(model, principal);
    }
}