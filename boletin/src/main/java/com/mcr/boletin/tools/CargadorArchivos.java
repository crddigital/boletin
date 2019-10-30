package com.mcr.boletin.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.log4j.Logger;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class CargadorArchivos implements Receiver, SucceededListener, ProgressListener,StartedListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public File file;
	public ProgressBar progressBar;
	private static final Logger log = Logger.getLogger(CargadorArchivos.class);
	
	
	public CargadorArchivos(ProgressBar progressBar){
		
		this.progressBar = progressBar;
		
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {


		Notification.show("Archivo correctamente subido " ,Notification.Type.TRAY_NOTIFICATION);
		
	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		
					
		
		  // Create upload stream
        FileOutputStream fos = null; // Stream to write to
        if(!filename.isEmpty()){
        
        try {
            // Open the file for writing.
        	//this.setFile(new File("C:\\pendrive\\"+filename)); //<- lugar donde va a parar el archivo subido (local)
        	
        	
        	
        	this.setFile(new File(File.separator+"home"+File.separator+"comodoro"+File.separator+"public_html"+File.separator+"archivos"+File.separator+"boletin_oficial"+File.separator+"pdf"+File.separator+""+filename));////<- lugar donde va a parar el archivo subido (host)
        	
        	
        	if(getFile().exists()) {
        		
        		log.info(file.getName() + "es solo lectura: "+file.setReadOnly());
                log.info(file.getName() + "es para lectura: "+file.setReadable(true));
        	}
        	
        	
        				
            //fos = new FileOutputStream(file);
        	fos = new FileOutputStream(this.getFile());
        	
        	
        	
        	
        	
        	
        } catch (final java.io.FileNotFoundException e) {
            new Notification("No es posible abrir el archivo <br/>", e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
            log.error("No es posible abrir el archivo :" + e);
            return null;
        }
        }else{
        	new Notification("Primero hay que seleccionar la resolución a cargar", Notification.Type.ERROR_MESSAGE);        	
        	return new NullOutputStream();
        }
        
        return fos; // Return the output stream to write to
        
	}

	@Override
	public void updateProgress(long readBytes, long contentLength) {
		            
		progressBar.setValue(new Float(readBytes / (float) contentLength));
       // textualProgress.setValue("Processed " + readBytes + " bytes of " + contentLength);
        
		
	}

	@Override
	public void uploadStarted(StartedEvent event) {
		
					
		Notification.show("Inicio de carga del archivo. Aguarde..", Type.TRAY_NOTIFICATION);
		
		
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	

}
