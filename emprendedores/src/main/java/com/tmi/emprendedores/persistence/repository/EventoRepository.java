package com.tmi.emprendedores.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmi.emprendedores.dto.EventoDTO.TipoVisibilidad;
import com.tmi.emprendedores.persistence.entities.Evento;
import com.tmi.emprendedores.persistence.entities.Usuario;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
	
	public List<Evento> findByTipoVisibilidadAndBorradoOrderByFechaDesc(TipoVisibilidad tipoVisibilidad, Boolean borrado, Pageable p);
	
	public Evento findByIdAndBorrado(Integer id, Boolean borrado);
	
	@Query("select e from Evento e where year(e.fecha) = ?1 and month(e.fecha) = ?2")
	public List<Evento> getByYearAndMonth(int year, int month);
	
	@Query("select e from Evento e where year(e.fecha) = ?1 and month(e.fecha) = ?2 and day(e.fecha) = ?3")
	public List<Evento> getByYearAndMonthAndDay(int year, int month, int day);
	
	public List<Evento> findByCreadorAndBorradoOrderByFechaDesc(Usuario creador, Boolean borrado, Pageable p);
}