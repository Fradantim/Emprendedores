package com.tmi.emprendedores.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmi.emprendedores.dto.EventoDTO;
import com.tmi.emprendedores.exception.ObjetoInexistenteException;
import com.tmi.emprendedores.persistence.entities.Evento;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.service.EventoService;
import com.tmi.emprendedores.service.Service;
import com.tmi.emprendedores.service.UsuarioService;

@RestController
public class EventoRestController {

	private static final String PATH="/api/eventos";
	
	@Autowired
	private EventoService eventoSvc;
	
	@Autowired
	private UsuarioService usuarioSvc;

	@GetMapping(PATH)
	public List<EventoDTO> all(
			@RequestParam(defaultValue = "false") Boolean includePic,
			@RequestParam(defaultValue = "0") Integer index,
			@RequestParam(defaultValue = Service.MAX_PAGE_SIZE_STR) Integer pageSize,
			@RequestParam(defaultValue = "") String nick
			) {
		
		String erroresEnArgumentos ="";
		if(index < 0) {
			erroresEnArgumentos+="El index no puede ser menor a 0. ";
		}
		
		if(pageSize < 1) {
			erroresEnArgumentos+="El pageSize no puede ser menor a 1. ";
		}
		
		if(erroresEnArgumentos.length()>0) {
			throw new IllegalArgumentException(erroresEnArgumentos);
		}
		
		
		List<Evento> eventos;
		
		if(nick.length()>0) {
			Usuario creador = usuarioSvc.findByNick(nick);
			if (creador == null) {
				throw new ObjetoInexistenteException("No se encontro un "+Usuario.class.getSimpleName()+" con nick '"+nick+"'" );
			}
			eventos= eventoSvc.findByCreador(creador, index, pageSize);
		} else {
			eventos= eventoSvc.findPublicos(index,pageSize);
		}
		
		List<EventoDTO> eventosDTO = eventos.stream().map(Evento::toMiniDTO).collect(Collectors.toList());
		
		for(EventoDTO evento: eventosDTO){
			if(!includePic) {
				evento.setFotoB64("");
			}
			
		}
		
		return eventosDTO;
	}
	
	@GetMapping(PATH+"/{id}")
	  EventoDTO one(
			  @PathVariable Integer id,
			  @RequestParam(defaultValue = "false") Boolean includePic
			  ) {
		Evento evento = eventoSvc.findById(id);
		if(evento == null) {
			throw new ObjetoInexistenteException(Evento.class, id);
		}
	    
		if(!includePic) {
			evento.setFotoB64("");
		}
		
		return evento.toDTO();
	  }
}
