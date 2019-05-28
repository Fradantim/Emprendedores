package com.tmi.emprendedores.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmi.emprendedores.persistence.entities.Emprendimiento;
import com.tmi.emprendedores.persistence.repository.EmprendimientoRepository;
import com.tmi.emprendedores.service.EmprendimientoService;

@Service
public class EmprendimientoServiceImpl implements EmprendimientoService {

	@Autowired
    private EmprendimientoRepository emprendimientoRepo;

	@Override
	public Emprendimiento save(Emprendimiento emprendimiento) {
		return emprendimientoRepo.save(emprendimiento);
	}

	@Override
	public Emprendimiento findById(Integer id) {
		if(id == null) return null;
		return emprendimientoRepo.findById(id).get();
				
	}
}