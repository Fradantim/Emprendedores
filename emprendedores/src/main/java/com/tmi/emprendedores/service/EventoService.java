package com.tmi.emprendedores.service;

import java.util.List;

import com.tmi.emprendedores.persistence.entities.Evento;
import com.tmi.emprendedores.persistence.entities.Usuario;

public interface EventoService extends Service{
	
	public Evento save(Evento evento);
	
	public Evento findById(Integer id);
	
	public List<Evento> findPublicos();
	
	public List<Evento> findPublicos(int index, int pageSize);
	
	public List<Evento> getByYearAndMonth(int year, int month);
	
	public List<Evento> getByYearAndMonthAndDay(int year, int month, int day);
	
	public List<Evento> findByCreador(Usuario creador, int index, int pageSize);

}
