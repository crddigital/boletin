package com.mcr.boletin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.log4j.Logger;

public class Conexion {

		private static String DRIVER = "com.mysql.jdbc.Driver";
		private static String URL = "jdbc:mysql://190.7.61.146:3306/comodoro_boletin";		
		private static String USUARIO = "";
	    private static String PASSWORD = "";       
		private Connection conexion;
		private PreparedStatement ps;
		private ResultSet rs;
		
		private static final Logger log = Logger.getLogger(Conexion.class);
		
		
		public Conexion() throws SQLException{
		//	datosConexion();
			this.setConexion(null);
			this.setPs(null);
			this.setRs(null);
			this.setConexion(DriverManager.getConnection(URL,USUARIO,PASSWORD));	
			//datosConexion();
		}


		public int queryInsercion(String consulta, Vector<Object> datos) throws SQLException {

			int idElemento = 0;

			try {
				Class.forName(DRIVER);
			} catch (Exception e) {
				log.error("Imposible registrar el controlador", e);
				System.exit(1);
			}

			try { 			 
				this.setPs((PreparedStatement) this.getConexion().prepareStatement(consulta,PreparedStatement.RETURN_GENERATED_KEYS));
				int i = 0;
				Enumeration<Object> e = datos.elements();
				while (e.hasMoreElements()){
					i++;			 
					Object elem = e.nextElement();
					
					if (elem != null){
						if (elem.getClass().getSimpleName().equals("String"))
							this.getPs().setString(i,(String)elem);  
						else if (elem.getClass().getSimpleName().equals("Integer"))
							this.getPs().setInt(i,(Integer)elem);
						else if (elem.getClass().getSimpleName().equals("Float"))
							this.getPs().setFloat(i,(Float)elem);
						else if (elem.getClass().getSimpleName().equals("Double"))
							this.getPs().setDouble(i,(Double)elem);
						else if (elem.getClass().getSimpleName().equals("Boolean"))
							this.getPs().setBoolean(i,(Boolean)elem);	
						else if (elem.getClass().getSimpleName().equals("Date"))
							this.getPs().setDate(i, (java.sql.Date)elem);	
						else if (elem.getClass().getSimpleName().equals("Long"))
							this.getPs().setLong(i, (Long)elem);	
						else if (elem.getClass().getSimpleName().equals("Byte"))
							this.getPs().setByte(i, (Byte)elem);
						else if (elem.getClass().getSimpleName().equals("Timestamp"))
							this.getPs().setTimestamp(i, (Timestamp)elem);	
					}
					
				}
				//System.out.println(this.getPs().toString()+ "\nfecha: "+new Date());
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				if (this.getPs().executeUpdate() == 1){ 				 
					this.setRs((ResultSet) this.getPs().getGeneratedKeys());

					if(this.getRs().next()){
						idElemento = this.getRs().getInt(1);
					}
				}
				else {
					//System.out.println("ERROR, en la base de datos");
					log.error(this.getPs().toString()+ "\nfecha: "+new Date());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	            // Se cierran los recursos de base de datos.
	            try {
	                if (this.getPs() != null) 
	                	this.getPs().close();
	                if (this.getRs() != null)
	                	this.getRs().close();
	                if (this.getConexion() != null)
	                	this.getConexion().close();
	                
	            } catch (SQLException e) {
	                System.out.println("El borrado no ha podido cerrar Statement.");
	            	log.error("El borrado no ha podido cerrar Statement.");
	            }
	        }
			return idElemento;

		}
		public void registrar_contorlador(){
			try {
				Class.forName(DRIVER);
			} catch (Exception e) {
				log.error("Imposible registrar el controlador.");
				System.exit(1);
			}

		}
		public int insert (String consulta, Vector<Object> datos) throws SQLException {

			int idElemento = 0;

						 
				this.setPs((PreparedStatement) this.getConexion().prepareStatement(consulta,PreparedStatement.RETURN_GENERATED_KEYS));
				int i = 0;
				Enumeration<Object> e = datos.elements();
				while (e.hasMoreElements()){
					i++;			 
					Object elem = e.nextElement();
					
					if (elem != null){
						if (elem.getClass().getSimpleName().equals("String"))
							this.getPs().setString(i,(String)elem);  
						else if (elem.getClass().getSimpleName().equals("Integer"))
							this.getPs().setInt(i,(Integer)elem);
						else if (elem.getClass().getSimpleName().equals("Float"))
							this.getPs().setFloat(i,(Float)elem);
						else if (elem.getClass().getSimpleName().equals("Double"))
							this.getPs().setDouble(i,(Double)elem);
						else if (elem.getClass().getSimpleName().equals("Boolean"))
							this.getPs().setBoolean(i,(Boolean)elem);	
						else if (elem.getClass().getSimpleName().equals("Date"))
							this.getPs().setDate(i, (java.sql.Date)elem);	
						else if (elem.getClass().getSimpleName().equals("Long"))
							this.getPs().setLong(i, (Long)elem);	
						else if (elem.getClass().getSimpleName().equals("Byte"))
							this.getPs().setByte(i, (Byte)elem);
						else if (elem.getClass().getSimpleName().equals("Timestamp"))
							this.getPs().setTimestamp(i, (Timestamp)elem);	
					}
					
				}
				//System.out.println(this.getPs().toString());
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				if (this.getPs().executeUpdate() == 1){ 				 
					this.setRs((ResultSet) this.getPs().getGeneratedKeys());

					if(this.getRs().next()){
						idElemento = this.getRs().getInt(1);
					}
				}
				else  log.error("Error en la base de datos");//System.out.println("ERROR, en la base de datos");
			
			return idElemento;
		}
		public int delete(String consulta, Vector<Object> datos) throws SQLException {

			//retorna el numero de registros que borro
			
			int resultado= 0; 
									 
				this.setPs((PreparedStatement)this.getConexion().prepareStatement(consulta));	 		
				int i = 0;			 //indice del vector
				Enumeration<Object> e = datos.elements();
				while (e.hasMoreElements()){
					i++;
					Object elem = e.nextElement();
					if (elem != null){
						if (elem.getClass().getSimpleName().equals("String"))
							this.getPs().setString(i,(String)elem);  
						else if (elem.getClass().getSimpleName().equals("Integer"))
							this.getPs().setInt(i,(Integer)elem);
						else if (elem.getClass().getSimpleName().equals("Float"))
							this.getPs().setFloat(i,(Float)elem);
						else if (elem.getClass().getSimpleName().equals("Double"))
							this.getPs().setDouble(i,(Double)elem);
						else if (elem.getClass().getSimpleName().equals("Boolean"))
							this.getPs().setBoolean(i,(Boolean)elem);	
						else if (elem.getClass().getSimpleName().equals("Date"))
							this.getPs().setDate(i, (java.sql.Date)elem);	
						else if (elem.getClass().getSimpleName().equals("Long"))
							this.getPs().setLong(i, (Long)elem);	
						else if (elem.getClass().getSimpleName().equals("Byte"))
							this.getPs().setByte(i, (Byte)elem);	
					}
				}
				//System.out.println(this.getPs().toString());
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				resultado = this.getPs().executeUpdate();
			
			return resultado;
		}
		
		
		
		public int update(String consulta, Vector<Object> datos) throws SQLException {

			//modifica valores de algun objeto de la base de datos
			//retorn el numero de registros modificados
			
			int resultado = 0;   	    	   
			
			try { 						 
				this.setPs((PreparedStatement)this.getConexion().prepareStatement(consulta));	 		

				int i = 0;
				Enumeration<Object> e = datos.elements();
				
				while (e.hasMoreElements()){
					i++;
					Object elem = e.nextElement();
					if (elem != null){
						if (elem.getClass().getSimpleName().equals("String"))
							this.getPs().setString(i,(String)elem);  
						else if (elem.getClass().getSimpleName().equals("Integer"))
							this.getPs().setInt(i,(Integer)elem);
						else if (elem.getClass().getSimpleName().equals("Float"))
							this.getPs().setFloat(i,(Float)elem);
						else if (elem.getClass().getSimpleName().equals("Double"))
							this.getPs().setDouble(i,(Double)elem);
						else if (elem.getClass().getSimpleName().equals("Boolean"))
							this.getPs().setBoolean(i,(Boolean)elem);	
						else if (elem.getClass().getSimpleName().equals("Date"))
							this.getPs().setDate(i, (java.sql.Date)elem);	
						else if (elem.getClass().getSimpleName().equals("Long"))
							this.getPs().setLong(i, (Long)elem);	
						else if (elem.getClass().getSimpleName().equals("Byte"))
							this.getPs().setByte(i, (Byte)elem);	
					}
				}
				//System.out.println(this.getPs().toString());
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				resultado = this.getPs().executeUpdate();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resultado;
			
		}
		
		
		
		public ResultSet select (String consulta, Vector<Object> datos) throws SQLException {
			
			
			try { 					 
				this.setPs((PreparedStatement)this.getConexion().prepareStatement(consulta));
				int i = 0;
				Enumeration<Object> e = datos.elements();
				while (e.hasMoreElements()){
					i++;
					Object elem = e.nextElement();
					if (elem != null){
						if (elem.getClass().getSimpleName().equals("String"))
							this.getPs().setString(i,(String)elem);  
						else if (elem.getClass().getSimpleName().equals("Integer"))
							this.getPs().setInt(i,(Integer)elem);
						else if (elem.getClass().getSimpleName().equals("Float"))
							this.getPs().setFloat(i,(Float)elem);
						else if (elem.getClass().getSimpleName().equals("Double"))
							this.getPs().setDouble(i,(Double)elem);
						else if (elem.getClass().getSimpleName().equals("Boolean"))
							this.getPs().setBoolean(i,(Boolean)elem);	
						else if (elem.getClass().getSimpleName().equals("Date"))
							this.getPs().setDate(i, (java.sql.Date)elem);	
						else if (elem.getClass().getSimpleName().equals("Long"))
							this.getPs().setLong(i, (Long)elem);	
						else if (elem.getClass().getSimpleName().equals("Byte"))
							this.getPs().setByte(i, (Byte)elem);	
					}
				}
				//System.out.println(this.getPs().toString());
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				this.setRs((ResultSet)this.getPs().executeQuery());				
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.getRs();
			
		}

		
		public ResultSet queryConsulta(String consulta, Vector<Object> datos) throws SQLException {
			
			try {
				Class.forName(DRIVER);
			} catch (Exception e) {
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				System.exit(1);
			}


			try { 					 
				this.setPs((PreparedStatement)this.getConexion().prepareStatement(consulta));
				int i = 0;
				Enumeration<Object> e = datos.elements();				
				while (e.hasMoreElements()){
					i++;
					Object elem = e.nextElement();					
					if (elem != null){
						if (elem.getClass().getSimpleName().equals("String"))
							this.getPs().setString(i,(String)elem);  
						else if (elem.getClass().getSimpleName().equals("Integer"))
							this.getPs().setInt(i,(Integer)elem);
						else if (elem.getClass().getSimpleName().equals("Float"))
							this.getPs().setFloat(i,(Float)elem);
						else if (elem.getClass().getSimpleName().equals("Double"))
							this.getPs().setDouble(i,(Double)elem);
						else if (elem.getClass().getSimpleName().equals("Boolean"))
							this.getPs().setBoolean(i,(Boolean)elem);	
						else if (elem.getClass().getSimpleName().equals("Date"))
							this.getPs().setDate(i, (java.sql.Date)elem);	
						else if (elem.getClass().getSimpleName().equals("Long"))
							this.getPs().setLong(i, (Long)elem);	
						else if (elem.getClass().getSimpleName().equals("Byte"))
							this.getPs().setByte(i, (Byte)elem);	
					}
				}
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());		
				this.setRs((ResultSet)this.getPs().executeQuery());				
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.getRs();
			
		}


		public int queryBorrar(String consulta, Vector<Object> datos) throws SQLException {

			//retorna el numero de registros que borro
			
			int resultado= 0; 
			try {
				Class.forName(DRIVER);
			} catch (Exception e) {
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				System.exit(1);
			}


			try { 						 
				this.setPs((PreparedStatement)this.getConexion().prepareStatement(consulta));	 		
				int i = 0;			 //indice del vector
				Enumeration<Object> e = datos.elements();
				while (e.hasMoreElements()){
					i++;
					Object elem = e.nextElement();
					if (elem != null){
						if (elem.getClass().getSimpleName().equals("String"))
							this.getPs().setString(i,(String)elem);  
						else if (elem.getClass().getSimpleName().equals("Integer"))
							this.getPs().setInt(i,(Integer)elem);
						else if (elem.getClass().getSimpleName().equals("Float"))
							this.getPs().setFloat(i,(Float)elem);
						else if (elem.getClass().getSimpleName().equals("Double"))
							this.getPs().setDouble(i,(Double)elem);
						else if (elem.getClass().getSimpleName().equals("Boolean"))
							this.getPs().setBoolean(i,(Boolean)elem);	
						else if (elem.getClass().getSimpleName().equals("Date"))
							this.getPs().setDate(i, (java.sql.Date)elem);	
						else if (elem.getClass().getSimpleName().equals("Long"))
							this.getPs().setLong(i, (Long)elem);	
						else if (elem.getClass().getSimpleName().equals("Byte"))
							this.getPs().setByte(i, (Byte)elem);	
					}
				}
				resultado = this.getPs().executeUpdate();
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
	            // Se cierran los recursos de base de datos.
	            try {
	                if (this.getPs() != null) 
	                	this.getPs().close();
	                if (this.getConexion() != null)
	                	this.getConexion().close();
	            } catch (SQLException e) {
	                //System.out.println("El borrado no ha podido cerrar Statement.");
	            	log.info(this.getPs().toString()+ "\nfecha: "+new Date());
	            }
	        }
			return resultado;
		}




		public int queryActualizacion(String consulta, Vector<Object> datos) throws SQLException {

			//modifica valores de algun objeto de la base de datos
			//retorn el numero de registros modificados
			
			int resultado = 0;   	    	   
			// Se registra el controlador JDBC nativo. Si no se puede
			// registrar el controlador, la prueba no puede continuar.
			try {
				Class.forName(DRIVER);
			} catch (Exception e) {
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				System.exit(1);
			}


			try { 						 
				this.setPs((PreparedStatement)this.getConexion().prepareStatement(consulta));	 		

				int i = 0;
				Enumeration<Object> e = datos.elements();
				
				while (e.hasMoreElements()){
					i++;
					Object elem = e.nextElement();
					if (elem != null){
						if (elem.getClass().getSimpleName().equals("String"))
							this.getPs().setString(i,(String)elem);  
						else if (elem.getClass().getSimpleName().equals("Integer"))
							this.getPs().setInt(i,(Integer)elem);
						else if (elem.getClass().getSimpleName().equals("Float"))
							this.getPs().setFloat(i,(Float)elem);
						else if (elem.getClass().getSimpleName().equals("Double"))
							this.getPs().setDouble(i,(Double)elem);
						else if (elem.getClass().getSimpleName().equals("Boolean"))
							this.getPs().setBoolean(i,(Boolean)elem);	
						else if (elem.getClass().getSimpleName().equals("Date"))
							this.getPs().setDate(i, (java.sql.Date)elem);	
						else if (elem.getClass().getSimpleName().equals("Long"))
							this.getPs().setLong(i, (Long)elem);	
						else if (elem.getClass().getSimpleName().equals("Byte"))
							this.getPs().setByte(i, (Byte)elem);	
					}
				}
				//System.out.println(this.getPs().toString());
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				resultado = this.getPs().executeUpdate();	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
	            // Se cierran los recursos de base de datos.
	            try {
	                if (this.getPs() != null) 
	                	this.getPs().close(); 
	                if (this.getConexion() != null)
	                	getConexion().close();
	            } catch (SQLException e) {
	                //System.out.println("El borrado no ha podido cerrar Statement.");
	            	log.info(this.getPs().toString()+ "\nfecha: "+new Date());
	            }
	        }
			return resultado;
			
		}
		
		public void datosConexion(){			
			
			System.out.println("---------------------------");
			System.out.println("Estos son los datos");
			System.out.println("ip:" + URL);
			System.out.println("pass:" + PASSWORD);
			System.out.println("user:" + USUARIO);		
			System.out.println("----------------------------");
					
		}

		public void cerrarRecursos() {
			
			try {
				if (this.getPs() != null){
					this.getPs().close();
					}
				if (this.getRs() != null){
					this.getRs().close();
				}	
				if (this.getConexion() != null){
					this.getConexion().close();
				}					
			}catch (SQLException e) {
					System.out.println("Error al cerrar los recursos de la BD");
					e.printStackTrace();
					}
		}


		public Connection getConexion() {
			return conexion;
		}


		public void setConexion(Connection conexion) {
			this.conexion = conexion;
		}


		public PreparedStatement getPs() {
			return ps;
		}


		public void setPs(PreparedStatement ps) {
			this.ps = ps;
		}


		public ResultSet getRs() {
			return rs;
		}


		public void setRs(ResultSet rs) {
			this.rs = rs;
		}


		public ResultSet queryConsultaConLike(String consulta,
				Vector<Object> datos) {
			
			try {
				Class.forName(DRIVER);
			} catch (Exception e) {
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());
				System.exit(1);
			}


			try { 					 
				this.setPs((PreparedStatement)this.getConexion().prepareStatement(consulta));
				int i = 0;
				Enumeration<Object> e = datos.elements();				
				while (e.hasMoreElements()){
					i++;
					Object elem = e.nextElement();					
					if (elem != null){
						if (elem.getClass().getSimpleName().equals("String"))
							this.getPs().setString(i,(String)"%"+elem+"%");  //'%2016%'
						else if (elem.getClass().getSimpleName().equals("Integer"))
							this.getPs().setInt(i,(Integer)elem);
						else if (elem.getClass().getSimpleName().equals("Float"))
							this.getPs().setFloat(i,(Float)elem);
						else if (elem.getClass().getSimpleName().equals("Double"))
							this.getPs().setDouble(i,(Double)elem);
						else if (elem.getClass().getSimpleName().equals("Boolean"))
							this.getPs().setBoolean(i,(Boolean)elem);	
						else if (elem.getClass().getSimpleName().equals("Date"))
							this.getPs().setDate(i, (java.sql.Date)elem);	
						else if (elem.getClass().getSimpleName().equals("Long"))
							this.getPs().setLong(i, (Long)elem);	
						else if (elem.getClass().getSimpleName().equals("Byte"))
							this.getPs().setByte(i, (Byte)elem);	
					}
				}
				log.info(this.getPs().toString()+ "\nfecha: "+new Date());		
				this.setRs((ResultSet)this.getPs().executeQuery());				
	
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Error en queryConsultaConLike():"+ e);
			}
			
			return this.getRs();
			
		}
}

		

	









