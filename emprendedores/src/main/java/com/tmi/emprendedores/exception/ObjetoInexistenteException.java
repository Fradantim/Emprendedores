package com.tmi.emprendedores.exception;

public class ObjetoInexistenteException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjetoInexistenteException(Class<?> clase, Integer id, Throwable err) {
        super("No se encontró un "+clase.getSimpleName()+" con id "+id+".", err);
    }
	
	public ObjetoInexistenteException(Class<?> clase, Integer id) {
        this(clase, id, null);
    }
	
	public ObjetoInexistenteException(String message) {
        super(message);
    }	
}