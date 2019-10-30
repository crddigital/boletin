package com.mcr.boletin.ui.LayAdministrador;

import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;


public class LayAdministradorCuerpo extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuBar menuBar;
	private FormularioBoletinAlta formBoletinAlta;
	private FormularioBoletinBajaModificacion formBoletinBajaModificacion;
	
	public LayAdministradorCuerpo() {
		
		setSizeFull();
		addComponent(generarMenuBar());
		this.setFormBoletinAlta(new FormularioBoletinAlta());
		this.setFormBoletinBajaModificacion(new FormularioBoletinBajaModificacion());
		
		
		
	}


	private Component generarMenuBar() {
		
		this.setMenuBar(new MenuBar());
		this.getMenuBar().setWidth(100.0f, Unit.PERCENTAGE);
		
		
		MenuItem boletin = this.getMenuBar().addItem("Boletin", null);		
		boletin.addItem("Alta",new Command() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public void menuSelected(MenuItem selectedItem) {			
				
				if(getComponentCount() == 1){ //
					addComponent(getFormBoletinAlta());
				}else{
					removeComponent(getComponent(1));
					addComponent(getFormBoletinAlta());
				}
			}
		});		
		
		boletin.addItem("Baja/Modificación", new Command() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
			
				if(getComponentCount() == 1){ //
					addComponent(getFormBoletinBajaModificacion());
				}else{
					removeComponent(getComponent(1));
					addComponent(getFormBoletinBajaModificacion());
				}
				
			}
		});
		
		
		MenuItem ventana = this.getMenuBar().addItem("Ventanas", null);		
		ventana.addItem("Vista Boletines",new Command() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public void menuSelected(MenuItem selectedItem) {			
				
				//UI.getCurrent().getNavigator().navigateTo(LayoutSistema.NAME);
				VaadinSession.getCurrent().getSession().invalidate();
				Page.getCurrent().setLocation("/boletin");				
			}
		});		
		
		
		MenuItem usuario = this.getMenuBar().addItem("Usuario",null);
		
		usuario.addItem("Datos Usuario", new Command() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				
				System.out.println("completar implementacion para que el user cambien sus datos....");
			}
		});
		usuario.addSeparator();
		usuario.addItem("Salir",new Command() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				
				ConfirmDialog.show(UI.getCurrent(), "Atecni�n", "Desea cerrar sesi�n?", "SI", "NO", new ConfirmDialog.Listener() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClose(ConfirmDialog rta) {
						
						if(rta.isConfirmed()){
							VaadinSession.getCurrent().getSession().invalidate();
							Page.getCurrent().setLocation("/boletin");						
						
						}
						
					}
				});
				
			}
		});
		
	
		
				
		return this.getMenuBar();
	}


	protected Component generarFormularioAlta() {
		// TODO Auto-generated method stub
		return null;
	}


	public MenuBar getMenuBar() {
		return menuBar;
	}


	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}
	
	public FormularioBoletinAlta getFormBoletinAlta() {
		return formBoletinAlta;
	}


	public void setFormBoletinAlta(FormularioBoletinAlta formBoletinAlta) {
		this.formBoletinAlta = formBoletinAlta;
	}


	public FormularioBoletinBajaModificacion getFormBoletinBajaModificacion() {
		return formBoletinBajaModificacion;
	}


	public void setFormBoletinBajaModificacion(
			FormularioBoletinBajaModificacion formBoletinBajaModificacion) {
		this.formBoletinBajaModificacion = formBoletinBajaModificacion;
	}
	
	
	
	

}
