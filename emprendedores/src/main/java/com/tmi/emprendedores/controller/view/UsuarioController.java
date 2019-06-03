package com.tmi.emprendedores.controller.view;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.dto.MensajeDTO;
import com.tmi.emprendedores.dto.MensajeDTO.TipoMensaje;
import com.tmi.emprendedores.persistence.entities.Perfil;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.service.LocalidadService;
import com.tmi.emprendedores.service.PerfilService;
import com.tmi.emprendedores.service.SecurityService;
import com.tmi.emprendedores.validator.UsuarioValidator;

@Controller
public class UsuarioController extends WebController{
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private LocalidadService locService;

    @Autowired
    private UsuarioValidator usuarioValidator;
    
    @GetMapping({WebUtils.MAPPING_ROOT, WebUtils.MAPPING_PORTAL})
    public String welcome(Model model) {
        return Page.PORTAL.getFile();
    }
    
    @GetMapping(WebUtils.MAPPING_REGISTRO)
    public String goToRegistration(Model model) {
        model.addAttribute("userForm", new Usuario());
        return Page.REGISTRIO.getFile();
    }
    
    @PostMapping(WebUtils.MAPPING_REGISTRO)
    public String registratiocn(Model model, @ModelAttribute("userForm") Usuario userForm, Principal principal, BindingResult bindingResult) {
    	if(isUsuarioLogueado(principal)) {
    		return goToNoTienePermisoAcceso(model).getFile();
    	}
        usuarioValidator.validateNew(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	return Page.REGISTRIO.getFile();
        }

        usuarioService.saveNew(userForm);

        securityService.autoLogin(userForm.getNick(), userForm.getPasswordConfirm());

        
        addMensajes(model, 
        		new MensajeDTO(TipoMensaje.SUCCESS, "Su nueva cuenta fue creada correctamente."),
        		new MensajeDTO(TipoMensaje.SUCCESS, "Bienvenido "+userForm.getNick()+"!"));
        return Page.PORTAL.getFile();
    }

    @GetMapping(WebUtils.MAPPING_LOGIN)
    public String login(Model model, String error, String logout) {
    	if (logout != null) {
        	model.addAttribute("message", "Cerró su sesión correctamente.");
        	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Cerro su sesion correctamente!"));
        	return Page.PORTAL.getFile();
        }   
        
        if (error != null)
            model.addAttribute("error", "Usuario o contraseña incorrectos.");

        return Page.LOGIN.getFile();
    }
    
    
    @GetMapping(WebUtils.MAPPING_MI_PERFIL)
    public String goToMiPerfil(Model model, Principal principal) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario usuerLogueado = getLoggedUser(principal);
    	addUsuarioLogueado(model, usuerLogueado);

        return Page.MI_PERFIL.getFile();
    }
    
    @GetMapping(WebUtils.MAPPING_MI_PERFIL2)
    public String goToMiPerfil2(Model model, Principal principal) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario usuerLogueado = getLoggedUser(principal);
    	addUsuarioLogueado(model, usuerLogueado);

        return Page.MI_PERFIL2.getFile();
    }
    
    @GetMapping(WebUtils.MAPPING_MODIFICAR_PERFIL)
    public String goToModificarPerfil(Model model, Principal principal) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	model.addAttribute("userForm", new Usuario());
    	addUsuarioLogueado(model, usuarioService.findByNick(principal.getName()));
        return Page.MODIFICAR_PERFIL.getFile();
    }
    
    @PostMapping(WebUtils.MAPPING_MODIFICAR_PERFIL)
    public String modificarPerfil(Model model, Principal principal, @ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult,
    		@RequestParam(value = "emprendedorCheckBox", required = false) String emprendedorCheckBox,
    		@RequestParam(value = "localidadId", required = false) Integer localidadId) {
    	
     	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario userLogueado = getLoggedUser(principal);
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
        
        if(localidadId != null) {
        	Localidad localidad = locService.findById(localidadId);
        	if (localidad == null) {
        		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR,"No se encontro una localidad con id "+localidadId));
        		return Page.MODIFICAR_PERFIL.getFile();
        	}
        	userLogueado.setLocalidad(localidad);
        }

        //persisto nuevo usuario
        userLogueado = usuarioService.save(userLogueado);
        addUsuarioLogueado(model, userLogueado);

        //actualizo permisos del user logueado por si cambio nick o roles
        Set<GrantedAuthority> nowAuthorities = userLogueado.getPerfiles().stream().map(Perfil::toGrantedAuthority).collect(Collectors.toSet());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userLogueado.getNick(), userLogueado.getPassword(), nowAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Su Perfil se modifico con exito!"));
        return Page.MI_PERFIL.getFile();
     }
    
    @GetMapping(WebUtils.MAPPING_MODIFICAR_CLAVE)
    public String goToModificarClave(Model model, Principal principal) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	model.addAttribute("userForm", new Usuario());
    	addUsuarioLogueado(model, principal);
        return Page.MODIFICAR_CLAVE.getFile();
    }
    
    @PostMapping(WebUtils.MAPPING_MODIFICAR_CLAVE)
    public String modificarClave(Model model, Principal principal, @ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	usuarioValidator.validateUpdatePassword(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	//si hubo errores vuelvo a la web de la que vine
        	return Page.MODIFICAR_CLAVE.getFile();
        }
    	
        
        Usuario userLogueado = getLoggedUser(principal);
        //si paso todas las validaciones actualizo el usuario
        userLogueado.setPassword(userForm.getPassword());
        
        //persisto nuevo usuario
        userLogueado = usuarioService.saveAndEncodePassword(userLogueado);
        addUsuarioLogueado(model, userLogueado);
        
        addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "La clave se modifico correctamente!"));
        return Page.MI_PERFIL.getFile();
     }
}