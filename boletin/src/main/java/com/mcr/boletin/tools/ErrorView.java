package com.mcr.boletin.tools;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ErrorView extends VerticalLayout implements View{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label explicacion;
	
	public ErrorView(){
		
		setMargin(true);
		setSpacing(true);
		
		Label header = new Label("<p><strong>La vista no puede ser encontrada<strong></p>", ContentMode.HTML);
		header.addStyleName(ValoTheme.LABEL_H1);
		addComponent(header);
		addComponent(explicacion = new Label());
	}
	

	@Override
	public void enter(ViewChangeEvent event) {
		
		explicacion.setValue(String.format("Esta intentando acceder a una vista -> ('%s')) que no existe",event.getViewName()));
		
	}

	public Label getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(Label explicacion) {
		this.explicacion = explicacion;
	}
	
	
	

}
