package com.tmi.emprendedores.controller.view;

public interface WebUtils {

	public final String MAPPING_ROOT ="/";
	public final String MAPPING_PORTAL ="/portal";
	public final String MAPPING_REGISTRO = "/registro";
	public final String MAPPING_MODIFICAR_PERFIL = "/modificarPerfil";
	public final String MAPPING_MODIFICAR_CLAVE = "/modificarClave";
	public final String MAPPING_MODIFICAR_EMPRENDIMIENTO = "/modificarEmprendimiento";
	public final String MAPPING_LOGIN = "/login";
	public final String MAPPING_LOGBOX = "/logBox";
	public final String MAPPING_MI_PERFIL= "/miPerfil";
	public final String MAPPING_MI_PERFIL2= "/miPerfil2";
	public final String MAPPING_GET_SELECT_PAIS = "/getSelectPais";
	public final String MAPPING_GET_SELECT_PROVINCIA = "/getSelectProvincia";
	public final String MAPPING_GET_SELECT_LOCALIDAD = "/getSelectLocalidad";

	/**
	 * Este enum refleja los archivos de vista que tiene la web (de momento solo *jsp)
	 */
	public enum Page{
		PORTAL("portal"),
		REGISTRIO("registro"),
		LOGIN("login"),
		LOGBOX("logBox"),
		MODIFICAR_PERFIL("modificarPerfil"),
		MODIFICAR_CLAVE("modificarClave"),
		MODIFICAR_EMPRENDIMIENTO("modificarEmprendimiento"),
		MI_PERFIL("miPerfil"),
		MI_PERFIL2("miPerfil2"),
		SELECT_PAIS ("ubicacionSelectPais"),
		SELECT_PROVINCIA ("ubicacionSelectProvincia"),
		SELECT_LOCALIDAD ("ubicacionSelectLocalidad"),
		;
		
		private String file;
		private Page(String file) {
			this.file = file;
		}
		
		public String getFile() {
			return file;
		}
		
		public String redirect() {
			return "redirect:/"+getFile();
		}
	}
}
