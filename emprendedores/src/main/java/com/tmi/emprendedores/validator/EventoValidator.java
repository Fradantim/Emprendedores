package com.tmi.emprendedores.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.tmi.emprendedores.persistence.entities.Emprendimiento;
import com.tmi.emprendedores.persistence.entities.Evento;

/**
 * Objeto responsable de las validaciones de los campos al crear o modificar Eventos
 */
@Component
public class EventoValidator extends MyValidator {


	@Override
	public boolean supports(Class<?> aClass) {
		return Emprendimiento.class.equals(aClass);
	}

	private String[] getCamposNoVacios() {
		return new String[] { "nombre", "descripcion", "descripcionLarga"/* ,"link","contacto","localidad" */ };
	}

	@Override
	public void validate(Object o, Errors errors) {
	}

	public void validateInsert(Evento evento, Errors errors) {
		// validacion de campos vacios
		rejectIfEmptyOrWhitespace(errors, getCamposNoVacios());
	}
	
	public void validateUpdate(Evento evento, Errors errors) {
		// validacion de campos vacios
		rejectIfEmptyOrWhitespace(errors, getCamposNoVacios());
	}
}