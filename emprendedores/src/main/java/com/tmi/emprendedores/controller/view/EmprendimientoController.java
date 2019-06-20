package com.tmi.emprendedores.controller.view;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.dto.EventoDTO;
import com.tmi.emprendedores.dto.MensajeDTO;
import com.tmi.emprendedores.dto.MensajeDTO.TipoMensaje;
import com.tmi.emprendedores.persistence.entities.Emprendimiento;
import com.tmi.emprendedores.persistence.entities.Evento;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.service.EmprendimientoService;
import com.tmi.emprendedores.validator.EmprendimientoValidator;

@Controller
public class EmprendimientoController extends WebController {

	@Autowired
	private EmprendimientoService emprendimientoService;

	@Autowired
	private EmprendimientoValidator emprendimientoValidator;

	@GetMapping(WebUtils.MAPPING_MODIFICAR_EMPRENDIMIENTO)
	public String goToModificarEmprendimiento(Model model, Principal principal, @RequestParam(value = "idEmprendimiento", required = false) Integer idEmprendimiento) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
		Emprendimiento emprendimiento = emprendimientoService.findById(idEmprendimiento);

		if (emprendimiento == null) {
			emprendimiento = new Emprendimiento();
		} else {
			//el emprendimiento existe, evaluo si el usuario logueado tiene permisos para editarlo
			if(!getLoggedUser(principal).puedeEditar(emprendimiento)) {
				return goToNoTienePermisoEdicion(model, principal);
			}
		}

		model.addAttribute("emprendimiento", emprendimiento.toMiniDTO());
		model.addAttribute("emprendimientoForm", new Emprendimiento());
		return Page.MODIFICAR_EMPRENDIMIENTO.getFile();
	}

	@PostMapping(WebUtils.MAPPING_MODIFICAR_EMPRENDIMIENTO)
	public String modificarEmprendimiento(Model model, Principal principal, @ModelAttribute("emprendimientoForm") Emprendimiento emprendimientoForm, BindingResult bindingResult) {
		if(!isUsuarioLogueado(principal)) {
			return goToDebeIniciarSesion(model).getFile();
    	}
		emprendimientoValidator.validateUpdate(emprendimientoForm, bindingResult);

		if (bindingResult.hasErrors()) {
			// si hubo errores vuelvo a la web de la que vine
			return Page.MODIFICAR_EMPRENDIMIENTO.getFile();
		}
		
		emprendimientoForm.setDescripcion(amortiguarInputHTML(emprendimientoForm.getDescripcion()));
		
		Emprendimiento emprendimientoAPersistir = null;
		if(emprendimientoForm.getId() == null) {
			//es un emprendimiento nuevo
			emprendimientoAPersistir = emprendimientoForm;
			Usuario usuarioLogueado = getLoggedUser(principal);
			usuarioLogueado.setEmprendimiento(emprendimientoAPersistir); //actualizo la relacion al emprendimiento
			usuarioService.save(usuarioLogueado); 
			emprendimientoAPersistir.setUsuario(usuarioLogueado); //actualizo la relacion al usuario creador
		} else {
			//es un emprendimiento existente
			emprendimientoAPersistir = emprendimientoService.findById(emprendimientoForm.getId());
			if(!getLoggedUser(principal).puedeEditar(emprendimientoAPersistir)) {
				return goToNoTienePermisoEdicion(model, principal);
			}
			emprendimientoAPersistir.modificarEmprendimiento(emprendimientoForm);
		}
		
		emprendimientoAPersistir = emprendimientoService.save(emprendimientoAPersistir);
		
		//actualizo la info del usuario atada al emprendimiento
		addUsuarioLogueado(model, principal);
		addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Actualizo correctamente su emprendimiento!"));
		return Page.MI_PERFIL.getFile();
	}	
}