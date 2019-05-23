package com.tmi.emprendedores.dto;

public interface DTOTransformable<T extends DTO> {
	public T toDTO();
}