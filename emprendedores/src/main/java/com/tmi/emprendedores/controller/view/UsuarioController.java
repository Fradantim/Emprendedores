package com.tmi.emprendedores.controller.view;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.service.PerfilService;
import com.tmi.emprendedores.service.SecurityService;
import com.tmi.emprendedores.service.UsuarioService;
import com.tmi.emprendedores.validator.UsuarioValidator;

@Controller
public class UsuarioController {
    
	@Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UsuarioValidator usuarioValidator;

    @GetMapping(WebUtils.MAPPING_LOGBOX)
    public String getLogBox(Model model) {
        model.addAttribute("userForm", new Usuario());
        
        return Page.LOGBOX.getFile();
    }
    
    /**
     * Agrega al model unicamente el usuario logueado.
     */
    @GetMapping(WebUtils.MAPPING_GET_USUARIO)
    public void getUsuarioLogueado(Model model, Principal principal) {
    	addUsuarioLogueado(model, usuarioService.findByUsername(principal.getName()));
    }
    
    private void addUsuarioLogueado(Model model, Usuario usuario) {
    	model.addAttribute("usuarioLogueado", usuario.toDTO());
    }
    
    
    @GetMapping(WebUtils.MAPPING_REGISTRO)
    public String registration(Model model) {
        model.addAttribute("userForm", new Usuario());
        return Page.REGISTRIO.getFile();
    }
    
    @GetMapping(WebUtils.MAPPING_MODIFICAR_USUARIO)
    public String goToModificarUsuario(Model model, Principal principal) {
    	model.addAttribute("usuarioLogueado", usuarioService.findByUsername(principal.getName()));
        return Page.MODIFICAR_USUARIO.getFile();
    }
    
    @PostMapping(WebUtils.MAPPING_REGISTRO)
    public String registration(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult) {
        usuarioValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	return Page.REGISTRIO.getFile();
        }

        usuarioService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return Page.PORTAL.redirect();
    }

    @GetMapping(WebUtils.MAPPING_LOGIN)
    public String login(Model model, String error, String logout) {

    	if (error != null)
            model.addAttribute("error", "Usuario o contraseña incorrectos.");

        if (logout != null) {
        	model.addAttribute("message", "Cerró su sesión correctamente.");
        	return Page.PORTAL.getFile();
        }
            

        return Page.LOGIN.getFile();
    }

    @GetMapping({WebUtils.MAPPING_ROOT, WebUtils.MAPPING_PORTAL})
    public String welcome(Model model) {
        return Page.PORTAL.getFile();
    }
    
    @PostMapping(WebUtils.MAPPING_MODIFICAR_USUARIO)
    public ResponseEntity<?> registratsion(Model model, Principal principal, @RequestParam String nombre, @RequestParam String apellido,
    		@RequestParam String username, @RequestParam String email, @RequestParam String pais, @RequestParam String provincia, @RequestParam String localidad,
    		@RequestParam(value = "emprendedor", required = false) String emprendedorCheckBox) {
    	
    	
    	Map<String, String> parametrosNoVacios = new HashMap<>();
    	parametrosNoVacios.put("nombre", nombre);
    	parametrosNoVacios.put("apellido", apellido);
    	parametrosNoVacios.put("nick", username);
    	parametrosNoVacios.put("email", email);
    	parametrosNoVacios.put("pais", pais);
    	parametrosNoVacios.put("provincia", provincia);
    	parametrosNoVacios.put("localidad", localidad);
    	
    	List<String> errores = evaluarParametrosVacios(parametrosNoVacios);
    	if(errores.size()>0) {
    		return new ResponseEntity<>(jsonify(errores),HttpStatus.BAD_REQUEST);
    	}
    	
    	Usuario usuario = usuarioService.findByUsername(principal.getName());
    	
    	usuario.setApellido(apellido);
    	usuario.setEmail(email);
    	usuario.setNombre(nombre);
    	usuario.setUsername(username);
    	
    	usuario.getUbicacion().setLocalidad(localidad);
    	usuario.getUbicacion().setPais(pais);
    	usuario.getUbicacion().setProvincia(provincia);
    	
    	if(emprendedorCheckBox != null ) {
    		usuario.addPerfil(PerfilService.EMPRENDEDOR);
    	} else {
    		usuario.removePerfil(PerfilService.EMPRENDEDOR);
    	}
    	
    	usuarioService.save(usuario);
    	addUsuarioLogueado(model, usuario);
    	
    	return new ResponseEntity<>(Page.PORTAL.getFile(),HttpStatus.OK);
    	
    }
    
    private List<String> evaluarParametrosVacios(Map<String, String> parametrosNoNull) {
    	List<String> errores= new ArrayList<>();
    	for(String key: parametrosNoNull.keySet()) {
    		String val = parametrosNoNull.get(key);
    		if(val == null || val.trim().isEmpty()) {
    			errores.add("El campo "+key+" es obligatorio.");
    		}
    	}
    	return errores;
    }
    
    private String jsonify(List<String> datos) {
    	String json="{";
    	for(String dato: datos) {
    		json+="'"+dato+"',";
    	}
    	json=json.substring(0, json.length()-1); //saco la ultima coma
    	json+="}";
    	
    	return json;
    }
}