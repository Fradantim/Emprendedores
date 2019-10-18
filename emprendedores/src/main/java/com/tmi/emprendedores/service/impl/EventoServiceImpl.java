package com.tmi.emprendedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.dto.EventoDTO.TipoVisibilidad;
import com.tmi.emprendedores.persistence.entities.Evento;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.persistence.repository.EventoRepository;
import com.tmi.emprendedores.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
    private EventoRepository eventoRepo;

	@Override
	public Evento save(Evento evento) {
		return eventoRepo.save(evento);
	}

	@Override
	public Evento findById(Integer id) {
		if(id == null) return null;
		return eventoRepo.findByIdAndBorrado(id, false);
	}

	@Override
	public List<Evento> findPublicos(int index, int pageSize){
		return eventoRepo.findByTipoVisibilidadAndBorradoOrderByFechaDesc(TipoVisibilidad.PUBLICA, false, PageRequest.of(index, pageSize));
	}
	
	@Override
	public List<Evento> findPublicos() {
		return findPublicos(0, MAX_PAGE_SIZE);
	}

	@Override
	public List<Evento> getByYearAndMonth(int year, int month) {
		return eventoRepo.getByYearAndMonth(year, month);
	}

	@Override
	public List<Evento> getByYearAndMonthAndDay(int year, int month, int day) {
		return eventoRepo.getByYearAndMonthAndDay(year, month, day);
	}

	@Override
	public List<Evento> findByCreador(Usuario creador, int index, int pageSize) {
		return eventoRepo.findByCreadorAndBorradoOrderByFechaDesc(creador, false, PageRequest.of(index, pageSize));
	}
}