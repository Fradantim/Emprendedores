package com.tmi.emprendedores.controller.view;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
    
    protected void addUsuarioLogueado(Model model, Usuario usuario) {
    	model.addAttribute("usuarioLogueado", usuario.toDTO());
    }

	protected List<String> evaluarParametrosVacios(Map<String, String> parametrosNoNull) {
    	List<String> errores= new ArrayList<>();
    	for(String key: parametrosNoNull.keySet()) {
    		String val = parametrosNoNull.get(key);
    		if(val == null || val.trim().isEmpty()) {
    			errores.add("El campo "+key+" es obligatorio.");
    		}
    	}
    	return errores;
    }
    
	protected String jsonify(List<String> datos) {
    	String json="{";
    	for(String dato: datos) {
    		json+="'"+dato+"',";
    	}
    	json=json.substring(0, json.length()-1); //saco la ultima coma
    	json+="}";
    	
    	return json;
    }
}