package com.tmi.emprendedores.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.service.UsuarioService;

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

    /**
     * En este metodo se ejecutan las validaciones del registro de usuario
     */
    @Override
    public void validate(Object o, Errors errors) {
    	Usuario user = (Usuario) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "NotEmpty");
        if (user.getNick().length() < LARGO_MIN_CAMPO || user.getNick().length() > LARGO_MAX_CAMPO) {
            errors.rejectValue("nick", "Size.userForm.nick");
        }
        
        if (userService.findByNick(user.getNick()) != null) {
            errors.rejectValue("nick", "Duplicate.userForm.nick");
        }
        
        if (userService.findByNick(user.getNick()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < LARGO_MIN_CAMPO || user.getPassword().length() > LARGO_MAX_CAMPO) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}