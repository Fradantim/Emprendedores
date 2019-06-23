package com.tmi.emprendedores.controller.view;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.dto.EventoDTO;
import com.tmi.emprendedores.dto.EventoDTO.TipoInscripcion;
import com.tmi.emprendedores.dto.EventoDTO.TipoVisibilidad;
import com.tmi.emprendedores.dto.MensajeDTO;
import com.tmi.emprendedores.dto.MensajeDTO.TipoMensaje;
import com.tmi.emprendedores.persistence.entities.Evento;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.service.EventoService;
import com.tmi.emprendedores.service.LocalidadService;
import com.tmi.emprendedores.service.PerfilService;
import com.tmi.emprendedores.util.DatePickerUtil;
import com.tmi.emprendedores.validator.EventoValidator;

@Controller
public class EventoController extends WebController {

	private final static String OBJETO = Evento.class.getSimpleName();
	
	public final static int FOTO_PORTADA_EVENTO_MAX_SIZE = 20*MB;
	
	@Autowired
	private EventoService eventoService;

	@Autowired
	private LocalidadService localidadService;
	
	@Autowired
	private EventoValidator eventoValidator;



	private String noPuedeInteractuarEventoFinalizado(Model model, Principal principal) {
		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No puede interactuar con un evento Finalizado."));
		return welcome(model, principal);
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
    	
    	addUsuarioLogueado(model, principal);//unicamente por su localidad
		model.addAttribute("eventoForm", new Evento());
		return Page.CREAR_EVENTO.getFile();
	}
	
	@PostMapping(WebUtils.MAPPING_CREAR_EVENTO)
	public String CrearEvento(Model model, Principal principal, @ModelAttribute("eventoForm") Evento eventoForm, BindingResult bindingResult,
			@RequestParam(value = "localidadId", required = false) Integer localidadId,
			@RequestParam(value = "fecha", required = false) String fecha,
			@RequestParam(value = "tipoInscripcion", required = false) String tipoInscripcion,
			@RequestParam(value = "tipoVisibilidad", required = false) String tipoVisibilidad,
			@RequestParam("foto") MultipartFile fotoFile) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	/*if(null ==null) {
    		System.out.println(fotoFile);
    		try {
				System.out.println(fotoFile.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO SACAME
			System.out.println();
			addMensajes(model, new MensajeDTO("NULL"));
			return welcome(model, principal);
		}*/
    	
    	Usuario usuarioLogueado = getLoggedUser(principal); 
    	if(!usuarioLogueado.poseePerfil(PerfilService.EMPRENDEDOR)) {
    		return goToNoTienePermisoAcceso(model, principal);
    	}
    	
		List<MensajeDTO> errores = new ArrayList<>();
		
		if(localidadId == null) {
			errores.add(new MensajeDTO(TipoMensaje.ERROR,"Debe ingresar una localidad."));
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
				Date fechaDate = DatePickerUtil.getDateformater().parse(fecha);
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
		eventoForm.setDescripcionLarga(amortiguarInputHTML(eventoForm.getDescripcionLarga()));
		if(eventoForm.getMapa()!=null)
			eventoForm.setMapa(amortiguarInputHTML(eventoForm.getMapa()));
		
		if(fotoFile!=null) {
			try {
				eventoForm.setFotoB64(fotoFile.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		eventoForm.setCreador(usuarioLogueado);
		eventoForm.addEmprendedor(usuarioLogueado);
		
		eventoForm = eventoService.save(eventoForm);
		
		addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Creo su evento con exito!"));
		
		if(eventoForm.getMapa()!=null && !eventoForm.getMapa().trim().isEmpty()) {
			return welcome(model, principal);
		} else {
			//TODO corregir este error, cuando vuelve el mapa me rompe el forward
			return detalleEvento(model, principal, eventoForm.getId());	
		}
	}

	@GetMapping(WebUtils.MAPPING_GET_EVENTOS_PUBLICOS)
	public String getEventosPublicos(Model model, Principal principal, @RequestParam(value = "jsp", required = false) String jsp) {
		List<Evento> eventos = eventoService.findPublicos();
		List<EventoDTO> dtos = eventos.stream().map(Evento::toMiniDTO).collect(Collectors.toList());
    	if(isUsuarioLogueado(principal)) {
    		Usuario usuarioLogueado = getLoggedUser(principal);
    		addUsuarioLogueado(model, principal);
    		for(Evento evento: eventos) {
    			EventoDTO dto = dtos.stream().filter(e -> e.getId().equals(evento.getId())).findFirst().get();
    			if(!evento.isFinalizado() && !evento.getCreador().equals(usuarioLogueado)) {
    				dto.setAsiste(evento.getAsistencia().contains(usuarioLogueado));;
    			} else {
    				dto.setAsiste(false);
    			}
    		}
    	}
    	
    	model.addAttribute("eventos", dtos);
    	return choice(jsp,Page.LISTA_EVENTOS.getFile());
	}
	
	@GetMapping(WebUtils.MAPPING_GET_EVENTOS_INSCRIPTOS)
	public String getEventosInscriptos(Model model, Principal principal, @RequestParam(value = "idUsuario", required = false) Integer idUsuario
			, @RequestParam(value = "jsp", required = false) String jsp) {
		Usuario usuario = usuarioService.findById(idUsuario);
		
		if(usuario == null) {
			addMensajes(model, new MensajeDTO(TipoMensaje.ERROR,"No se encontro "+Usuario.class.getSimpleName()+" con id:"+idUsuario));
			return null;
		}
		
		List<EventoDTO> eventos = usuario.getEventosInscriptos().stream().map(Evento::toMiniDTO).collect(Collectors.toList());
		
		Collections.sort(eventos,(e1,e2) -> e1.getFecha().compareTo(e2.getFecha()));
		
		model.addAttribute("eventos", eventos);
		return choice(jsp,Page.LISTA_EVENTOS.getFile());
	}
	
	@GetMapping(WebUtils.MAPPING_GET_EVENTOS_CREADOS)
	public String getEventosCreados(Model model, Principal principal,@RequestParam(value = "idUsuario", required = false) Integer idUsuario
			, @RequestParam(value = "jsp", required = false) String jsp) {
		Usuario usuario = usuarioService.findById(idUsuario);
		
		if(usuario == null) {
			addMensajes(model, new MensajeDTO(TipoMensaje.ERROR,"No se encontro "+Usuario.class.getSimpleName()+" con id:"+idUsuario));
			return null;
		}
		
		List<EventoDTO> eventos = usuario.getEventosCreados().stream().map(Evento::toMiniDTO).collect(Collectors.toList());
		
		Collections.sort(eventos,(e1,e2) -> e1.getFecha().compareTo(e2.getFecha()));
		
		model.addAttribute("eventos", eventos);
		return choice(jsp,Page.LISTA_EVENTOS.getFile());
	}
	
	@GetMapping(WebUtils.MAPPING_GET_EVENTOS_ASISTENCIA)
	public String getEventosAsustencia(Model model, Principal principal,@RequestParam(value = "idUsuario", required = false) Integer idUsuario
			, @RequestParam(value = "jsp", required = false) String jsp) {
		Usuario usuario = usuarioService.findById(idUsuario);
		
		if(usuario == null) {
			addMensajes(model, new MensajeDTO(TipoMensaje.ERROR,"No se encontro "+Usuario.class.getSimpleName()+" con id:"+idUsuario));
			return null;
		}
		
		addUsuarioLogueado(model, principal);
		List<EventoDTO> eventos = usuario.getEventosAsistencia().stream().map(Evento::toMiniDTO).collect(Collectors.toList());
		
		Collections.sort(eventos,(e1,e2) -> e1.getFecha().compareTo(e2.getFecha()));
		
		model.addAttribute("eventos", eventos);
		return choice(jsp,Page.LISTA_EVENTOS.getFile());
	}
	
	@GetMapping(WebUtils.MAPPING_BORRAR_EVENTO)
	public String borrarEvento(Model model, Principal principal, @RequestParam(value = "idEvento", required = false) Integer idEvento) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		return goToNoSeEncontroObjeto(model, principal, OBJETO, idEvento);
    	}  
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model,principal);
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
		
		if(!getLoggedUser(principal).poseePerfil(PerfilService.EMPRENDEDOR)) {
    		return goToNoTienePermisoAcceso(model, principal);
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No se encontro un Evento con id:"+idEvento));
    		return welcome(model, principal);
    	}  
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model, principal);
    	}
    	
    	if(!getLoggedUser(principal).puedeEditar(eventoGuardado)) {
    		return goToNoTienePermisoEdicion(model, principal);
    	}
    	
    	model.addAttribute("tiposInscripcion", TipoInscripcion.values());
    	model.addAttribute("tiposVisibilidad", TipoVisibilidad.values());
    	
		model.addAttribute("eventoForm", new Evento());
		
		//&le;
		if(eventoGuardado.getMapa()!= null)
			eventoGuardado.setMapa(eventoGuardado.getMapa().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;"));
		model.addAttribute("eventoGuardado", eventoGuardado.toDTO());
		
		System.out.println("Map: "+eventoGuardado.getMapa());
		
		return Page.MODIFICAR_EVENTO.getFile();
	}
	
	@PostMapping(WebUtils.MAPPING_MODIFICAR_EVENTO)
	public String modificarEvento(Model model, Principal principal,
			@RequestParam(value = "idEvento", required = false) Integer idEvento,
			@ModelAttribute("eventoForm") Evento eventoForm, BindingResult bindingResult,
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
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	
    	if(eventoGuardado==null) {
    		return goToNoSeEncontroObjeto(model, principal, OBJETO, idEvento);
    	}
    	
    	if(!usuarioLogueado.puedeEditar(eventoGuardado)) {
    		return goToNoTienePermisoEdicion(model, principal);
    	}
    	
		List<MensajeDTO> errores = new ArrayList<>();
		
		if(localidadId == null) {
			errores.add(new MensajeDTO(TipoMensaje.ERROR,"Debe ingresar una localidad"));
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
				Date fechaDate = DatePickerUtil.getDateformater().parse(fecha);
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
			return Page.MODIFICAR_EVENTO.getFile();
		}
		
		//si todo sale ok....

		eventoForm.setDescripcionLarga(amortiguarInputHTML(eventoForm.getDescripcionLarga()));
		if(eventoForm.getMapa()!=null)
			eventoForm.setMapa(amortiguarInputHTML(eventoForm.getMapa()));
		eventoGuardado.modificarEvento(eventoForm);
		
		eventoGuardado = eventoService.save(eventoGuardado);
		
		addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Modifico su evento con exito!"));
		System.out.println("!!!!!!! "+eventoForm.getMapa());
		if(eventoForm.getMapa()!=null && !eventoForm.getMapa().trim().isEmpty()) {
			return welcome(model, principal);
		} else {
			//TODO corregir este error, cuando vuelve el mapa me rompe el forward
			return detalleEvento(model, principal, eventoGuardado.getId());	
		}
	}
	
	@GetMapping(WebUtils.MAPPING_INSCRIBIRME_EVENTO)
	public String inscribirmeEvento(Model model, Principal principal, @RequestParam(value = "idEvento", required = false) Integer idEvento
			, @RequestParam(value = "jsp", required = false) String jsp) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario usuarioLogueado = getLoggedUser(principal);
    	if(!usuarioLogueado.poseePerfil(PerfilService.EMPRENDEDOR)) {
    		return goToNoTienePermisoAcceso(model, principal);
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		return goToNoSeEncontroObjeto(model, principal, OBJETO, idEvento);
    	}
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model,principal);
    	}
    	
    	if(eventoGuardado.getEmprendedores().contains(usuarioLogueado)) {
    		addMensajes(model, new MensajeDTO("Ud ya se encuentra inscripto a este evento."));
    		return welcome(model, principal);
    	}
    	
    	if(eventoGuardado.getTipoInscripcion().equals(TipoInscripcion.CERRADA)) {
    		addMensajes(model, new MensajeDTO("No puede inscribirse a un evento con inscripcion cerrada."));
    		return welcome(model, principal);
    	}
    	
    	eventoGuardado.addEmprendedor(usuarioLogueado);

    	eventoService.save(eventoGuardado);
    	
    	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Se inscribio al evento con exito!"));
    	return choice(jsp,welcome(model, principal));
	}
	
	@GetMapping(WebUtils.MAPPING_DESINSCRIBIRME_EVENTO)
	public String desinscribirmeEvento(Model model, Principal principal
			, @RequestParam(value = "idEvento", required = false) Integer idEvento
			, @RequestParam(value = "jsp", required = false) String jsp) {
    	if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario usuarioLogueado = getLoggedUser(principal);
    	if(!usuarioLogueado.poseePerfil(PerfilService.EMPRENDEDOR)) {
    		return goToNoTienePermisoAcceso(model, principal);
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		return goToNoSeEncontroObjeto(model, principal, OBJETO, idEvento);
    	}
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model,principal);
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
    	return choice(jsp,welcome(model, principal));
	}
	
	@GetMapping(WebUtils.MAPPING_DETALLE_EVENTO)
	public String detalleEvento(Model model, Principal principal
			, @RequestParam(value = "idEvento", required = false) Integer idEvento) {
    	Evento evento = eventoService.findById(idEvento);
    	
    	if(evento==null) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No se encontro un Evento con id:"+idEvento));
    		return welcome(model, principal);
    	}
    	
    	EventoDTO dto = evento.toDTO();
    	
    	if(isUsuarioLogueado(principal)) {
    		Usuario usuarioLogueado = getLoggedUser(principal);
        	
        	if(!evento.isFinalizado() && !evento.getCreador().equals(usuarioLogueado)) {
        		dto.setAsiste(evento.getAsistencia().contains(usuarioLogueado));
    		} else {
    			dto.setAsiste(false);
    		}
        	
        	if(evento.isAbierto() && !evento.isFinalizado() && !evento.getCreador().equals(usuarioLogueado)) {
        		dto.setInscripto(evento.getEmprendedores().contains(usuarioLogueado));
        	} else {
        		dto.setInscripto(false);
        	}
        	addUsuarioLogueado(model, usuarioLogueado);
    	}
    	
    	model.addAttribute("evento", evento.toDTO());
    	
    	return Page.DETALLE_EVENTO.getFile();
	}

	
	@GetMapping(WebUtils.MAPPING_ASISTIR_EVENTO)
	public String asistirEvento(Model model, Principal principal, @RequestParam(value = "idEvento", required = false) Integer idEvento
			, @RequestParam(value = "idUsuario", required = false) Integer idUsuario
			, @RequestParam(value = "jsp", required = false) String jsp) {
    	
		if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario usuarioLogueado = getLoggedUser(principal);
    	
    	Usuario usuarioQueAsistira = usuarioService.findById(idUsuario);
    	if(usuarioQueAsistira==null) {
    		return goToNoSeEncontroObjeto(model, principal, Usuario.class.getSimpleName(), idUsuario);
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		return goToNoSeEncontroObjeto(model, principal, OBJETO, idEvento);
    	}
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model,principal);
    	}
    	
    	if(!usuarioLogueado.puedeEditar(usuarioQueAsistira)) {
    		return goToNoTienePermisoEdicion(model, principal);
    	}
    	
    	eventoGuardado.addAsistencia(usuarioQueAsistira);

    	eventoService.save(eventoGuardado);
    	
    	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Se inscribio al evento con exito!"));
    	return choice(jsp, welcome(model, principal));
	}
	
	@GetMapping(WebUtils.MAPPING_DESASISTIR_EVENTO)
	public String desasistirEvento(Model model, Principal principal, @RequestParam(value = "idEvento", required = false) Integer idEvento
			, @RequestParam(value = "idUsuario", required = false) Integer idUsuario
			, @RequestParam(value = "jsp", required = false) String jsp) {
		
		if(!isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	Usuario usuarioLogueado = getLoggedUser(principal);
    	
    	Usuario usuarioQueNoAsistira = usuarioService.findById(idUsuario);
    	if(usuarioQueNoAsistira==null) {
    		return goToNoSeEncontroObjeto(model, principal, Usuario.class.getSimpleName(), idUsuario);
    	}
    	
    	Evento eventoGuardado = eventoService.findById(idEvento);
    	if(eventoGuardado==null) {
    		return goToNoSeEncontroObjeto(model, principal, OBJETO, idEvento);
    	}
    	
    	if(eventoGuardado.isFinalizado()) {
    		return noPuedeInteractuarEventoFinalizado(model,principal);
    	}
    	
    	if(!usuarioLogueado.puedeEditar(usuarioQueNoAsistira)) {
    		return goToNoTienePermisoEdicion(model, principal);
    	}
    	
    	eventoGuardado.removeAsistencia(usuarioQueNoAsistira);

    	eventoService.save(eventoGuardado);
    	
    	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Se desinscribio al evento con exito!"));
    	return choice(jsp, welcome(model, principal));
	}

	@RequestMapping(value = "/"+WebUtils.MAPPING_GET_EVENTOS_CALENDARIO, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getEventosMensuales(
			@RequestParam(value = "year", required = false) String year
			, @RequestParam(value = "month", required = false) String month) {
		
		List<MensajeDTO> errores = new ArrayList<>();
		if(year== null) {
			errores.add(new MensajeDTO(TipoMensaje.ERROR, "El año debe ser informado."));
		}
		if(month== null) {
			errores.add(new MensajeDTO(TipoMensaje.ERROR, "El mes debe ser informado."));
		}
		
		Integer intYear = null;
		Integer intMonth = null;
		if(errores.isEmpty()) {
			try {
				intYear = Integer.parseInt(year);
			} catch (NumberFormatException e) {
				errores.add(new MensajeDTO(TipoMensaje.ERROR, "No se pudo interpretar el año como un numero."));
			}
			
			try {
				intMonth = Integer.parseInt(month);
			} catch (NumberFormatException e) {
				errores.add(new MensajeDTO(TipoMensaje.ERROR, "No se pudo interpretar el mes como un numero."));
			}
		}
		
		if(!errores.isEmpty()) {
			//addMensajes(model, errores);
			//return welcome(model, principal);
			//TODO retornar codigo 400
		}
		
		List<Evento> eventos= eventoService.getByYearAndMonth(intYear.intValue(), intMonth.intValue());
		
		Set<String> fechas = new HashSet<>();
		
		for(Evento evento: eventos) {
			fechas.add(evento.toMiniDTO().getDatePickerFormatedDate());
		}
		
		return "["+ fechas.stream().collect( Collectors.joining( "," ) )+ "]";
	}
	
	@GetMapping(WebUtils.MAPPING_GET_EVENTOS_FILTRADOS)
	public String getEventosFiltrados(Model model, Principal principal
			, @RequestParam(value = "jsp", required = false) String jsp
			, @RequestParam(value = "year", required = false) String year
			, @RequestParam(value = "month", required = false) String month
			, @RequestParam(value = "day", required = false) String day) {
		
		List<Evento> eventos = eventoService.getByYearAndMonthAndDay(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		List<EventoDTO> dtos = eventos.stream().map(Evento::toMiniDTO).collect(Collectors.toList());
    	if(isUsuarioLogueado(principal)) {
    		Usuario usuarioLogueado = getLoggedUser(principal);
    		addUsuarioLogueado(model, principal);
    		for(Evento evento: eventos) {
    			EventoDTO dto = dtos.stream().filter(e -> e.getId().equals(evento.getId())).findFirst().get();
    			if(!evento.isFinalizado() && !evento.getCreador().equals(usuarioLogueado)) {
    				dto.setAsiste(evento.getAsistencia().contains(usuarioLogueado));;
    			} else {
    				dto.setAsiste(false);
    			}
    		}
    	}
    	
    	model.addAttribute("eventos", dtos);
    	return choice(jsp,Page.LISTA_EVENTOS.getFile());
	}	
}