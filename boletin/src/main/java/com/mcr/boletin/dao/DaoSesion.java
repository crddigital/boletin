package com.mcr.boletin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import com.mcr.boletin.model.Administrador;
import com.mcr.boletin.model.Administrativo;
import com.mcr.boletin.model.Usuario;
import com.mcr.boletin.tools.StringMD;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;


public class DaoSesion {
	
	
	private static DaoSesion instance;
	
	private DaoSesion(){
		
	}
	
	public static DaoSesion getInstance(){
		
		if(instance == null){
			instance = new DaoSesion();			
		} return instance;
		
	}

	public Usuario login(String nombreUsuario, String contrasenia) {
		
		String consulta ="select * from v_sesion where nombre_usuario = ? and contrasenia = ?";
		Vector<Object>datos = new Vector<Object>();
		datos.add(nombreUsuario);
		datos.add(generarSha(contrasenia));
		ResultSet rs = null;
		try {
		Conexion conexion = new Conexion();
		conexion.getConexion().setAutoCommit(false);
		rs = conexion.select(consulta, datos);
			if(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt(1));
				usuario.setNombre(rs.getString(2));
				usuario.setApellido(rs.getString(3));				
				if(rs.getString(6).equalsIgnoreCase("administrador")){
					usuario.setPerfil(new Administrador());
						
					}
					if(rs.getString(6).equalsIgnoreCase("administrativo")){
						usuario.setPerfil(new Administrativo());						
					}
					
					//log
					consulta = "insert into log (fecha,servicio,metodo,ip,idUsuario) values (?,?,?,?,?)";
					datos.clear();
					datos.add(new Timestamp(new java.util.Date().getTime()));		
					datos.add("servicioSesion");
					datos.add("login:" + usuario.getIdUsuario());
				
					WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
					String ipAddress = webBrowser.getAddress();
					datos.add(ipAddress);
					datos.add(usuario.getIdUsuario());				
					conexion.insert(consulta, datos);
					conexion.getConexion().commit();
					
					return usuario;
					
					
					
				}else return null;
			  }catch(SQLException e){
				  e.printStackTrace();
			}
			return null;
	     }

	private String generarSha(String contrasenia) {
			
			contrasenia = StringMD.getStringMessageDigest(contrasenia, StringMD.SHA1);
			return contrasenia;
	}

}
