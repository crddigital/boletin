package com.mcr.boletin.presenter;

import java.io.Serializable;

import com.mcr.boletin.IHandlers.IViewCredencialHandler;
import com.mcr.boletin.model.Usuario;
import com.mcr.boletin.servicio.ServicioSesion;
import com.mcr.boletin.ui.LayoutCredencial.LayoutCredencial;


public class PresenterSesion implements Serializable, IViewCredencialHandler{

	private LayoutCredencial view;
	private ServicioSesion service;
	
	public PresenterSesion(LayoutCredencial view, ServicioSesion service) {
		
		this.view = view;
		this.service = service;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void login() {
		
		
		String nombreDeUsuario =view.getTxtNombreDeUsuario().getValue();
		String contrasenia = (view.getTxtContrasenia().getValue());
		Usuario usuario = service.login(nombreDeUsuario,contrasenia);
		
		if(usuario !=null){
			view.loginOk(usuario);
		}else{
			view.loginError();
		}
		
	} 

}
