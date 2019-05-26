package com.tmi.emprendedores.service;

import com.tmi.emprendedores.persistence.entities.Emprendimiento;

public interface EmprendimientoService {
    
	public Emprendimiento save(Emprendimiento emprendimiento);
	
	public Emprendimiento findById(Integer id);
	
}
