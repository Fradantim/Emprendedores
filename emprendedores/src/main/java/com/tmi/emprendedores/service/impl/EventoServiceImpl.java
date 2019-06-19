package com.tmi.emprendedores.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.dto.EventoDTO.TipoVisibilidad;
import com.tmi.emprendedores.persistence.entities.Evento;
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
	public List<Evento> findPublicos() {
		return eventoRepo.findByTipoVisibilidadAndBorradoOrderByFechaDesc(TipoVisibilidad.PUBLICA, false);
	}

	@Override
	public List<Evento> getByYearAndMonth(int year, int month) {
		return eventoRepo.getByYearAndMonth(year, month);
	}

	@Override
	public List<Evento> getByYearAndMonthAndDay(int year, int month, int day) {
		return eventoRepo.getByYearAndMonthAndDay(year, month, day);
	}
}