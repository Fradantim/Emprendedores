package com.tmi.emprendedores.controller.view;

import java.security.Principal;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmi.emprendedores.MailSender;
import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.dto.MensajeDTO;
import com.tmi.emprendedores.dto.MensajeDTO.TipoMensaje;
import com.tmi.emprendedores.exception.CryptoException;
import com.tmi.emprendedores.persistence.entities.RecuperoClave;
import com.tmi.emprendedores.persistence.entities.Usuario;
import com.tmi.emprendedores.service.RecuperoClaveService;
import com.tmi.emprendedores.service.UsuarioService;
import com.tmi.emprendedores.validator.UsuarioValidator;

@Controller
public class ClaveController extends WebController{
    
    @Autowired
    private RecuperoClaveService recuperoClaveService;
	
	@Autowired
    private UsuarioValidator usuarioValidator;
	
	@Autowired
	private MailSender mailSender;

	@GetMapping(WebUtils.MAPPING_MODIFICAR_CLAVE)
    public String goToModificarClave(Model model, Principal principal, @RequestParam(value = "idEncriptado", required = false) String idEncriptado) {
		
		//si me dan un idEncriptado quiero usar la pantalla para modificar la clave que perdi (viene de Solicitud Recupero Clave)
		
		if(idEncriptado == null && !isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
		
		if(idEncriptado!= null) {
			model.addAttribute("idEncriptado", idEncriptado);
		}
	
    	model.addAttribute("userForm", new Usuario());
    	if(isUsuarioLogueado(principal))
    		addUsuarioLogueado(model, principal);
        return Page.MODIFICAR_CLAVE.getFile();
    }
    
    @PostMapping(WebUtils.MAPPING_MODIFICAR_CLAVE)
    public String modificarClave(Model model, Principal principal
    		, @ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult
    		, @RequestParam(value = "idEncriptado", required = false) String idEncriptado) {

    	//si me dan un idEncriptado quiero usar la pantalla para modificar la clave que perdi (viene de Solicitud Recupero Clave)
    	
    	if(idEncriptado == null && !isUsuarioLogueado(principal)) {
    		return goToDebeIniciarSesion(model).getFile();
    	}
    	
    	usuarioValidator.validateUpdatePassword(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	//si hubo errores vuelvo a la web de la que vine
        	return Page.MODIFICAR_CLAVE.getFile();
        }
    	
        if(isUsuarioLogueado(principal)) {
        	//el usuario esta logueado y quiere cambiar su clave
        	Usuario userLogueado = getLoggedUser(principal);
            //si paso todas las validaciones actualizo el usuario
            userLogueado.setPassword(userForm.getPassword());
            
            //persisto nuevo usuario
            userLogueado = usuarioService.saveAndEncodePassword(userLogueado);
            addUsuarioLogueado(model, userLogueado);
            
            addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "La clave se modifico correctamente!"));
            return Page.MI_PERFIL.getFile();
        
        } else if(idEncriptado != null){
        	//el usuario no esta logueado y quiere setear su clave olvidada
        	RecuperoClave recupero = recuperoClaveService.findByIdUsuarioEncriptado(idEncriptado); 
        	
        	Usuario user= recupero.getUsuario(); 
        	user.setPassword(userForm.getPassword());
        	
        	usuarioService.saveAndEncodePassword(user);
        	
        	recuperoClaveService.delete(recupero);
        	
        	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "La clave se modifico correctamente, ya puede ingresar!"));
        	return Page.LOGIN.getFile();
        }
        
        return Page.LOGIN.getFile();
     }
    
    @PostMapping(WebUtils.MAPPING_SOLICITUD_RECUPERO_CLAVE)
    public String recuperoClave(Model model, @RequestParam(value = "email", required = false) String mail) {
    	
    	if(mail == null) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "Debe ingresar un e-mail!"));
    		return Page.LOGIN.getFile();
    	} 
    	
    	Usuario user = usuarioService.findByEmail(mail.trim());
    	
    	if(user == null) {
    		addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "No se encontró el e-mail!"));
    		return Page.LOGIN.getFile();
    	} 
    	
    	RecuperoClave recupero;
    	try {
			recupero = new RecuperoClave(user);
		} catch (CryptoException e) {
			addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "Ocurrio un error no controlado."));
			e.printStackTrace();
			return Page.LOGIN.getFile(); 
		}
    	
    	try {
			mailSender.sendMail(mailSender.buildMessageRecuperoClave(user.getEmail(), recupero));
		} catch (AddressException e) {
			addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "El correo de destino no es consistente."));
			return Page.LOGIN.getFile(); 
		} catch (MessagingException e) {
			addMensajes(model, new MensajeDTO(TipoMensaje.ERROR, "Ocurrio un error al enviar el correo."));
			e.printStackTrace();
			return Page.LOGIN.getFile(); 
		}
    	
    	//si el correo se mando bien entonces persisto.
    	recuperoClaveService.save(recupero);
    	
    	addMensajes(model, new MensajeDTO(TipoMensaje.SUCCESS, "Se envió un correo, aguarde unos minutos a que llegue y verifique en su correo o casilla de spam."));
    	return Page.LOGIN.getFile();    	
     }
    
    
    
}