package com.tmi.emprendedores.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.persistence.entities.Usuario;
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
    
    @GetMapping(WebUtils.MAPPING_REGISTRO)
    public String registration(Model model) {
        model.addAttribute("userForm", new Usuario());
        return Page.REGISTRIO.getFile();
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
}