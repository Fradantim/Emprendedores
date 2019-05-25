package com.tmi.emprendedores.controller.view;

public interface WebUtils {

	public final String MAPPING_ROOT ="/";
	public final String MAPPING_PORTAL ="/portal";
	public final String MAPPING_REGISTRO = "/registro";
	public final String MAPPING_MODIFICAR_USUARIO = "/modificarUsuario";
	public final String MAPPING_GET_USUARIO = "/getUsuario";
	public final String MAPPING_LOGIN = "/login";
	public final String MAPPING_LOGBOX = "/logBox";
	public final String MAPPING_MI_PERFIL= "/miPerfil";

	public enum Page{
		PORTAL("portal"),
		REGISTRIO("registro"),
		LOGIN("login"),
		LOGBOX("logBox"),
		MODIFICAR_USUARIO("modificarUsuario"),
		MI_PERFIL("miPerfil"),;
		
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
