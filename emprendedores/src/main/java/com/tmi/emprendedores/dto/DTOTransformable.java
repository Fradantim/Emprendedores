package com.tmi.emprendedores.dto;

public interface DTOTransformable<T extends DTO> {
	
	/**
	 * Retorna un DTO con todos los DTO que conoce cargados
	 */
	public T toDTO();
	
	/**
	 * Retorna un DTO <b>solo de este objeto</b> con los DTO que conoce vacios, salvo que sean muy importantes, o que no tengan referencias nuevamente a este objeto.
	 * @return
	 */
	public T toMiniDTO();
}