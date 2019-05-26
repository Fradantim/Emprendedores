package com.tmi.emprendedores.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.tmi.emprendedores.persistence.entities.Emprendimiento;

/**
 * Objeto responsable de las validaciones de los campos al crear un usuario
 */
@Component
public class EmprendimientoValidator extends MyValidator {


	@Override
	public boolean supports(Class<?> aClass) {
		return Emprendimiento.class.equals(aClass);
	}

	private String[] getCamposNoVacios() {
		return new String[] { "nombre", "descripcion"/* ,"link","contacto","localidad" */ };
	}

	@Override
	public void validate(Object o, Errors errors) {
	}

	public void validateUpdate(Emprendimiento emprendimiento, Errors errors) {
		// validacion de campos vacios
		rejectIfEmptyOrWhitespace(errors, getCamposNoVacios());
	}
}