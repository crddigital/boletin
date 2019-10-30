package com.mcr.boletin.ui.LayoutCredencial;

import com.mcr.boletin.IHandlers.IViewCredencialHandler;
import com.mcr.boletin.IVistas.IViewCredencial;
import com.mcr.boletin.model.Usuario;
import com.mcr.boletin.ui.LayAdministrador.LayAdministrador;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;




public class LayoutCredencial extends VerticalLayout implements IViewCredencial, ClickListener  {
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IViewCredencialHandler handler;
	private TextField txtNombreDeUsuario;
	private PasswordField txtContrasenia;
	private Button btnValidar;
	
	
	
	
	public LayoutCredencial() {
		
		setSizeFull();	
		Component  formularioLogin = generarFormulario();
		addComponent(formularioLogin);
		setComponentAlignment(formularioLogin, Alignment.MIDDLE_CENTER);

	}

	private Component generarFormulario() {
		
		final VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.addStyleName("login-panel");
		loginPanel.setSpacing(true);
		loginPanel.addComponent(generarLabels());
		loginPanel.addComponent(generarCampos());
		return loginPanel;
	}
	
       private Component generarCampos() {
		
		HorizontalLayout campos = new HorizontalLayout();
		campos.setSpacing(true);		
		this.setTxtNombreDeUsuario(new TextField("Nombre de usuario"));
		this.getTxtNombreDeUsuario().setRequired(true);
		
		Validator stringValidator = new RegexpValidator("^[a-zA-Z-0-9]+$", "Ingrese caracteres validos");
		this.getTxtNombreDeUsuario().addValidator(stringValidator);		
		this.getTxtNombreDeUsuario().setIcon((FontAwesome.USER));
		this.getTxtNombreDeUsuario().addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		this.getTxtNombreDeUsuario().focus();
		
		this.setTxtContrasenia(new PasswordField("Contraseña"));
		this.getTxtContrasenia().setRequired(true);		
		this.getTxtContrasenia().setIcon(FontAwesome.LOCK);
		this.getTxtContrasenia().addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		
		this.setBtnValidar(new Button("Ingresar",this));
		this.getBtnValidar().addStyleName(ValoTheme.BUTTON_PRIMARY);
		this.getBtnValidar().setClickShortcut(KeyCode.ENTER);
		
		
		campos.addComponent(this.getTxtNombreDeUsuario());
		campos.addComponent(this.getTxtContrasenia());
		campos.addComponent(this.getBtnValidar());
		
		campos.setComponentAlignment(this.getBtnValidar(), Alignment.BOTTOM_RIGHT);
		
		
		return campos;
	}

	private Component generarLabels() {
		
		CssLayout labels = new CssLayout();
		
		Label lblBienvenida = new Label("Bienvenido - ABM Boletín Oficial MCR");
		lblBienvenida.setSizeUndefined();
		lblBienvenida.addStyleName(ValoTheme.LABEL_H4);
		lblBienvenida.addStyleName(ValoTheme.LABEL_COLORED);
		labels.addComponent(lblBienvenida);
		
		return labels;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getBtnValidar()){
			
			if(!this.getTxtContrasenia().isValid() ||
					!this.getTxtNombreDeUsuario().isValid()){
				Notification.show("Atención", "Datos ingresados incorrectos", Type.ERROR_MESSAGE);
			}else{
				handler.login();
			}
		}
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		
	}

	public IViewCredencialHandler getHandler() {
		return handler;
	}

	public void setHandler(IViewCredencialHandler handler) {
		this.handler = handler;
	}

	public TextField getTxtNombreDeUsuario() {
		return txtNombreDeUsuario;
	}

	public void setTxtNombreDeUsuario(TextField txtNombreDeUsuario) {
		this.txtNombreDeUsuario = txtNombreDeUsuario;
	}

	public PasswordField getTxtContrasenia() {
		return txtContrasenia;
	}

	public void setTxtContrasenia(PasswordField txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}

	public Button getBtnValidar() {
		return btnValidar;
	}

	public void setBtnValidar(Button btnValidar) {
		this.btnValidar = btnValidar;
	}

	public void loginOk(Usuario usuario) {
		
		UI.getCurrent().getSession().setAttribute("usuario", usuario);
		System.out.println("loginok: " + usuario.obtenerPerfil().getClass().getSimpleName());
		if(usuario.obtenerPerfil().getClass().getSimpleName().equals("Administrador")){
			UI.getCurrent().getNavigator().navigateTo(LayAdministrador.NAME);
		}
		if(usuario.obtenerPerfil().getClass().getSimpleName().equals("Administrativo")){
			System.out.println("administrativo");
		}
		
	}

	public void loginError() {
		
		Notification.show("Error", "Nombre de usuario y/o contrasen�a incorrecta", Type.ERROR_MESSAGE);
		
	}

}
