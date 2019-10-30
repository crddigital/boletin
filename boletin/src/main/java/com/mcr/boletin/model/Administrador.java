package com.mcr.boletin.model;

import java.io.Serializable;

public class Administrador extends Perfil implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Administrador() {
		
	}

	@Override
	public Perfil obtenerPerfil() {
		
		return this;
	}

	@Override
	public String recuperarPerfil() {
		
		return "administrador";
	}
	


}
