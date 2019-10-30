package com.mcr.boletin.ui;

import com.mcr.boletin.IVistas.ILayoutSistema;
import com.mcr.boletin.presenter.PresenterLayBoletin;
import com.mcr.boletin.presenter.PresenterSesion;
import com.mcr.boletin.servicio.ServicioBoletin;
import com.mcr.boletin.servicio.ServicioSesion;
import com.mcr.boletin.ui.LayoutBoletines.LayoutBoletines;
import com.mcr.boletin.ui.LayoutCredencial.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;

public class LayoutSistema extends VerticalLayout implements ILayoutSistema, ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	private LayoutCredencial layoutCredencial;
	private LayoutBoletines layoutBoletin;
	
	
	
	public LayoutSistema() {
		
		
		setSizeFull();
		//genero 
		addComponent(generarLayoutBoletin());
		this.getLayoutBoletin().getBtnAccesoMCR().addClickListener(this);
		
		
		//instancio LayCredencial
		this.setLayoutCredencial(new LayoutCredencial());		
		PresenterSesion pCredencial = new PresenterSesion(this.getLayoutCredencial(), ServicioSesion.getInstance());
		this.getLayoutCredencial().setHandler(pCredencial);	
	
	}



	private Component generarLayoutBoletin() {
		
		
		//Seteo Presenter
		this.setLayoutBoletin(new LayoutBoletines());
		PresenterLayBoletin presenterLayBoletin = new PresenterLayBoletin(this.getLayoutBoletin(), ServicioBoletin.getInstance());
		this.getLayoutBoletin().setHandler(presenterLayBoletin);	
		return this.getLayoutBoletin();
	}



	public LayoutCredencial getLayoutCredencial() {
		return layoutCredencial;
	}



	public void setLayoutCredencial(LayoutCredencial layoutCredencial) {
		this.layoutCredencial = layoutCredencial;
	}



	public LayoutBoletines getLayoutBoletin() {
		return layoutBoletin;
	}



	public void setLayoutBoletin(LayoutBoletines layoutBoletin) {
		this.layoutBoletin = layoutBoletin;
	}



	@Override
	public void enter(ViewChangeEvent event) {

		
		
	}



	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getLayoutBoletin().getBtnAccesoMCR()){
						
			
			replaceComponent(this.getLayoutBoletin(), this.getLayoutCredencial());
			setComponentAlignment(this.getLayoutCredencial(), Alignment.MIDDLE_CENTER);
			System.out.println("pasoooooo!");
		}
		
	}
	
	
	
	
	
	

}
