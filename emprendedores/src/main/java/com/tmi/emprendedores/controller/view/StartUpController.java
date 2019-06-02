package com.tmi.emprendedores.controller.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.tmi.emprendedores.persistence.entities.Perfil;
import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.persistence.entities.ubicacion.Pais;
import com.tmi.emprendedores.persistence.entities.ubicacion.Provincia;
import com.tmi.emprendedores.service.LocalidadService;
import com.tmi.emprendedores.service.PaisService;
import com.tmi.emprendedores.service.PerfilService;
import com.tmi.emprendedores.service.ProvinciaService;

@Component
public class StartUpController implements InitializingBean {
	
	private final static String CSV_DELIMITER = ",";

	private static final Logger logger = LoggerFactory.getLogger(StartUpController.class);
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private ProvinciaService provService;

	@Autowired
	private LocalidadService locService;

	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("Evaluando estado de la BBDD.");
		evaluarPerfiles();
		
		evaluarUbicaciones();		
		
		logger.info("Fin "+this.getClass().getSimpleName());
	}
	
	private void evaluarPerfiles() {
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
	}
	
	
	private void evaluarUbicaciones() {
		logger.info("Evaluo existencia de Ubicaciones (Pais/Provincia/Localidad)");
		if(paisService.count()==0) {
			logger.info("No hay ubicaciones cargadas, las cargo.");
			BufferedReader reader;
			try {
				logger.info("PAISES -------------------------------");
				reader = new BufferedReader(new FileReader(new ClassPathResource("csvdata/paises.csv").getFile()));
				String line = reader.readLine();
				while (line != null) {
					Pais pais = new Pais();
					String[] campos = line.split(CSV_DELIMITER);
					
					pais.setId(new Integer(campos[0]));
					pais.setNombre(campos[1]);
					
					paisService.save(pais);
					
					line = reader.readLine();
				}
				reader.close();
				
				logger.info("PROVINCIAS -------------------------------");
				reader = new BufferedReader(new FileReader(new ClassPathResource("csvdata/provincias.csv").getFile()));
				line = reader.readLine();
				while (line != null) {
					Provincia prov = new Provincia();
					String[] campos = line.split(CSV_DELIMITER);
					
					prov.setId(new Integer(campos[0]));
					prov.setPais(paisService.findById(new Integer(campos[1])));
			
					prov.setNombre(campos[2]);
					
					provService.save(prov);
					
					line = reader.readLine();
				}
				reader.close();
				
				logger.info("LOCALIDADES -------------------------------");
				reader = new BufferedReader(new FileReader(new ClassPathResource("csvdata/localidades.csv").getFile()));
				line = reader.readLine();
				while (line != null) {
					Localidad loc = new Localidad();
					String[] campos = line.split(CSV_DELIMITER);
					
					loc.setId(new Integer(campos[0]));
					loc.setProvincia(provService.findById(new Integer(campos[1])));
			
					loc.setNombre(campos[2]);
					
					locService.save(loc);
					
					line = reader.readLine();
				}
				reader.close();
				
				logger.info("Ubicaciones persistidas.");
				
				/*
				//TODO: corregir manejo de transacciones
				logger.info("Recupero Info de BBDD");
				
				for(Pais pais: paisService.findAll()) {
					Pais paisE = paisService.findById(pais.getId());
					logger.info(pais+" "+pais.getNombre()+" ("+paisE.getProvincias().size()+" provs)");
					for(Provincia prov : provService.findByPais(pais)) {
						logger.info("\t"+prov+" "+prov.getNombre()+" ("+prov.getLocalidades().size()+" locs)");
						String locs = "";
						for(Localidad loc : locService.findByProvincia(prov)) {
							locs +=loc+" "+loc.getNombre()+";";
						}
						logger.info("\t\t"+locs);
					}
				}
				*/
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
