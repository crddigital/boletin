package com.mcr.boletin.model;

import java.io.Serializable;

public class Administrativo extends Perfil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Perfil obtenerPerfil() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public String recuperarPerfil() {
		
		return "administrativo";
	}



}
