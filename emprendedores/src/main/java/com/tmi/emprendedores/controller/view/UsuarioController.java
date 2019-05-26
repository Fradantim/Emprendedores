package com.tmi.emprendedores.controller.view;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuarioController extends WebController{
    
	@Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UsuarioValidator usuarioValidator;
    
    @GetMapping({WebUtils.MAPPING_ROOT, WebUtils.MAPPING_PORTAL})
    public String welcome(Model model) {
        return Page.PORTAL.getFile();
    }

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
    	addUsuarioLogueado(model, usuarioService.findByNick(principal.getName()));
    }
    
    private void addUsuarioLogueado(Model model, Usuario usuario) {
    	model.addAttribute("usuarioLogueado", usuario.toDTO());
    }
    
    @GetMapping(WebUtils.MAPPING_REGISTRO)
    public String goToRegistration(Model model) {
        model.addAttribute("userForm", new Usuario());
        return Page.REGISTRIO.getFile();
    }
    
    @PostMapping(WebUtils.MAPPING_REGISTRO)
    public String registratiocn(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult) {
        usuarioValidator.validateNew(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	return Page.REGISTRIO.getFile();
        }

        usuarioService.saveNew(userForm);

        securityService.autoLogin(userForm.getNick(), userForm.getPasswordConfirm());

        return Page.PORTAL.redirect();
    }

    @GetMapping(WebUtils.MAPPING_LOGIN)
    public String login(Model model, String error, String logout) {
    	if (logout != null) {
        	model.addAttribute("message", "Cerró su sesión correctamente.");
        	return Page.PORTAL.getFile();
        }   
        
        if (error != null)
            model.addAttribute("error", "Usuario o contraseña incorrectos.");

        return Page.LOGIN.getFile();
    }
    
    
    @GetMapping(WebUtils.MAPPING_MI_PERFIL)
    public String goToMiPerfil(Model model, Principal principal) {
    	Usuario usuerLogueado = usuarioService.findByNick(principal.getName());
    	addUsuarioLogueado(model, usuerLogueado);

        return Page.MI_PERFIL.getFile();
    }
    
    @GetMapping(WebUtils.MAPPING_MODIFICAR_PERFIL)
    public String goToModificarPerfil(Model model, Principal principal) {
    	model.addAttribute("userForm", new Usuario());
    	addUsuarioLogueado(model, usuarioService.findByNick(principal.getName()));
        return Page.MODIFICAR_PERFIL.getFile();
    }
    
    @PostMapping(WebUtils.MAPPING_MODIFICAR_PERFIL)
    public String modificarPerfil(Model model, Principal principal, @ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult, @RequestParam(value = "emprendedorCheckBox", required = false) String emprendedorCheckBox) {
    	Usuario userLogueado = usuarioService.findByNick(principal.getName());
    	usuarioValidator.validateUpdate(userLogueado, bindingResult, userForm);

        if (bindingResult.hasErrors()) {
        	//si hubo errores vuelvo a la web de la que vine
        	return Page.MODIFICAR_PERFIL.getFile();
        }
    	
        //si paso todas las validaciones actualizo el usuario
        userLogueado.modificarPerfil(userForm);
        
        if(emprendedorCheckBox != null ) {
        	userLogueado.addPerfil(PerfilService.EMPRENDEDOR);
    	} else {
    		userLogueado.removePerfil(PerfilService.EMPRENDEDOR);
    	}
        
        userLogueado = usuarioService.save(userLogueado);
        addUsuarioLogueado(model, userLogueado);
        
        //TODO hacer autologin?
        securityService.autoLogin(userLogueado.getNick(), userLogueado.getPassword());
    	return Page.MI_PERFIL.getFile();
     }
}