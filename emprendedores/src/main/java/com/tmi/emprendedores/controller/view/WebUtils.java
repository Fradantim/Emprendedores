package com.tmi.emprendedores.controller.view;

public interface WebUtils {

	public final String MAPPING_ROOT ="/";
	public final String MAPPING_PORTAL ="/portal";
	public final String MAPPING_REGISTRO = "/registro";
	public final String MAPPING_LOGIN = "/login";
	public final String MAPPING_LOGBOX = "/logBox";

	public enum Page{
		PORTAL("portal"),
		REGISTRIO("registro"),
		LOGIN("login"),
		LOGBOX("logBox");
		
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
