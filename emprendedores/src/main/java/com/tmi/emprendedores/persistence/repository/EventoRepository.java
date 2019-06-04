package com.tmi.emprendedores.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmi.emprendedores.dto.EventoDTO.TipoVisibilidad;
import com.tmi.emprendedores.persistence.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
	public List<Evento> findByTipoVisibilidadAndBorradoOrderByFechaCreacionDesc(TipoVisibilidad tipoVisibilidad, Boolean borrado);
	
	public Evento findByIdAndBorrado(Integer id, Boolean borrado);
}