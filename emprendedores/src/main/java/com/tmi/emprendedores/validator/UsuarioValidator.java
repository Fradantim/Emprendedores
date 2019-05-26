package com.tmi.emprendedores.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.service.UsuarioService;

/**
 * Objeto responsable de las validaciones de los campos al crear un usuario
 */
@Component
public class UsuarioValidator implements Validator {

	public static final int LARGO_MIN_CAMPO = 1;
	public static final int LARGO_MAX_CAMPO = 32;

	@Autowired
	private UsuarioService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Usuario.class.equals(aClass);
	}

	private String[] getCamposNoVacios() {
		return new String[] { "nick", "nombre", "apellido", "email"/* ,"pais","provincia","localidad" */ };
	}

	private void rejectIfEmptyOrWhitespace(Errors errors, String... fields) {
		for (String field : fields) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "NotEmpty");
		}
	}

	@Override
	public void validate(Object o, Errors errors) {
	}

	public void validateNew(Usuario newUser, Errors errors) {
		// validacion de campos vacios
		rejectIfEmptyOrWhitespace(errors, getCamposNoVacios());
		rejectIfEmptyOrWhitespace(errors, "password", "passwordConfirm");

		if (newUser.getNick().length() < LARGO_MIN_CAMPO || newUser.getNick().length() > LARGO_MAX_CAMPO) {
			errors.rejectValue("nick", "Size.userForm.nick");
		}

		if (userService.findByNick(newUser.getNick()) != null) {
			errors.rejectValue("nick", "Duplicate.userForm.nick");
		}

		if (userService.findByNick(newUser.getNick()) != null) {
			errors.rejectValue("email", "Duplicate.userForm.email");
		}

		if (newUser.getPassword().length() < LARGO_MIN_CAMPO || newUser.getPassword().length() > LARGO_MAX_CAMPO) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!newUser.getPasswordConfirm().equals(newUser.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
	}

	public void validateUpdate(Usuario userOrig, Errors errors, Usuario userMod) {
		// validacion de campos vacios
		rejectIfEmptyOrWhitespace(errors, getCamposNoVacios());

		if (userMod.getNick().length() < LARGO_MIN_CAMPO || userMod.getNick().length() > LARGO_MAX_CAMPO) {
			errors.rejectValue("nick", "Size.userForm.nick");
		}

		// si ya hay un usuario con el nick ingresado y no es el del usuario logueado
		Usuario userConNuevoNick = userService.findByNick(userMod.getNick());
		if (userConNuevoNick != null && !userOrig.equals(userConNuevoNick)) {
			errors.rejectValue("nick", "Duplicate.userForm.nick");
		}

		// si ya hay un usuario con el email ingresado y no es el del usuario logueado
		Usuario userConNuevoEmail = userService.findByEmail(userMod.getEmail());
		if (userConNuevoEmail != null && !userOrig.equals(userConNuevoEmail)) {
			errors.rejectValue("email", "Duplicate.userForm.email");
		}
	}

	public void validateUpdatePassword(Usuario userForm, Errors errors) {
		rejectIfEmptyOrWhitespace(errors, "password", "passwordConfirm");

		if (!userForm.getPasswordConfirm().equals(userForm.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}

	}
}