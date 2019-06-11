package com.tmi.emprendedores.controller.view;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmi.emprendedores.controller.view.WebUtils.Page;
import com.tmi.emprendedores.dto.ubicacion.LocalidadDTO;
import com.tmi.emprendedores.dto.ubicacion.PaisDTO;
import com.tmi.emprendedores.dto.ubicacion.ProvinciaDTO;
import com.tmi.emprendedores.persistence.entities.ubicacion.Localidad;
import com.tmi.emprendedores.persistence.entities.ubicacion.Pais;
import com.tmi.emprendedores.persistence.entities.ubicacion.Provincia;
import com.tmi.emprendedores.service.LocalidadService;
import com.tmi.emprendedores.service.PaisService;
import com.tmi.emprendedores.service.ProvinciaService;

@Controller
public class UbicacionController extends WebController {

	@Autowired
	private PaisService paisService;
	
	@Autowired
	private ProvinciaService provService;
	
	@Autowired
	private LocalidadService locService;
		
	@GetMapping(WebUtils.MAPPING_GET_SELECT_PAIS)
	public String getPaises(Model model, Principal principal
			, @RequestParam(value = "selectedId", required = false) Integer selectedId) {
		
		List<PaisDTO> dtos = paisService.findAll().stream().map(Pais::toMiniDTO).collect(Collectors.toList());
		if(selectedId != null) {
			updateSelected(dtos, selectedId);
		}
		
		model.addAttribute("paises", dtos);
		
		return Page.SELECT_PAIS.getFile();
	}
	    
	@GetMapping(WebUtils.MAPPING_GET_SELECT_PROVINCIA)
	public String getProvincias(Model model, Principal principal
			, @RequestParam(value = "paisId", required = false) Integer paisId
			, @RequestParam(value = "selectedId", required = false) Integer selectedId) {
		
		Pais pais = paisService.findById(paisId);
		
		List<ProvinciaDTO> dtos = provService.findByPais(pais).stream().map(Provincia::toMiniDTO).collect(Collectors.toList());
		
		if(selectedId != null) {
			updateSelected(dtos, selectedId);
		}
		
		model.addAttribute("provincias", dtos);
		
		return Page.SELECT_PROVINCIA.getFile();
	}
	
	@GetMapping(WebUtils.MAPPING_GET_SELECT_LOCALIDAD)
	public String getLocalidades(Model model, Principal principal
			, @RequestParam(value = "provinciaId", required = false) Integer provinciaId
			, @RequestParam(value = "selectedId", required = false) Integer selectedId) {
		
		Provincia provincia = provService.findById(provinciaId);
		
		List<LocalidadDTO> dtos = locService.findByProvincia(provincia).stream().map(Localidad::toMiniDTO).collect(Collectors.toList());
		
		if(selectedId != null) {
			updateSelected(dtos, selectedId);
		}
		
		model.addAttribute("localidades", dtos);
		
		return Page.SELECT_LOCALIDAD.getFile();
	}
}