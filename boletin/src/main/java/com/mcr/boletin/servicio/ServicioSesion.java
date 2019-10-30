package com.mcr.boletin.servicio;

import java.io.Serializable;

import com.mcr.boletin.IServicio.IServicioCredencial;
import com.mcr.boletin.dao.DaoSesion;
import com.mcr.boletin.model.Usuario;

public class ServicioSesion implements Serializable, IServicioCredencial {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioSesion instance;
	
	private ServicioSesion(){
		
	}
	
	public static ServicioSesion getInstance(){
		
		if(instance == null){
			instance = new ServicioSesion();			
		}return instance;
	}
	

	@Override
	public Usuario login(String nombreUsuario, String contrasenia) {
		
		return DaoSesion.getInstance().login(nombreUsuario,contrasenia);
	}

}
