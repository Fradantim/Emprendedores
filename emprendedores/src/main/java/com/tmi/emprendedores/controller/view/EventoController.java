package com.tmi.emprendedores.controller.view;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.dto.MensajeDTO;
import com.tmi.emprendedores.dto.EventoDTO.TipoInscripcion;
import com.tmi.emprendedores.dto.EventoDTO.TipoVisibilidad;
import com.tmi.emprendedores.dto.MensajeDTO.TipoMensaje;
import com.tmi.emprendedores.persistence.entities.Evento;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.service.EventoService;
import com.tmi.emprendedores.service.LocalidadService;
import com.tmi.emprendedores.service.PerfilService;
import com.tmi.emprendedores.validator.EventoValidator;

@Controller
public class EventoController extends WebController {

	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
	
	private final SimpleDateFormat dateFormater =new SimpleDateFormat(DATE_FORMAT);
	
	@Autowired
	private EventoService eventoService;

	@Autowired
	private LocalidadService localidadService;
	
	@Autowired
	private EventoValidator eventoValidator;

	private Page noPuedeInteractuarEventoFinalizado(Model model) {
		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No puede interactuar con un evento Finalizado."));
		return Page.MIS_EVENTOS;
	}
	
	
	@GetMapping(WebUtils.MAPPING_MIS_EVENTOS)
	public String goToMisEventos(Model model, Principal principal) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	
    	if(isUsuarioLogueado(principal)) {
    		//Usuario usuarioLogueado = getLoggedUser(principal);
    		//TODO evaluar si el usuario logueado ya esta inscripto!
    	}
		//TODO agregar eventos
    	
    	
		return Page.MIS_EVENTOS.getFile();
	}
	
	@GetMapping(WebUtils.MAPPING_CREAR_EVENTO)
	public String goToCrearEvento(Model model, Principal principal) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	if(!getLoggedUser(principal).poseePerfil(PerfilService.EMPRENDEDOR)) {
    		return goToNoTienePermisoAcceso(model, principal);
    	}
    	
    	model.addAttribute("tiposInscripcion", TipoInscripcion.values());
    	model.addAttribute("tiposVisibilidad", TipoVisibilidad.values());
    	
		model.addAttribute("eventoForm", new Evento());
		return Page.CREAR_EVENTO.getFile();
	}
	
	@PostMapping(WebUtils.MAPPING_CREAR_EVENTO)
	public String CrearEvento(Model model, Principal principal, @ModelAttribute("eventoForm") Evento eventoForm, BindingResult bindingResult,
			@RequestParam(value = "localidadId", required = false) Integer localidadId,
			@RequestParam(value = "fecha", required = false) String fecha,
			@RequestParam(value = "tipoInscripcion", required = false) String tipoInscripcion,
			@RequestParam(value = "tipoVisibilidad", required = false) String tipoVisibilidad) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario usuarioLogueado = getLoggedUser(principal); 
    	if(!usuarioLogueado.poseePerfil(PerfilService.EMPRENDEDOR)) {
    		return goToNoTienePermisoAcceso(model, principal);
    	}
    	
		List<MensajeDTO> errores = new ArrayList<>();
		
		if(localidadId == null) {
			errores.add(new MensajeDTO(TipoMensaje.ERROR,"No se encontro una localidad con id:"+localidadId));
		} else {
			Localidad localidad = localidadService.findById(localidadId);
			if (localidad == null) {
				errores.add(new MensajeDTO(TipoMensaje.ERROR,"No se encontro una localidad con id:"+localidadId));
			} else {
				eventoForm.setLocalidad(localidad);
			}
		}
		
		if(fecha == null) {
			errores.add(new MensajeDTO(TipoMensaje.ERROR,"Debe ingresar una fecha."));
		} else {
			try {
				Date fechaDate = dateFormater.parse(fecha);
				if(new Date().compareTo(fechaDate)>0) {
					errores.add(new MensajeDTO(TipoMensaje.ERROR,"Great Scott! No puede ingresar una fecha del pasado."));
				} else {
					eventoForm.setFecha(fechaDate);
				}
			} catch (ParseException e) {
				errores.add(new MensajeDTO(TipoMensaje.ERROR,"No se pudo interpretar la fecha:"+fecha));
			}
		}
		
		if(tipoInscripcion == null) {
			errores.add(new MensajeDTO(TipoMensaje.ERROR,"Debe ingresar un tipo de inscripcion."));
		} else {
			try {
				eventoForm.setTipoInscripcion(tipoInscripcion);
			} catch (IllegalArgumentException e) {
				errores.add(new MensajeDTO(TipoMensaje.ERROR,"No se conoce un tipo de inscripcion:"+tipoInscripcion));
			}
		}
		
		if(tipoVisibilidad == null) {
			errores.add(new MensajeDTO(TipoMensaje.ERROR,"Debe ingresar un tipo de visibilidad."));
		} else {
			try {
				eventoForm.setTipoVisibilidad(tipoVisibilidad);
			} catch (IllegalArgumentException e) {
				errores.add(new MensajeDTO(TipoMensaje.ERROR,"No se conoce un tipo de visibilidad:"+tipoVisibilidad));
			}
		}
		
		eventoValidator.validateInsert(eventoForm, bindingResult);
		if (bindingResult.hasErrors() || errores.size()>0) {
			// si hubo errores vuelvo a la web de la que vine
			addMensajes(model, errores);
			return Page.CREAR_EVENTO.getFile();
		}
		
		
		//si todo sale ok....
		eventoForm.setCreador(usuarioLogueado);
		eventoForm.addEmprendedor(usuarioLogueado);
		
		eventoService.save(eventoForm);
		
		addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Creo su evento con exito!"));
		return welcome(model, principal);
	}
	
	@GetMapping(WebUtils.MAPPING_BORRAR_EVENTO)
	public String goToBorrarEvento(Model model, Principal principal, @RequestParam(value = "idEvento", required = false) Integer idEvento) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No se encontro un Evento con id:"+idEvento));
    		return welcome(model, principal);
    	}  
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model).getFile();
    	}
    	
    	if(!getLoggedUser(principal).puedeEditar(eventoGuardado)) {
    		return goToNoTienePermisoEdicion(model, principal);
    	}

    	eventoGuardado.setBorrado(true);
    	eventoService.save(eventoGuardado);
    	
    	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Borro su evento con exito."));
    	return welcome(model, principal);
	}
	
	@GetMapping(WebUtils.MAPPING_MODIFICAR_EVENTO)
	public String goToModificarEvento(Model model, Principal principal,@RequestParam(value = "idEvento", required = false) Integer idEvento) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No se encontro un Evento con id:"+idEvento));
    		return Page.MIS_EVENTOS.getFile();
    	}  
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model).getFile();
    	}
    	
    	if(!getLoggedUser(principal).puedeEditar(eventoGuardado)) {
    		return goToNoTienePermisoEdicion(model, principal);
    	}
    	
		model.addAttribute("eventoForm", new Evento());
		model.addAttribute("eventoGuardado", eventoGuardado);
		
		return Page.MODIFICAR_EVENTO.getFile();
	}

	@PostMapping(WebUtils.MAPPING_MODIFICAR_EVENTO)
	public String modificarEvento(Model model, Principal principal, @ModelAttribute("eventoForm") Evento eventoForm, BindingResult bindingResult) {
		if(!isUsuarioLogueado(principal)) {
			return goToDebeIniciarSesion(model).getFile();
    	}
		
		eventoValidator.validateUpdate(eventoForm, bindingResult);

		if (bindingResult.hasErrors()) {
			// si hubo errores vuelvo a la web de la que vine
			return Page.MODIFICAR_EVENTO.getFile();
		}
		/* TODO HACEME
		
		if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model).getFile();
    	}
		String descripcion = emprendimientoForm.getDescripcion().replace("\r", "").replace("\n", "").trim();;
		emprendimientoForm.setDescripcion(descripcion);
		
		
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
			// TODO evaluar si el usuario logueado esta queriendo editar su propio emprendimiento u otro? -> vulnerabilidad
			//emprendimientoAPersistir = emprendimientoService.findById(emprendimientoForm.getId());
			emprendimientoAPersistir.modificarEmprendimiento(emprendimientoForm);
		}
		
		//emprendimientoAPersistir = emprendimientoService.save(emprendimientoAPersistir);
		
		//actualizo la info del usuario atada al emprendimiento
		addUsuarioLogueado(model, principal);
		addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Actualizo correctamente su emprendimiento!"));
		*/
		return goToMisEventos(model, principal);
	}
	
	@GetMapping(WebUtils.MAPPING_INSCRIBIRME_EVENTO)
	public String inscribirmeEvento(Model model, Principal principal, @RequestParam(value = "idEvento", required = false) Integer idEvento) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario usuarioLogueado = getLoggedUser(principal);
    	if(!usuarioLogueado.poseePerfil(PerfilService.EMPRENDEDOR)) {
    		return goToNoTienePermisoAcceso(model, principal);
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No se encontro un Evento con id:"+idEvento));
    		return Page.MIS_EVENTOS.getFile();
    	}
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model).getFile();
    	}
    	
    	if(eventoGuardado.getEmprendedores().contains(usuarioLogueado)) {
    		addMensajes(model, new MensajeDTO("Ud ya se encuentra inscripto a este evento."));
    		return Page.MIS_EVENTOS.getFile();
    	}
    	
    	eventoGuardado.addEmprendedor(usuarioLogueado);

    	eventoService.save(eventoGuardado);
    	
    	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Se inscribio al evento con exito!"));
    	return welcome(model, principal);
	}
	
	@GetMapping(WebUtils.MAPPING_DESINSCRIBIRME_EVENTO)
	public String desinscribirmeEvento(Model model, Principal principal, @RequestParam(value = "idEvento", required = false) Integer idEvento) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario usuarioLogueado = getLoggedUser(principal);
    	if(!usuarioLogueado.poseePerfil(PerfilService.EMPRENDEDOR)) {
    		return goToNoTienePermisoAcceso(model, principal);
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No se encontro un Evento con id:"+idEvento));
    		return welcome(model, principal);
    	}
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model).getFile();
    	}
    	
    	if(!eventoGuardado.getEmprendedores().contains(usuarioLogueado)) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR,"Ud no se encuentra inscripto a este evento."));
    		return welcome(model, principal);
    	}
    	
    	if(eventoGuardado.getCreador().equals(usuarioLogueado)) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR,"Ud no puede desincribirse a su propio evento."));
    		return welcome(model, principal);
    	}
    	   	
    	eventoGuardado.removeEmprendedor(usuarioLogueado);

    	eventoService.save(eventoGuardado);
    	
    	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Se desinscribio al evento con exito!"));
    	return welcome(model, principal);
	}
	
}