package com.tmi.emprendedores.controller.view;

import com.tmi.emprendedores.persistence.entities.AbsEntity;

public interface HasOwner<T extends AbsEntity> {

	public T getOwner();
}
