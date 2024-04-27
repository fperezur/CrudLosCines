package com.rf.lcs.persistence.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Transient;

public interface Modelo {
	//Devuelve true si el objeto es válido para insertar en la BBDD
	@Transient
	@JsonIgnore
	public boolean isValidInsert();
	
	//Devuelve true si el objeto es válido para actualizar en la BBDD
	@Transient
	@JsonIgnore
	public boolean isValidUpdate();
}
