package com.mcr.boletin.ui.LayoutBoletines;

import java.text.DateFormat;
import org.apache.log4j.Logger;
import org.tepi.filtertable.FilterTable;
import com.mcr.boletin.IHandlers.IlayoutBoletinesHandler;
import com.mcr.boletin.IVistas.IlayoutBoletines;
import com.mcr.boletin.model.Boletin;
import com.mcr.boletin.servicio.ServicioBoletin;
import com.mcr.boletin.tools.DemoFilterDecorator;
import com.mcr.boletin.tools.DemoFilterGenerator;
import com.mcr.boletin.ui.LayoutCredencial.LayoutCredencial;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomTable.ColumnGenerator;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;


public class LayoutBoletines extends VerticalLayout implements IlayoutBoletines, ClickListener, ValueChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FilterTable tablaBoletines;
	private Button btnAccesoMCR;
	private ComboBox cmbSeleccionAnio;	
	private Button btnGenerarPDF;
	private Button btnVerPDF;
	private String direccionPDF = "";
	private IlayoutBoletinesHandler handler;
	
	private Label label;
	private Button btnBuscarTodos;
	
	public static Logger log = Logger.getLogger(LayoutBoletines.class);
	BrowserWindowOpener fd = new BrowserWindowOpener("");
	
	
	
	
	
	public LayoutBoletines() {
			
		setWidth("100%");
		setSpacing(true);
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		lay.addComponent(generarSeleccion());
		
		this.setLabel(new Label("<p><strong><big>ó",ContentMode.HTML));
		this.setBtnBuscarTodos(new Button("Buscar todos los años",this));
		
		lay.addComponent(this.getLabel());
		lay.addComponent(this.getBtnBuscarTodos());			
		lay.setComponentAlignment(this.getLabel(), Alignment.BOTTOM_RIGHT);
		lay.setComponentAlignment(this.getBtnBuscarTodos(), Alignment.BOTTOM_RIGHT);
		
		addComponent(lay);
		addComponent(generarTablaBoltines());
		addComponent(generarAccesoMCR());
		fd.extend(btnVerPDF);
	
		
	}

	private Component generarAccesoMCR() {
		
		this.setBtnAccesoMCR(new Button("Acceso MCR",this));
		this.getBtnAccesoMCR().setStyleName(BaseTheme.BUTTON_LINK);
		return this.getBtnAccesoMCR();
	}

	private Component generarSeleccion() {
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		
		this.setCmbSeleccionAnio(new ComboBox());
		this.getCmbSeleccionAnio().setCaptionAsHtml(true);
		this.getCmbSeleccionAnio().setCaption("<p><strong><big>Seleccione año:");
		this.getCmbSeleccionAnio().setNewItemsAllowed(false);
		this.getCmbSeleccionAnio().setNullSelectionAllowed(false);
		this.getCmbSeleccionAnio().addItem("2020");
		this.getCmbSeleccionAnio().addItem("2019");
		this.getCmbSeleccionAnio().addItem("2018");
		this.getCmbSeleccionAnio().addItem("2017");
		this.getCmbSeleccionAnio().addItem("2016");
		this.getCmbSeleccionAnio().addItem("2015");
		this.getCmbSeleccionAnio().addItem("2014");
		this.getCmbSeleccionAnio().addItem("2013");
		this.getCmbSeleccionAnio().addItem("2012");
		this.getCmbSeleccionAnio().addItem("2011");
		this.getCmbSeleccionAnio().addItem("2010");
		this.getCmbSeleccionAnio().addItem("2009");		
		this.getCmbSeleccionAnio().addItem("2008");		
		this.getCmbSeleccionAnio().addItem("2007");
		this.getCmbSeleccionAnio().addItem("2006");
		this.getCmbSeleccionAnio().addItem("2005");
		this.getCmbSeleccionAnio().addItem("2004");
		this.getCmbSeleccionAnio().addItem("2003");
		this.getCmbSeleccionAnio().addItem("2002");
		this.getCmbSeleccionAnio().addItem("2001");
		this.getCmbSeleccionAnio().addItem("2000");		
		this.getCmbSeleccionAnio().addValueChangeListener(this);		
		this.setBtnGenerarPDF(new Button("Generar Boletin Oficial", this));
		this.setBtnVerPDF(new Button("Ver Boletin",this));
		this.getBtnGenerarPDF().setVisible(true);
		this.getBtnVerPDF().setVisible(false);
		
		
		layout.addComponent(this.getCmbSeleccionAnio());
	//	layout.addComponent(this.getBtnGenerarPDF());
	//	layout.addComponent(this.getBtnVerPDF());
	//	layout.setComponentAlignment(this.getBtnGenerarPDF(), Alignment.BOTTOM_CENTER);
	//	layout.setComponentAlignment(this.getBtnVerPDF(), Alignment.BOTTOM_CENTER);
		
		return layout;
	}

	private Component generarTablaBoltines() {
		
		ColumnGenerator generadorColumna = new ColumnGenerator() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
								
				@SuppressWarnings("rawtypes")
				Property property = source.getItem(itemId).getItemProperty(columnId);				
				if(property != null){
							
				Boletin boletin = (Boletin)itemId;			
				//Label label = new Label("<a href='"+boletin.getDireccionArchivo()+"' target='_blank'>PDF "+validar(boletin.getTamanioArchivo())+"</a>",ContentMode.HTML);				 
				Label label = new Label("<b><strong><a href='"+boletin.getDireccionArchivo()+"' target='_blank'>"+boletin.getNumeroDeBoletin()+" "+validar(boletin.getTamanioArchivo())+"</a></strong></b>",ContentMode.HTML);
				return label;
				
				}
				return null;
				}			
			};				
				
		this.setTablaBoletines(new FilterTable());
		this.getTablaBoletines().setSizeFull();
		this.getTablaBoletines().setFilterDecorator(new DemoFilterDecorator());
		this.getTablaBoletines().setFilterGenerator(new DemoFilterGenerator());
		

		this.getTablaBoletines().setSelectable(true);
		this.getTablaBoletines().setImmediate(true);	
		this.getTablaBoletines().setPageLength(10);
		this.getTablaBoletines().setFilterBarVisible(true);
		//this.getTablaBoletines().setWidth("300px");
		
		BeanItemContainer<Boletin> conteinerBoletin = ServicioBoletin.getInstance().getBoletines(); //<--generar servicioo y levantar boletines		
		this.getTablaBoletines().setContainerDataSource(conteinerBoletin);
		this.getTablaBoletines().setCaption("Boletines: " + this.getTablaBoletines().getContainerDataSource().size());
		
		
//		this.getTablaBoletines().setVisibleColumns(new Object[]{"direccionArchivo","numeroDeBoletin","fechaDeBoletin",
//		"resolucionesDesde","resolucionesHasta","ordenanzasIncluidas","otrosDocumentos",});
//		this.getTablaBoletines().setColumnHeaders(new String[]{"PDF","Nº Boletin","Fecha","Res Desde","Res Hasta","Ordenanzas","Otros"});
		
		
		
		this.getTablaBoletines().setVisibleColumns(new Object[]{"numeroDeBoletin","fechaDeBoletin","resolucionDesdeHasta","ordenanzasIncluidas","otrosDocumentos",});
		this.getTablaBoletines().setColumnHeaders(new String[]{"Nº Boletin","Fecha","Resoluciones","Ordenanzas","Otros"});
		
		
		//this.getTablaBoletines().setItemIcon("direccionArchivo",new ThemeResource("images/pdf.png"));
		
		
		this.getTablaBoletines().setColumnWidth("ordenanzasIncluidas",60);
		this.getTablaBoletines().setColumnWidth("otrosDocumentos", 60);
		this.getTablaBoletines().setColumnWidth("numeroDeBoletin",95);
		this.getTablaBoletines().setColumnWidth("fechaDeBoletin",95);
		this.getTablaBoletines().setColumnWidth("resolucionDesdeHasta",300);
		this.getTablaBoletines().setColumnWidth("ordenanzasIncluidas",162);
		this.getTablaBoletines().setColumnWidth("otrosDocumentos",159);
		//this.getTablaBoletines().setColumnWidth("numeroDeBoletin", 40);
		//this.getTablaBoletines().setColumnWidth("resolucionDesdeHasta", -1);
		//this.getTablaBoletines().setColumnExpandRatio("resolucionesDesde", -1);
		//this.getTablaBoletines().setColumnExpandRatio("resolucionesHasta",-1);
		//this.getTablaBoletines().setColumnExpandRatio("numeroDeBoletin",-1);
		//this.getTablaBoletines().setColumnWidth("direccionArchivo",95);
		
		//this.getTablaBoletines().addGeneratedColumn("direccionArchivo", generadorColumna);
		
		this.getTablaBoletines().addGeneratedColumn("numeroDeBoletin", generadorColumna);
		//this.getTablaBoletines().setColumnExpandRatio("numeroDeBoletin",-1);
	
		
		
		this.getTablaBoletines().setConverter("fechaDeBoletin", new StringToDateConverter(){
			
			private static final long serialVersionUID = 1L;
			protected java.text.DateFormat getFormat(java.util.Locale locale) {
				
					return DateFormat.getDateInstance(DateFormat.SHORT, locale);
				}
			});
		
		this.getTablaBoletines().addValueChangeListener(new Property.ValueChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				
				getBtnGenerarPDF().setVisible(true);
				getBtnVerPDF().setVisible(false);
			}
		});
		
		
		return this.getTablaBoletines();
	}

	protected String validar(String tamanioArchivo) {
		
//		if(tamanioArchivo == null){
//			tamanioArchivo = "";
//		}
//		return tamanioArchivo;
		return "";
	}

	public FilterTable getTablaBoletines() {
		return tablaBoletines;
	}

	public void setTablaBoletines(FilterTable tablaBoletines) {
		this.tablaBoletines = tablaBoletines;
	}

	public ComboBox getCmbSeleccionAnio() {
		return cmbSeleccionAnio;
	}

	public void setCmbSeleccionAnio(ComboBox cmbSeleccionAnio) {
		this.cmbSeleccionAnio = cmbSeleccionAnio;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	public Button getBtnAccesoMCR() {
		return btnAccesoMCR;
	}

	public void setBtnAccesoMCR(Button btnAccesoMCR) {
		this.btnAccesoMCR = btnAccesoMCR;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getBtnAccesoMCR()){			
			replaceComponent(this, new LayoutCredencial());
			
			
		}
		if(event.getSource() == this.getBtnGenerarPDF()){
			
			if(this.getTablaBoletines().getValue() != null){
				
				this.getBtnGenerarPDF().setVisible(false);
				this.getBtnVerPDF().setVisible(true);
				Item itemTabla = this.getTablaBoletines().getItem(this.getTablaBoletines().getValue());
				direccionPDF = itemTabla.getItemProperty("direccionArchivo").getValue().toString();
				fd.setUrl(direccionPDF);
			
				
			}
	
		}
		if(event.getSource() == this.getBtnBuscarTodos()){
			
			handler.cargarBoletinesTodos();
		}
		
	}

	public Button getBtnVerPDF() {
		return btnVerPDF;
	}

	public void setBtnVerPDF(Button btnVerPDF) {
		this.btnVerPDF = btnVerPDF;
	}

	public Button getBtnGenerarPDF() {
		return btnGenerarPDF;
	}

	public void setBtnGenerarPDF(Button btnGenerarPDF) {
		this.btnGenerarPDF = btnGenerarPDF;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		if(event.getProperty() == this.getCmbSeleccionAnio()){
		
			handler.cargarBoletinesPorAnio();
		}
		
	}

	public IlayoutBoletinesHandler getHandler() {
		return handler;
	}

	public void setHandler(IlayoutBoletinesHandler handler) {
		this.handler = handler;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Button getBtnBuscarTodos() {
		return btnBuscarTodos;
	}

	public void setBtnBuscarTodos(Button btnBuscarTodos) {
		this.btnBuscarTodos = btnBuscarTodos;
	}


}
