package com.tmi.emprendedores.controller.view;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.tmi.emprendedores.persistence.entities.Evento;
import com.tmi.emprendedores.persistence.entities.Perfil;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.service.EventoService;
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
    private EventoService eventoService;
    
    @Autowired
    private UsuarioValidator usuarioValidator;
    
    @GetMapping({WebUtils.MAPPING_ROOT, WebUtils.MAPPING_PORTAL})
    public String welcome(Model model, Principal principal) {
    	return super.welcome(model, principal);
    }
    
    @GetMapping(WebUtils.MAPPING_REGISTRO)
    public String goToRegistration(Model model) {
        model.addAttribute("userForm", new Usuario());
        return Page.REGISTRIO.getFile();
    }
    
    @PostMapping(WebUtils.MAPPING_REGISTRO)
    public String registracion(Model model
    		, @ModelAttribute("userForm") Usuario userForm, Principal principal, BindingResult bindingResult
    		, @RequestParam(value = "aceptaTyCCheckBox", required = false) String aceptaTyCCheckBox) {
    	if(isUsuarioLogueado(principal)) {
    		return goToNoTienePermisoAcceso(model, principal);
    	}
        usuarioValidator.validateNew(userForm, bindingResult);

        List<MensajeDTO> errores = new ArrayList<>();
        if(aceptaTyCCheckBox == null) {
        	errores.add(new MensajeDTO(TipoMensaje.ERROR,"Debe aceptar los terminos y condiciones para crear su usuario."));
        }
        
        if (bindingResult.hasErrors() || errores.size()>0) {
        	addMensajes(model, errores);
        	return Page.REGISTRIO.getFile();
        }

        usuarioService.saveNew(userForm);

        securityService.autoLogin(userForm.getNick(), userForm.getPasswordConfirm());
        
        addMensajes(model, 
        		new MensajeDTO(TipoMensaje.SUCCESS, "Su nueva cuenta fue creada correctamente."),
        		new MensajeDTO(TipoMensaje.SUCCESS, "Bienvenido "+userForm.getNick()+"!"));
        return welcome(model, principal);
    }

    @GetMapping(WebUtils.MAPPING_LOGIN)
    public String login(Model model, Principal principal, String error, String logout) {
    	if (logout != null) {
        	model.addAttribute("message", "Cerró su sesión correctamente.");
        	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Cerro su sesion correctamente!"));
        	return welcome(model, principal);
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
    		if(userLogueado.poseePerfil(PerfilService.EMPRENDEDOR)) {
    			removerEmprendedor(userLogueado);
    		}
    		userLogueado.removePerfil(PerfilService.EMPRENDEDOR);
    	}
        
        if(localidadId != null) {
        	Localidad localidad = locService.findById(localidadId);
        	if (localidad == null) {
        		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR,"No se encontro una localidad con id "+localidadId));
        		return Page.MODIFICAR_PERFIL.getFile();
        	}
        	userLogueado.setLocalidad(localidad);
        } else {
        	userLogueado.setLocalidad(null);
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
    
    /**
     * Si un usuario deja de ser emprendedor se borran sus inscripciones a eventos que aun no hayan tenido lugar, y sus eventos que no hayan tenido lugar son borrados.
     */
    private void removerEmprendedor(Usuario usuario) {
		Set<Evento> inscripcionesARemover = new HashSet<Evento>();
    	for(Evento evento: usuario.getEventosInscriptos()) {
			if(!evento.isFinalizado()) {
				evento.removeEmprendedor(usuario);
				inscripcionesARemover.add(evento);
				eventoService.save(evento);
			}
		}
    	usuario.getEventosInscriptos().removeAll(inscripcionesARemover);
		
    	Set<Evento> creacionesABorrar = new HashSet<Evento>();
		for(Evento evento: usuario.getEventosCreados()) {
			if(!evento.isFinalizado()) {
				creacionesABorrar.add(evento);
			}
		}
		
		usuario.getEventosCreados().removeAll(creacionesABorrar);
		
		for(Evento evento: creacionesABorrar) {
			evento.empty();
			evento.setBorrado(true);
			eventoService.save(evento);
		}
	}
    
    @GetMapping(WebUtils.MAPPING_GET_EMPRENDEDORES_LISTADO)
    public String getListaEmprendedores(Model model, Principal principal) {
    	
    	List<Usuario> usr = usuarioService.getByPerfil(PerfilService.EMPRENDEDOR);
    	
    	model.addAttribute("usuarios", usr);
        return Page.LISTA_EMPRENDEDORES.getFile();
    }
    
    @GetMapping(WebUtils.MAPPING_DETALLE_EMPRENDEDOR)
	public String detalleEmprendedor(Model model, Principal principal
			, @RequestParam(value = "idEmprendedor", required = false) Integer idEmprendedor) {
    	Usuario usuario = usuarioService.findById(idEmprendedor);
    	
    	if(usuario == null) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No se encontro al Emprendedor con id:" + idEmprendedor));
    		return welcome(model, principal);
    	}    	
    	    	
    	model.addAttribute("usuarioEmprendedor", usuario);
    	
    	return Page.DETALLE_EMPRENDEDOR.getFile();
	}
    
    
}