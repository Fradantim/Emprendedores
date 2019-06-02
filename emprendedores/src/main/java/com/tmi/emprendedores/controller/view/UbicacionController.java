package com.tmi.emprendedores.controller.view;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tmi.emprendedores.persistence.entities.ubicacion.Pais;
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

	@GetMapping(WebUtils.MAPPING_GET_PAISES)
	public void getPaises(Model model) {
		model.addAttribute("paises", paisService.findAll().stream().map(Pais::toMiniDTO).collect(Collectors.toSet()));
	}
	
	//public String goToModificarClave(Model model, Principal principal, @RequestParam(value = "idEmprendimiento", required = false) Integer idEmprendimiento) {

	
}