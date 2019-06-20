package com.tmi.emprendedores.persistence.entities;

public interface HasOwner<T extends AbsEntity> {

	public T getOwner();
}
