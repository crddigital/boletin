package com.mcr.boletin.ui.LayAdministrador;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;


public class LayAdministradorEncabezado extends HorizontalLayout{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label lblEncabezado;
	private Button btnAltaBoletin;
	private Button btnBajaModificacion;
	private Button btnSalir;
	

	public LayAdministradorEncabezado() {
		
		
		setSpacing(true);
		//addComponent(generarEncabezado());
		//addComponent(generarMenu());		
		//addStyleName("view-encabezado");
		//setSizeUndefined();
		//setWidth("250px");
		
		setSizeFull();
		addComponent(generarEncabezado());
		addStyleName("view-encabezado");
		setHeight("100px");
		
		
		setComponentAlignment(this.getLblEncabezado(), Alignment.TOP_RIGHT);
		
	}
	
//	private Component generarMenu() {
//		
//		VerticalLayout menu = new VerticalLayout();
//		menu.setSpacing(true);
//		
//		this.setBtnSalir(new Button("Salir"));
//		this.getBtnSalir().addStyleName(ValoTheme.BUTTON_LINK);
//		this.setBtnAltaBoletin(new Button("Alta Boletin"));
//		this.getBtnAltaBoletin().addStyleName(ValoTheme.BUTTON_LINK);
//		this.setBtnBajaModificacion(new Button("Baja - Modificacion Bolet�n"));
//		this.getBtnBajaModificacion().addStyleName(ValoTheme.BUTTON_LINK);
//		menu.addComponent(this.getBtnAltaBoletin());
//		menu.addComponent(this.getBtnBajaModificacion());
//		menu.addComponent(this.getBtnSalir());
//		
//		
//		return menu;
//	}

	private Component generarEncabezado() {
		
		this.setLblEncabezado(new Label("", ContentMode.HTML));
		return this.getLblEncabezado();
	}

	public Label getLblEncabezado() {
		return lblEncabezado;
	}

	public void setLblEncabezado(Label lblEncabezado) {
		this.lblEncabezado = lblEncabezado;
	}

	public Button getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(Button btnSalir) {
		this.btnSalir = btnSalir;
	}

	public Button getBtnAltaBoletin() {
		return btnAltaBoletin;
	}

	public void setBtnAltaBoletin(Button btnAltaBoletin) {
		this.btnAltaBoletin = btnAltaBoletin;
	}

	public Button getBtnBajaModificacion() {
		return btnBajaModificacion;
	}

	public void setBtnBajaModificacion(Button btnBajaModificacion) {
		this.btnBajaModificacion = btnBajaModificacion;
	}

	


}
