package com.tmi.emprendedores.controller.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

@Controller
public abstract class WebController {
    
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