package com.tmi.emprendedores.controller.view;

public interface WebUtils {

	/*GENERICOS*/
	public final String MAPPING_ROOT ="/";
	public final String MAPPING_PORTAL ="/portal";
	
	/*USUARIO*/
	public final String MAPPING_REGISTRO = "/registro";
	public final String MAPPING_MODIFICAR_PERFIL = "/modificarPerfil";
	public final String MAPPING_MODIFICAR_CLAVE = "/modificarClave";
	public final String MAPPING_LOGIN = "/login";
	public final String MAPPING_LOGBOX = "/logBox";
	public final String MAPPING_MI_PERFIL= "/miPerfil";
	public final String MAPPING_MI_PERFIL2= "/miPerfil2";
	public final String MAPPING_RECUPERO= "/recupero";
	
	
	/*EMPRENDIMIENTO*/
	public final String MAPPING_MODIFICAR_EMPRENDIMIENTO = "/modificarEmprendimiento";
	
	/*UBICACION*/
	public final String MAPPING_GET_SELECT_PAIS = "/getSelectPais";
	public final String MAPPING_GET_SELECT_PROVINCIA = "/getSelectProvincia";
	public final String MAPPING_GET_SELECT_LOCALIDAD = "/getSelectLocalidad";

	/*EVENTOS*/
	public final String MAPPING_CREAR_EVENTO = "/crearEvento";
	public final String MAPPING_MODIFICAR_EVENTO = "/modificarEvento";
	public final String MAPPING_BORRAR_EVENTO = "/borrarEvento";
	public final String MAPPING_INSCRIBIRME_EVENTO = "/inscribirmeEvento";
	public final String MAPPING_DESINSCRIBIRME_EVENTO = "/desinscribirmeEvento";
	public final String MAPPING_DETALLE_EVENTO = "/detalleEvento";
	public final String MAPPING_ASISTIR_EVENTO = "/asistirEvento";
	public final String MAPPING_DESASISTIR_EVENTO = "/desasistirEvento";
	public final String MAPPING_GET_EVENTOS_PUBLICOS = "/getEventosPublicos";
	public final String MAPPING_GET_EVENTOS_CREADOS = "/getEventosCreados";
	public final String MAPPING_GET_EVENTOS_INSCRIPTOS = "/getEventosInscriptos";
	public final String MAPPING_GET_EVENTOS_ASISTENCIA = "/getEventosAsistencia";
	public final String MAPPING_GET_EVENTOS_CALENDARIO = "/getEventosCalendario";
	public final String MAPPING_GET_EVENTOS_FILTRADOS = "/getEventosFiltrados";
	
	/**
	 * Este enum refleja los archivos de vista que tiene la web (de momento solo *jsp)
	 */
	public enum Page{
		/*GENERICOS*/
		PORTAL("portal"),
		REGISTRIO("registro"),
		
		/*USUARIO*/
		LOGIN("login"),
		LOGBOX("logBox"),
		MODIFICAR_PERFIL("modificarPerfil"),
		MODIFICAR_CLAVE("modificarClave"),
		MI_PERFIL("miPerfil"),
		MI_PERFIL2("miPerfil2"),
		
		
		/*EMPRENDIMIENTO*/
		MODIFICAR_EMPRENDIMIENTO("modificarEmprendimiento"),
		
		/*UBICACION*/
		SELECT_PAIS ("ubicacionSelectPais"),
		SELECT_PROVINCIA ("ubicacionSelectProvincia"),
		SELECT_LOCALIDAD ("ubicacionSelectLocalidad"),
		
		/*EVENTOS*/
		CREAR_EVENTO ("crearEvento"),
		MODIFICAR_EVENTO ("modificarEvento"),
		MIS_EVENTOS ("misEventos"),
		DETALLE_EVENTO("detalleEvento"),
		LISTA_EVENTOS("listaEventos"),
		
		;
		
		private String file;
		private Page(String file) { this.file = file; }
		
		public String getFile() { return file; }
		
		public String redirect() { return "redirect:/"+getFile(); }
	}
}
