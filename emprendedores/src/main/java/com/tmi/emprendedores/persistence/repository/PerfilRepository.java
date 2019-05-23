package com.tmi.emprendedores.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmi.emprendedores.persistence.entities.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

}