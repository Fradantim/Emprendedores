package com.tmi.emprendedores.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Objeto responsable de las validaciones de los campos al crear un usuario
 */
@Component
public abstract class MyValidator implements Validator {

	public static final int LARGO_MIN_CAMPO = 8;
	public static final int LARGO_MAX_CAMPO = 12;

	protected void rejectIfEmptyOrWhitespace(Errors errors, String... fields) {
		for (String field : fields) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "NotEmpty");
		}
	}
}