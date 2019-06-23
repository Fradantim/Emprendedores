package com.tmi.emprendedores.controller.view;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.dto.DTO;
import com.tmi.emprendedores.dto.MensajeDTO;
import com.tmi.emprendedores.dto.MensajeDTO.TipoMensaje;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.service.UsuarioService;

@Controller
public abstract class WebController {
	
	/**
	 * Atributos para determinar dimensiones de archivos
	 */
	public static final int B = 1, KB=B*1024, MB=KB*1024, GB=MB*1024;
	
	
	protected final static int DEFAULT_PAGE_SIZE = 10;
    
	@Autowired
	protected UsuarioService usuarioService;
	    
	public String welcome(Model model, Principal principal) {
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
    
    protected String goToNoSeEncontroObjeto(Model model, Principal principal, String objeto, Integer id) {
    	addMensajes(model, new MensajeDTO(TipoMensaje.ERROR,"No se encontro "+objeto+" con id:"+id));
		return welcome(model, principal);
    }
    
    /**
    * Cuando el CKEDITOR o el iframe de google maps manda el atributo le mete espacios y saltos del linea al fondo, si lo llevo asi de
    * nuevo a la pantalla rompe el jsp y se va todo a la mierda, asi amortiguo las cosas.
	*/
    protected String amortiguarInputHTML(String value) {
    	return value.replace("\r", "").replace("\n", "").trim();
    }
    
    
    /**
     * retorna el primer elemento de la lista que no sea null
     * @param thisOne
     * @param thisOther
     * @return
     */
    protected String choice(String... choices) {
    	for(String choice : choices) {
    		if(choice != null) return choice;
    	}
    	return null;
    }
    
    /**
     * Recorre la lista de DTOs, y si el id de alguno corresponde al parametro <b>slectedId</b> lo marca como <i>selected = true</i>
     * @param dtos
     * @param selectedId
     */
	protected void updateSelected(List<? extends DTO> dtos, Integer selectedId) {
		dtos.stream().forEach(dto -> dto.setSelected( dto.getId().equals(selectedId) ));
	}
}