package com.mcr.boletin.ui.LayAdministrador;

import org.apache.log4j.Logger;
import org.vaadin.dialogs.ConfirmDialog;

import com.mcr.boletin.IHandlers.ILayAdministradorHandler;
import com.mcr.boletin.IVistas.ILayAdministrador;
import com.mcr.boletin.model.Usuario;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;



public class LayAdministrador extends VerticalLayout implements ILayAdministrador, ClickListener , ValueChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LayAdministradorEncabezado layAdmEncabezado;
	private LayAdministradorCuerpo layAdmCuerpo;
	private ILayAdministradorHandler handler;	
	public static String NAME = "layAdministrador"; 
	private String direccionPDF = "";
	
	public static Logger log = Logger.getLogger(LayAdministrador.class);
	BrowserWindowOpener fd = new BrowserWindowOpener("");
	
	public LayAdministrador() {
		
		addComponent(generarEncabezado());
		addComponent(generarCuerpo());
	
		
		//escuchadores
		
		//alta
		this.getLayAdmCuerpo().getFormBoletinAlta().getBtnGuardar().addClickListener(this);
		this.getLayAdmCuerpo().getFormBoletinAlta().getBtnLimpiar().addClickListener(this);
		
		//modificacion-baja
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnGuardar().addClickListener(this);
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnLimpiar().addClickListener(this);
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnEliminar().addClickListener(this);		
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnGenerarPDF().addClickListener(this);	
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getCmbSeleccionAnio().addValueChangeListener(this);
		fd.extend(this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnVerPDF());
		
		
	
	}
	

	

	private Component generarCuerpo() {
		
		this.setLayAdmCuerpo(new LayAdministradorCuerpo());
		return this.getLayAdmCuerpo();
	}




	private Component generarEncabezado() {
		
		this.setLayAdmEncabezado(new LayAdministradorEncabezado());
		return this.getLayAdmEncabezado();
	}


	@Override
	public void enter(ViewChangeEvent event) {
	
		Usuario usuario = (Usuario)UI.getCurrent().getSession().getAttribute("usuario");
		this.getLayAdmEncabezado().getLblEncabezado().setValue("<p><strong>Usuario: " + usuario.getApellido()+","+usuario.getNombre()+
				"<br>Perfil: " + usuario.getPerfil().recuperarPerfil().toUpperCase()+"<br></strong><p>");
		
	}


	public LayAdministradorEncabezado getLayAdmEncabezado() {
		return layAdmEncabezado;
	}




	public void setLayAdmEncabezado(LayAdministradorEncabezado layAdmEncabezado) {
		this.layAdmEncabezado = layAdmEncabezado;
	}




	public LayAdministradorCuerpo getLayAdmCuerpo() {
		return layAdmCuerpo;
	}




	public void setLayAdmCuerpo(LayAdministradorCuerpo layAdmCuerpo) {
		this.layAdmCuerpo = layAdmCuerpo;
	}
	



	@Override
	public void buttonClick(ClickEvent event) {
				
		
		if(event.getSource() == this.getLayAdmCuerpo().getFormBoletinAlta().getBtnLimpiar()){
			
			limpiar();
			
		}
		if(event.getSource() == this.getLayAdmCuerpo().getFormBoletinAlta().getBtnGuardar()){
			
			if(!this.getLayAdmCuerpo().getFormBoletinAlta().getTxtNumeroDeBoletin().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinAlta().getCmbAnioBoletin().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinAlta().getTxtOrdenanzas().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinAlta().getTxtOtros().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinAlta().getTxtResolucionesDesde().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinAlta().getTxtResolucionesHasta().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinAlta().getDtfFechaDelBoletin().isValid()){
				Notification.show("Error", "Datos incorrectos y/o invalidos", Type.ERROR_MESSAGE);
			}else{
				
				ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea guardar este boletin?", "SI", "NO", new ConfirmDialog.Listener() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClose(ConfirmDialog rta) {
					
						if(rta.isConfirmed()){
							handler.guardarBoletin();
						}
					}
					});	
			}
		}
		if(event.getSource() == this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnGenerarPDF()){
			
			if(this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getValue() != null){
				
				this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnGenerarPDF().setVisible(false);
				this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnVerPDF().setVisible(true);
				Item itemTabla = this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getItem(this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getValue());
				direccionPDF = itemTabla.getItemProperty("direccionArchivo").getValue().toString();
				fd.setUrl(direccionPDF);			
			}
		}
		if(event.getSource() == this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnGuardar()){
			
			if(!this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtNumeroDeBoletin().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getCmbAnioBoletin().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtOrdenanzas().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtOtros().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtResolucionesDesde().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtResolucionesHasta().isValid() ||
					!this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getDtfFechaDelBoletin().isValid()){
				Notification.show("Error", "Datos incorrectos y/o invalidos", Type.ERROR_MESSAGE);
			}
			else{
				
				ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea modificar este boletin?", "SI", "NO", new ConfirmDialog.Listener() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClose(ConfirmDialog rta) {
					
						if(rta.isConfirmed()){
							handler.modificarBoletin();
						}
					}
					});	
			}
		}
		if(event.getSource() == this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnEliminar()){
			
			if(this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTablaBoletines().getValue() != null){
				
				ConfirmDialog.show(UI.getCurrent(), "Atencion", "Desea eliminar este boletin?", "SI", "NO", new ConfirmDialog.Listener() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClose(ConfirmDialog rta) {
					
						if(rta.isConfirmed()){
							handler.eliminarBoletin();
						}
					}
					});	
				
			}
			
		}
		if(event.getSource() == this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getBtnLimpiar()){
			
			limpiar();
		}
	}




	private void limpiar() {
		
		this.getLayAdmCuerpo().getFormBoletinAlta().getTxtNumeroDeBoletin().clear();
		this.getLayAdmCuerpo().getFormBoletinAlta().getCmbAnioBoletin().clear();
		this.getLayAdmCuerpo().getFormBoletinAlta().getTxtOrdenanzas().clear();
		this.getLayAdmCuerpo().getFormBoletinAlta().getTxtOtros().clear();
		this.getLayAdmCuerpo().getFormBoletinAlta().getTxtResolucionesDesde().clear();
		this.getLayAdmCuerpo().getFormBoletinAlta().getTxtResolucionesHasta().clear();
		this.getLayAdmCuerpo().getFormBoletinAlta().getDtfFechaDelBoletin().clear();
		
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtNumeroDeBoletin().clear();
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getCmbAnioBoletin().clear();
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtOrdenanzas().clear();
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtOtros().clear();
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtResolucionesDesde().clear();
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getTxtResolucionesHasta().clear();
		this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getDtfFechaDelBoletin().clear();
		
	}




	public ILayAdministradorHandler getHandler() {
		return handler;
	}




	public void setHandler(ILayAdministradorHandler handler) {
		this.handler = handler;
	}




	public void guardarBoletinOK() {
		
		Notification notification = new Notification("Atencion","Boletin guardado correctamente", Type.ASSISTIVE_NOTIFICATION);
		notification.setPosition(Position.MIDDLE_CENTER);
		notification.setDelayMsec(3000);
		notification.show(Page.getCurrent());
		
		limpiar();
		
	}




	public void guardarBoletinError() {
		
		Notification.show("Atencion","Error al guardar boletin", Type.ERROR_MESSAGE);
		
	}




	public void modificarBoletinError() {

		Notification.show("Atencion","Error al guardar boletin", Type.ERROR_MESSAGE);
	}




	public void modificarBoletinOK() {
		
		Notification notification = new Notification("Atencion","Boletin modificado correctamente", Type.ASSISTIVE_NOTIFICATION);
		notification.setPosition(Position.MIDDLE_CENTER);
		notification.setDelayMsec(3000);
		notification.show(Page.getCurrent());
		
		limpiar();
		
	}




	public void eliminarBoletinOK() {
		
		Notification notification = new Notification("Atencion","Boletin eliminado correctamente", Type.ASSISTIVE_NOTIFICATION);
		notification.setPosition(Position.MIDDLE_CENTER);
		notification.setDelayMsec(3000);
		notification.show(Page.getCurrent());
		
	}




	public void eliminarBoletinError() {
		
		Notification.show("Atencion","Error al eliminar boletin", Type.ERROR_MESSAGE);
	}




	@Override
	public void valueChange(ValueChangeEvent event) {
		
		if(event.getProperty() == this.getLayAdmCuerpo().getFormBoletinBajaModificacion().getCmbSeleccionAnio()){
			handler.cargarBoletinesPorAnio();
		}
		
	}

}
