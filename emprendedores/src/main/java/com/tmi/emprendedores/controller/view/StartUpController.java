package com.tmi.emprendedores.controller.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmi.emprendedores.persistence.entities.Perfil;
import com.tmi.emprendedores.service.PerfilService;

@Component
public class StartUpController implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(StartUpController.class);
	
	@Autowired
	private PerfilService perfilService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("Evaluando estado de la BBDD.");
		
		logger.info("Evaluo existencia de Perfiles.");
		//Si no estan cargados los perfiles los agrego;
		for(Perfil perfil : PerfilService.getPerfiles()) {
			logger.info(perfil.getNombre()+" ?");
			Perfil perfilPersistido = perfilService.findByNombre(perfil.getNombre());
			if(perfilPersistido == null) {
				logger.info("\tNo existe, lo grabo y dejo en memoria.");
				perfilService.save(perfil);
			} else {
				logger.info("\tExiste, lo paso a memoria.");
				perfil.setId(perfilPersistido.getId());
			}
		}
		
		logger.info("Perfiles en memoria:");
		for(Perfil perfil : PerfilService.getPerfiles()) {
			logger.info("\t"+perfil +" "+perfil.getNombre());
		}
		
		
		
		
		logger.info("Fin "+this.getClass().getSimpleName());
	}
}
