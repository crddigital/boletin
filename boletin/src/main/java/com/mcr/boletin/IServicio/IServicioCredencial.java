package com.mcr.boletin.IServicio;

import com.mcr.boletin.model.Usuario;

public interface IServicioCredencial {
	
	
	public Usuario login(String nombreUsuario, String contrasenia);

}
