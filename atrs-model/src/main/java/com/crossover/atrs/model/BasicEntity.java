package com.crossover.atrs.model;

import org.springframework.data.annotation.Id;

/**
 * Mongo Basic Entity that carries the common attributes shared by all mongo
 * entities
 * 
 * @author valverde.thiago
 *
 */
public abstract class BasicEntity {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}