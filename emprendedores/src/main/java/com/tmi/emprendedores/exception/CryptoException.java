package com.tmi.emprendedores.exception;

public class CryptoException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public CryptoException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }	
}