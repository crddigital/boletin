package com.mcr.boletin.ui.responsive;

import com.mcr.boletin.IVistas.ILayoutSistema;
import com.mcr.boletin.presenter.PresenterLayBoletin;
import com.mcr.boletin.servicio.ServicioBoletin;
import com.mcr.boletin.ui.LayoutBoletinesRespo.LayoutBoletinesResponsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LayoutSistemaResponsive extends VerticalLayout implements ILayoutSistema, ClickListener{ 

	
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	private LayoutBoletinesResponsive layoutBoletinesResponsive;
	
	
	
	
	
	public LayoutSistemaResponsive() {
	
		setSizeFull();
		//genero 
		addComponent(generarLayoutBoletin());
		//this.getLayoutBoletinesResponsive().getBtnAccesoMCR().addClickListener(this);
		
		
		
	}
	
	private Component generarLayoutBoletin() {
		
		
		//Seteo Presenter
		this.setLayoutBoletinesResponsive(new LayoutBoletinesResponsive());
		PresenterLayBoletin presenterLayBoletin = new PresenterLayBoletin(this.getLayoutBoletinesResponsive(), ServicioBoletin.getInstance());
		this.getLayoutBoletinesResponsive().setHandler(presenterLayBoletin);	
		return this.getLayoutBoletinesResponsive();
		
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		
		
	}
	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
	public LayoutBoletinesResponsive getLayoutBoletinesResponsive() {
		return layoutBoletinesResponsive;
	}
	public void setLayoutBoletinesResponsive(LayoutBoletinesResponsive layoutBoletinesResponsive) {
		this.layoutBoletinesResponsive = layoutBoletinesResponsive;
	}
}
