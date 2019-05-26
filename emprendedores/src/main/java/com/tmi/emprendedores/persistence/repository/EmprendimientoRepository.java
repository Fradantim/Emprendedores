package com.tmi.emprendedores.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmi.emprendedores.persistence.entities.Emprendimiento;

public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Integer> {

}