package com.mcr.boletin.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.mcr.boletin.model.Boletin;
import com.mcr.boletin.model.Usuario;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;


public class DaoBoletin {
	
	
	public static DaoBoletin instance;
	public static Logger log = Logger.getLogger(DaoBoletin.class);
	
	
	
	private DaoBoletin(){
				
	}
	
	public static DaoBoletin getInstance(){
		
		if(instance == null){
			instance = new DaoBoletin();
		}return instance;
	}

	public ArrayList<Boletin> getBoletines() {
		
		//busca sobre la fecha del boletin
		//String consulta = "select  * from boletin b WHERE YEAR(b.fechaDeBoletin) = ?";
		//String consulta = "select * from boletin b WHERE b.numeroDeBoletin like ? order by b.ID_boletin desc";
		String consulta = "select * from boletin b WHERE b.numeroDeBoletin like ? and b.fechaDeBoletin <> '0000-00-00' and b.estadoBoletin = 1 "
				+ "order by b.fechaDeBoletin desc";
		Vector<Object>datos = new Vector<Object>();
		Calendar fecha = Calendar.getInstance();
	    int anio = fecha.get(Calendar.YEAR);
		datos.add(String.valueOf(anio));
	    //datos.add(anio);
		ResultSet rs = null;
		ArrayList<Boletin> boletines = new ArrayList<Boletin>();
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsultaConLike(consulta, datos);
			while(rs.next()){
				
				Boletin boletin = new Boletin();
				boletin.setIdBoletin(rs.getInt(1));
				boletin.setNumeroDeBoletin(rs.getString(2));
				boletin.setFechaDeBoletin(rs.getDate(3));
				boletin.setResolucionesDesde(rs.getString(4));
				boletin.setResolucionesHasta(rs.getString(5));
				boletin.setOrdenanzasIncluidas(rs.getString(6));
				boletin.setOtrosDocumentos(rs.getString(7));
				boletin.setDireccionArchivo("http://www.comodoro.gov.ar/archivos/boletin_oficial/pdf/"+rs.getString(8));
				

				if(!boletin.getResolucionesDesde().isEmpty() || !boletin.getResolucionesHasta().isEmpty()){
					boletin.setResolucionDesdeHasta(boletin.getResolucionesDesde()+" hasta "+boletin.getResolucionesHasta());
				}else {
					boletin.setResolucionDesdeHasta("");
				}
				
				boletin.setEstadoBoletin(true); //<-- always true!
				boletin.setTamanioArchivo(rs.getString(10));
				boletines.add(boletin);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error en getBoletines():" + e);
			e.printStackTrace();
		}		
		return boletines;
	}

	public int guardarBoletin(Boletin boletin,Usuario usuario) {
		
		//= "insert into vacunacion_aplicada (idPaciente, fechaVacunacion, estadoVacuna, estadoGenerado) values (?,?,?,?)";
		
		String consulta = "insert into boletin (numeroDeBoletin,fechaDeBoletin,resolucionesDesde,resolucionesHasta,"
				+ "ordenanzas,otros,archivo,estadoBoletin,tamanioArchivo) values (?,?,?,?,?,?,?,?,?)";
		Vector<Object>datos = new Vector<Object>();
		datos.add(boletin.getNumeroDeBoletin());
		datos.add(new Date(boletin.getFechaDeBoletin().getTime()));
		datos.add(boletin.getResolucionesDesde());
		datos.add(boletin.getResolucionesHasta());
		datos.add(boletin.getOrdenanzasIncluidas());
		datos.add(boletin.getOtrosDocumentos());
		datos.add(boletin.getDireccionArchivo());
		datos.add(boletin.isEstadoBoletin());
		datos.add(boletin.getTamanioArchivo());
		Conexion conexion = null;		
		int rta = 0;
		try {
		conexion = new Conexion();
		conexion.getConexion().setAutoCommit(false);
		rta = conexion.insert(consulta, datos);
		if(rta != 0){
			//ok, grabamos el log 
			consulta = "insert into log (fecha,servicio,metodo,ip,idUsuario) values (?,?,?,?,?)";
			datos.clear();
			datos.add(new Timestamp(new java.util.Date().getTime()));		
			datos.add("servicioBoletin");
			datos.add("guardarBoletin:" + rta);
		
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(ipAddress);
			datos.add(usuario.getIdUsuario());				
			conexion.insert(consulta, datos);
			conexion.getConexion().commit();
			rta = 1;
		}
		
		} catch (SQLException e) {
			log.error("Error en guardarBoletin(): " + e);
			try {
				conexion.getConexion().rollback();
			} catch (SQLException e1) {
				log.error("Error en rollback(): " + e1);
				
			}
			e.printStackTrace();
		}
		
		
		return rta;
		
		
		
	}

	public ArrayList<Boletin> getBoletinesModificacion() {
		
		//busca sobre la fecha del boletin
				//String consulta = "select  * from boletin b WHERE YEAR(b.fechaDeBoletin) = ?";
		log.info("******");
		log.info("*** Para la edicion de boletines aprovechamos y cargamos los del corriente a�o ***");
		log.info("******");
		String consulta = "select * from boletin b WHERE b.numeroDeBoletin like ?";
				Vector<Object>datos = new Vector<Object>();
				Calendar fecha = Calendar.getInstance();
			    int anio = fecha.get(Calendar.YEAR);
				datos.add(anio);
				ResultSet rs = null;
				ArrayList<Boletin> boletines = new ArrayList<Boletin>();
				try {
					Conexion conexion = new Conexion();
					rs = conexion.queryConsultaConLike(consulta, datos);
					while(rs.next()){
						
						Boletin boletin = new Boletin();
						boletin.setIdBoletin(rs.getInt(1));
						boletin.setNumeroDeBoletin(rs.getString(2));
						boletin.setFechaDeBoletin(rs.getDate(3));
						boletin.setResolucionesDesde(rs.getString(4));
						boletin.setResolucionesHasta(rs.getString(5));
						boletin.setOrdenanzasIncluidas(rs.getString(6));
						boletin.setOtrosDocumentos(rs.getString(7));
						boletin.setDireccionArchivo(rs.getString(8));
						boletin.setEstadoBoletin(true); //<-- always true!				
						boletines.add(boletin);
					}
				} catch (SQLException e) {
					log.info("Error al instanciar Conexion: " + e);					
				}		
		return boletines;
	}

	public int modificarBoletin(Boletin boletin, Usuario usuario) {
		
		String consulta = "update boletin set numeroDeBoletin = ?, fechaDeBoletin = ?, resolucionesDesde = ? ,resolucionesHasta = ?, "
				+ "ordenanzas = ?, otros = ?, archivo = ?, estadoBoletin = ?, tamanioArchivo = ? where ID_boletin = ?";
		Vector<Object>datos = new Vector<Object>();
		datos.add(boletin.getNumeroDeBoletin());
		datos.add(new Date(boletin.getFechaDeBoletin().getTime()));
		datos.add(boletin.getResolucionesDesde());
		datos.add(boletin.getResolucionesHasta());
		datos.add(boletin.getOrdenanzasIncluidas());
		datos.add(boletin.getOtrosDocumentos());
		datos.add(boletin.getDireccionArchivo());
		datos.add(boletin.isEstadoBoletin());
		datos.add(boletin.getTamanioArchivo());
		datos.add(boletin.getIdBoletin());
		Conexion conexion = null;
		int rta = 0;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			conexion.update(consulta, datos);
			
			//ok, grabamos el log 
			consulta = "insert into log (fecha,servicio,metodo,ip,idUsuario) values (?,?,?,?,?)";
			datos.clear();
			datos.add(new Timestamp(new java.util.Date().getTime()));		
			datos.add("servicioBoletin");
			datos.add("modificarBoletin:" + boletin.getIdBoletin());
		
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(ipAddress);
			datos.add(usuario.getIdUsuario());				
			conexion.insert(consulta, datos);
			conexion.getConexion().commit();
			rta = 1;
		} catch (SQLException e) {
			log.error("Error en modificarBoletin():" +e );
			e.printStackTrace();
		}
		
		return rta;
	}

	public int eliminarBoletin(Boletin boletin, Usuario usuario) {
		
		String consulta = "update boletin set estadoBoletin = ? where ID_boletin = ?";
		Vector<Object>datos = new Vector<Object>();
		datos.add(boletin.isEstadoBoletin());
		datos.add(boletin.getIdBoletin());
		Conexion conexion = null;
		int rta = 0;		
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			conexion.update(consulta, datos);
			
			//ok, grabamos el log 
			consulta = "insert into log (fecha,servicio,metodo,ip,idUsuario) values (?,?,?,?,?)";
			datos.clear();
			datos.add(new Timestamp(new java.util.Date().getTime()));		
			datos.add("servicioBoletin");
			datos.add("eliminarBoletin:" + boletin.getIdBoletin());
		
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(ipAddress);
			datos.add(usuario.getIdUsuario());				
			conexion.insert(consulta, datos);
			conexion.getConexion().commit();
			rta = 1;
		} catch (SQLException e) {
			log.error("Error en eliminarBoletin():"+e);
			//e.printStackTrace();
		}
		
		
		return rta;
	}

	public  ArrayList<Boletin>  getBoletines(String anio) {

		//busca sobre la fecha del boletin
		//String consulta = "select  * from boletin b WHERE YEAR(b.fechaDeBoletin) = ?";
		//String consulta = "select * from boletin b WHERE b.numeroDeBoletin like ? and b.fechaDeBoletin <> '0000-00-00' order by b.ID_boletin desc";
		String consulta = "select * from boletin b WHERE b.numeroDeBoletin like ? and b.fechaDeBoletin <> '0000-00-00' and estadoBoletin <> 0 order by b.fechaDeBoletin desc";
		
		Vector<Object>datos = new Vector<Object>();
		//Calendar fecha = Calendar.getInstance();
	    //int anio = fecha.get(Calendar.YEAR);
		datos.add(anio);
		ResultSet rs = null;
		ArrayList<Boletin> boletines = new ArrayList<Boletin>();
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsultaConLike(consulta, datos);
			while(rs.next()){
				
				Boletin boletin = new Boletin();
				boletin.setIdBoletin(rs.getInt(1));
				boletin.setNumeroDeBoletin(rs.getString(2));
				boletin.setFechaDeBoletin(rs.getDate(3));
				boletin.setResolucionesDesde(rs.getString(4));
				boletin.setResolucionesHasta(rs.getString(5));
				boletin.setOrdenanzasIncluidas(rs.getString(6));
				boletin.setOtrosDocumentos(rs.getString(7));
				boletin.setDireccionArchivo("http://www.comodoro.gov.ar/archivos/boletin_oficial/pdf/"+rs.getString(8));
				
				if(!boletin.getResolucionesDesde().isEmpty() || !boletin.getResolucionesHasta().isEmpty()){
					boletin.setResolucionDesdeHasta(boletin.getResolucionesDesde()+" hasta "+boletin.getResolucionesHasta());
				}else {
					boletin.setResolucionDesdeHasta("");
				}
				
				boletin.setEstadoBoletin(true); //<-- always true!				
				boletines.add(boletin);
			}
		} catch (SQLException e) {
			log.error("Error en getBoletines(anio):"+e);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}		
		return boletines;
	}

	public ArrayList<Boletin> getBoletinesTodos() {
		
		//busca sobre la fecha del boletin
				//String consulta = "select  * from boletin b WHERE YEAR(b.fechaDeBoletin) = ?";
				//String consulta = "select * from boletin b WHERE b.estadoBoletin = 1 and b.fechaDeBoletin <> '0000-00-00' order by b.ID_boletin desc";
				String consulta = "select * from boletin b WHERE b.estadoBoletin = 1 and b.fechaDeBoletin <> '0000-00-00' order by b.fechaDeBoletin desc";
				Vector<Object>datos = new Vector<Object>();
				//Calendar fecha = Calendar.getInstance();
			    //int anio = fecha.get(Calendar.YEAR);
				ResultSet rs = null;
				ArrayList<Boletin> boletines = new ArrayList<Boletin>();
				try {
					Conexion conexion = new Conexion();
					rs = conexion.queryConsultaConLike(consulta, datos);
					while(rs.next()){
						
						Boletin boletin = new Boletin();
						boletin.setIdBoletin(rs.getInt(1));
						boletin.setNumeroDeBoletin(rs.getString(2));
						
						boletin.setFechaDeBoletin(rs.getDate(3));
						boletin.setResolucionesDesde(rs.getString(4));
						boletin.setResolucionesHasta(rs.getString(5));
						boletin.setOrdenanzasIncluidas(rs.getString(6));
						boletin.setOtrosDocumentos(rs.getString(7));
						//boletin.setDireccionArchivo("http://www.comodoro.gov.ar/archivos/boletin_oficial/pdf/"+rs.getString(8));
						boletin.setDireccionArchivo("http://www.comodoro.gov.ar/archivos/boletin_oficial/pdf/"+rs.getString(8));
						
						if(!boletin.getResolucionesDesde().isEmpty() || !boletin.getResolucionesHasta().isEmpty()){
							boletin.setResolucionDesdeHasta(boletin.getResolucionesDesde()+" hasta "+boletin.getResolucionesHasta());
						}
						
						boletin.setEstadoBoletin(true); //<-- always true!				
						boletines.add(boletin);
					}
				} catch (SQLException e) {
					log.error("Error en getBoletines(anio):"+e);
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}		
				return boletines;
	}
	

}
