package com.mcr.boletin;

import javax.servlet.annotation.WebServlet;

import com.mcr.boletin.presenter.PresenterLayAdministrador;
import com.mcr.boletin.servicio.ServicioBoletin;
import com.mcr.boletin.ui.LayoutSistema;
import com.mcr.boletin.ui.LayAdministrador.*;
import com.mcr.boletin.ui.responsive.LayoutSistemaResponsive;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;


@SuppressWarnings("serial")
@Theme("boletin")
@Title("Boletin Oficial MCR")
public class BoletinUI extends UI {

	Navigator navigator;
	
	public enum LayoutMode{
		SMALL,DESTOP
	}
	
	
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = BoletinUI.class, widgetset = "com.mcr.boletin.widgetset.BoletinWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
	
		
		Responsive.makeResponsive(this);
		navigator = new Navigator(this,this);	
				
		
		
		if(getLayoutMode() == LayoutMode.SMALL){
	
			//Devices
			//Notification.show("Celulares, tablets..");
			LayoutSistemaResponsive layoutSistemaResponsive = new LayoutSistemaResponsive();				
			navigator.addView("", layoutSistemaResponsive);		
			
		}else{
						
			LayoutSistema layoutSistema = new LayoutSistema();
			
			LayAdministrador layoutAdministrador = new LayAdministrador();
			PresenterLayAdministrador presenterLayAdministrador = new PresenterLayAdministrador(layoutAdministrador, ServicioBoletin.getInstance());
			layoutAdministrador.setHandler(presenterLayAdministrador);
						
			navigator.addView("", layoutSistema);		
			navigator.addView(LayAdministrador.NAME, layoutAdministrador);		
		}
		
		
		
		
		
		
	}

	private LayoutMode getLayoutMode() {
		
		int width = Page.getCurrent().getBrowserWindowWidth();
		
		Notification.show(String.valueOf(width));
		
		if(width < 800){
			return LayoutMode.SMALL;
		}else return LayoutMode.DESTOP;
		
	}

}