package com.mcr.boletin.presenter;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.mcr.boletin.IHandlers.ILayAdministradorHandler;
import com.mcr.boletin.model.Boletin;
import com.mcr.boletin.model.Usuario;
import com.mcr.boletin.servicio.ServicioBoletin;
import com.mcr.boletin.ui.LayAdministrador.LayAdministrador;
import com.vaadin.data.Item;
import com.vaadin.ui.UI;



public class PresenterLayAdministrador implements Serializable, ILayAdministradorHandler{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LayAdministrador view;
	private ServicioBoletin service;
	
	
	
	
	public static Logger log = Logger.getLogger(PresenterLayAdministrador.class);
	
	public PresenterLayAdministrador(LayAdministrador view, ServicioBoletin service) {
		
		
		this.view = view;
		this.service = service;
	}

	@Override
	public void guardarBoletin() {
		
		Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario"); // <--para el log
		
		Boletin boletin = new Boletin();
		boletin.setIdBoletin(0);
		boletin.setNumeroDeBoletin(view.getLayAdmCuerpo().getFormBoletinAlta().getTxtNumeroDeBoletin().getValue()+"/"+view.getLayAdmCuerpo().getFormBoletinAlta().getCmbAnioBoletin().getValue().toString());
		boletin.setFechaDeBoletin(view.getLayAdmCuerpo().getFormBoletinAlta().getDtfFechaDelBoletin().getValue());
		boletin.setResolucionesDesde(view.getLayAdmCuerpo().getFormBoletinAlta().getTxtResolucionesDesde().getValue());
		boletin.setResolucionesHasta(view.getLayAdmCuerpo().getFormBoletinAlta().getTxtResolucionesHasta().getValue());
		boletin.setOrdenanzasIncluidas(view.getLayAdmCuerpo().getFormBoletinAlta().getTxtOrdenanzas().getValue());
		boletin.setOtrosDocumentos(view.getLayAdmCuerpo().getFormBoletinAlta().getTxtOtros().getValue());
		boletin.setDireccionArchivo(view.getLayAdmCuerpo().getFormBoletinAlta().getReceptor().getFile().getName());
		boletin.setEstadoBoletin(true);	
		boletin.setTamanioArchivo(readableFileSize(view.getLayAdmCuerpo().getFormBoletinAlta().getReceptor().getFile().length()));
			
			
		
		
		
		if(service.guardarBoletin(boletin,usuario) != 0){
			view.guardarBoletinOK();
		}else view.guardarBoletinError();
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modificarBoletin() {
		
		Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario"); // <--para el log
		
		Item itemTabla = view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getItem(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getValue());		
			
		Boletin boletin = new Boletin();
		boletin.setIdBoletin(Integer.valueOf(itemTabla.getItemProperty("idBoletin").getValue().toString()));		
		boletin.setNumeroDeBoletin(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtNumeroDeBoletin().getValue()+"/"+view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getCmbAnioBoletin().getValue().toString());
		boletin.setFechaDeBoletin(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getDtfFechaDelBoletin().getValue());
		boletin.setResolucionesDesde(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtResolucionesDesde().getValue());
		boletin.setResolucionesHasta(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtResolucionesHasta().getValue());
		boletin.setOrdenanzasIncluidas(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtOrdenanzas().getValue());
		boletin.setOtrosDocumentos(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtOtros().getValue());
		boletin.setEstadoBoletin(true);
		
		if(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getReceptor().getFile() != null){
			boletin.setDireccionArchivo(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getReceptor().getFile().getName());
		}else boletin.setDireccionArchivo(itemTabla.getItemProperty("direccionArchivo").getValue().toString());
		
		
		if(service.modificarBoletin(boletin,usuario) != 0){
			view.modificarBoletinOK();
			itemTabla.getItemProperty("numeroDeBoletin").setValue(boletin.getNumeroDeBoletin());;
			itemTabla.getItemProperty("fechaDeBoletin").setValue(boletin.getFechaDeBoletin());
			itemTabla.getItemProperty("resolucionesDesde").setValue(boletin.getResolucionesDesde());
			itemTabla.getItemProperty("resolucionesHasta").setValue(boletin.getResolucionesHasta());
			itemTabla.getItemProperty("ordenanzasIncluidas").setValue(boletin.getOrdenanzasIncluidas());
			itemTabla.getItemProperty("otrosDocumentos").setValue(boletin.getOtrosDocumentos());
			itemTabla.getItemProperty("direccionArchivo").setValue(boletin.getDireccionArchivo());		
		}else view.modificarBoletinError();		
		
		
		
		
		
	}

	@Override
	public void eliminarBoletin() {
		
		Usuario usuario = (Usuario) UI.getCurrent().getSession().getAttribute("usuario"); // <--para el log		
		Item itemTabla = view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getItem(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getValue());
		
		Boletin boletin = new Boletin();
		boletin.setIdBoletin(Integer.valueOf(itemTabla.getItemProperty("idBoletin").getValue().toString()));
		boletin.setEstadoBoletin(false);
		if(service.eliminarBoletin(boletin,usuario) != 0){		
			view.eliminarBoletinOK();
			view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().removeItem(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getValue());
		}else view.eliminarBoletinError();
	}

	@Override
	public void cargarBoletinesPorAnio() {
		
		view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getContainerDataSource().removeAllItems();	
		ArrayList<Boletin> boletines = service.getBoletines(view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getCmbSeleccionAnio().getValue().toString());	
		Iterator<Boletin> iteradorBoletines = boletines.iterator();
		while(iteradorBoletines.hasNext()){
			
			Boletin boletin = iteradorBoletines.next();
			view.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getContainerDataSource().addItem(boletin);
		}
		
	}
	
	public static String readableFileSize(long size) {
	    if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}
