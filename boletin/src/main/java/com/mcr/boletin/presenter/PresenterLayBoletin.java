package com.mcr.boletin.presenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import com.mcr.boletin.IHandlers.IlayoutBoletinesHandler;
import com.mcr.boletin.model.Boletin;
import com.mcr.boletin.servicio.ServicioBoletin;
import com.mcr.boletin.ui.LayoutBoletines.LayoutBoletines;
import com.mcr.boletin.ui.LayoutBoletinesRespo.LayoutBoletinesResponsive;

public class PresenterLayBoletin implements Serializable, IlayoutBoletinesHandler{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LayoutBoletines view;
	private LayoutBoletinesResponsive viewResponsive;
	private ServicioBoletin service;
	
	public PresenterLayBoletin(LayoutBoletines view, ServicioBoletin service) {
		
		this.view = view;
		this.service = service;
	
	}
	
	public PresenterLayBoletin(LayoutBoletinesResponsive viewResponsive, ServicioBoletin service) {
		
		this.viewResponsive = viewResponsive;
		this.service = service;
	
	}

	@Override
	public void cargarBoletinesPorAnio() {
		
		view.getTablaBoletines().getContainerDataSource().removeAllItems();	
		ArrayList<Boletin> boletines = service.getBoletines(view.getCmbSeleccionAnio().getValue().toString());	
		Iterator<Boletin> iteradorBoletines = boletines.iterator();
		while(iteradorBoletines.hasNext()){
			
			Boletin boletin = iteradorBoletines.next();
			view.getTablaBoletines().getContainerDataSource().addItem(boletin);
		}
		
	}

	@Override
	public void cargarBoletinesPorAnioResponsive() {
		
		viewResponsive.getTablaBoletines().getContainerDataSource().removeAllItems();	
		ArrayList<Boletin> boletines = service.getBoletines(viewResponsive.getCmbSeleccionAnio().getValue().toString());	
		Iterator<Boletin> iteradorBoletines = boletines.iterator();
		while(iteradorBoletines.hasNext()){
			
			Boletin boletin = iteradorBoletines.next();
			viewResponsive.getTablaBoletines().getContainerDataSource().addItem(boletin);
		}
		
	}

	@Override
	public void cargarBoletinesTodos() {
		
		view.getTablaBoletines().getContainerDataSource().removeAllItems();	
		ArrayList<Boletin> boletines = service.getBoletinesTodos();	
		Iterator<Boletin> iteradorBoletines = boletines.iterator();
		while(iteradorBoletines.hasNext()){
			
			Boletin boletin = iteradorBoletines.next();
			view.getTablaBoletines().getContainerDataSource().addItem(boletin);
		}
		
	}

	@Override
	public void cargarBoletinesTodosResponvise() {
		
		viewResponsive.getTablaBoletines().getContainerDataSource().removeAllItems();	
		ArrayList<Boletin> boletines = service.getBoletinesTodos();	
		Iterator<Boletin> iteradorBoletines = boletines.iterator();
		while(iteradorBoletines.hasNext()){
			
			Boletin boletin = iteradorBoletines.next();
			viewResponsive.getTablaBoletines().getContainerDataSource().addItem(boletin);
		}
		
		
	}

	
}
