package com.mcr.boletin.ui.LayAdministrador;

import java.text.DateFormat;
import java.util.Date;

import org.tepi.filtertable.FilterTable;

import com.mcr.boletin.model.Boletin;
import com.mcr.boletin.servicio.ServicioBoletin;
import com.mcr.boletin.tools.CargadorArchivos;
import com.mcr.boletin.tools.DemoFilterGenerator;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Upload;
import com.vaadin.ui.CustomTable.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;




public class FormularioBoletinBajaModificacion extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FilterTable tablaBoletines;
	private TextField txtNumeroDeBoletin;
	private ComboBox cmbAnioBoletin;
	private DateField dtfFechaDelBoletin;
	private TextField txtResolucionesDesde;
	private TextField txtResolucionesHasta;
	private TextArea txtOrdenanzas;
	private TextArea txtOtros;
	private Upload uplBoletin;
	private Button btnGuardar;
	private Button btnLimpiar;
	private Button btnEliminar;
	private ComboBox cmbSeleccionAnio;	
	private Button btnGenerarPDF;
	private Button btnVerPDF;
	
	private static ProgressBar progressBar = new ProgressBar(0.0f);
	private BeanFieldGroup<Boletin> beanFieldGroup = new BeanFieldGroup<Boletin>(Boletin.class);
	
	private CargadorArchivos receptor = new CargadorArchivos(progressBar);
	
	public FormularioBoletinBajaModificacion() {
	
		setCaption("Baja-Modificacion Boletines Oficiales");
		setMargin(true);
		setSpacing(true);
		setSizeFull();
		
		addComponent(generarSeleccion());
		addComponent(generarTablaBoltines());
		
		GridLayout grid = new GridLayout(3,2);
		grid.setCaptionAsHtml(true);
		grid.setCaption("<b><u><big> Edicion Boletin");
		grid.setSpacing(true);
		grid.addComponent(generarTxtNumeroDeBoletin());
		grid.addComponent(generarCmbAnioNumeroDeBoletin());
		grid.addComponent(generarDtfFechaDelBoletin());
		grid.addComponent(generarTxtResolucionesDesde());
		grid.addComponent(generarTxtResolucionesHasta());		
		addComponent(grid);
		addComponent(bigBoxes());	
		addComponent(new com.vaadin.ui.Label("<hr/>",ContentMode.HTML));
		addComponent(generarUplBoletin());
		
		progressBar.setSizeFull();
		addComponent(progressBar);
		addComponent(generarComandos());
		
		
		
		//this.getBeanFieldGroup().bind(this.getTxtNumeroDeBoletin(),"numeroDeBoletin");
		//this.getBeanFieldGroup().bind(this.getCmbAnioBoletin(),this.getTxtNumeroDeBoletin().getValue().split("/")
		this.getBeanFieldGroup().bind(this.getDtfFechaDelBoletin(),"fechaDeBoletin");
		this.getBeanFieldGroup().bind(this.getTxtResolucionesDesde(),"resolucionesDesde");
		this.getBeanFieldGroup().bind(this.getTxtResolucionesHasta(),"resolucionesHasta");													   
		this.getBeanFieldGroup().bind(this.getTxtOrdenanzas(),"ordenanzasIncluidas");
		this.getBeanFieldGroup().bind(this.getTxtOtros(), "otrosDocumentos");
		
		/*
		 * 	this.getTablaBoletines().setVisibleColumns(new Object[]{"numeroDeBoletin","fechaDeBoletin",
		"resolucionesDesde","resolucionesHasta","ordenanzasIncluidas","otrosDocumentos","direccionArchivo"});
		 * 
		 */
		
	}

		private Component generarTablaBoltines() {
		
		ColumnGenerator generadorColumna = new ColumnGenerator() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(CustomTable source, final Object itemId,
					Object columnId) {
				@SuppressWarnings("rawtypes")
				Property property = source.getItem(itemId).getItemProperty(columnId);				
				if(property != null){					
					Embedded icono = new Embedded(null, new ThemeResource("images/pdf.png")); 				
				return icono;
				}
				return null;
				}			
			};				
				
		this.setTablaBoletines(new FilterTable());
		this.getTablaBoletines().setSizeFull();
		this.getTablaBoletines().setFilterGenerator(new DemoFilterGenerator());
		this.getTablaBoletines().setSelectable(true);
		this.getTablaBoletines().setImmediate(true);	
		this.getTablaBoletines().setPageLength(5);
		this.getTablaBoletines().setFilterBarVisible(true);
		
		BeanItemContainer<Boletin> conteinerBoletin = ServicioBoletin.getInstance().getBoletinesModificacion(); //<--generar servicioo y levantar boletines		
		this.getTablaBoletines().setContainerDataSource(conteinerBoletin);
		this.getTablaBoletines().setCaption("Boletines: " + this.getTablaBoletines().getContainerDataSource().size());
		
		
		this.getTablaBoletines().setVisibleColumns(new Object[]{"numeroDeBoletin","fechaDeBoletin","resolucionesDesde","resolucionesHasta",
				"ordenanzasIncluidas","otrosDocumentos","direccionArchivo"});
		this.getTablaBoletines().setColumnHeaders(new String[]{"N�Bolet�n","Fecha",
		"Resol Desde","Resul Hasta","Ordenanzas","Otros","PDF"});
		
		
		//this.getTablaBoletines().setItemIcon("direccionArchivo",new ThemeResource("images/pdf.png"));
		this.getTablaBoletines().setColumnWidth("ordenanzasIncluidas", 200);
		this.getTablaBoletines().setColumnWidth("otrosDocumentos", 200);
		this.getTablaBoletines().setColumnExpandRatio("resolucionesDesde", -1);
		//this.getTablaBoletines().setColumnExpandRatio("resolucionesHasta",-1);		
		this.getTablaBoletines().addGeneratedColumn("direccionArchivo", generadorColumna);	
		
		
		this.getTablaBoletines().setConverter("fechaDeBoletin", new StringToDateConverter(){
			
			private static final long serialVersionUID = 1L;
			protected java.text.DateFormat getFormat(java.util.Locale locale) {
				
					return DateFormat.getDateInstance(DateFormat.SHORT, locale);
				}
			});
		
		this.getTablaBoletines().addValueChangeListener(new Property.ValueChangeListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				
				editarBoletin((Boletin)getTablaBoletines().getValue());
				getBtnGenerarPDF().setVisible(true);
				getBtnVerPDF().setVisible(false);
			}

			private void editarBoletin(Boletin boletin) {
				
				String numeroDeBoletin = "";
				String[] parts;
				String numeroBoletin = "";
				String anio = "";
				
				if(boletin !=null){
				
					BeanItem<Boletin> item = new BeanItem<Boletin>(boletin);
					numeroDeBoletin = (String)item.getItemProperty("numeroDeBoletin").getValue();
					parts = numeroDeBoletin.split("/");
					numeroBoletin = parts[0]; 
					anio = parts[1]; 
					
					getTxtNumeroDeBoletin().setValue(numeroBoletin);
					getCmbAnioBoletin().setValue(anio.toString());
					
					getBeanFieldGroup().setItemDataSource(item);
					
				}
				
			}
		});
		
		
		return this.getTablaBoletines();
	}
		
		private Component generarSeleccion() {
			
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			
			this.setCmbSeleccionAnio(new ComboBox("Seleccione año:"));
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
			
			
			this.setBtnGenerarPDF(new Button("Generar Boletin Oficial"));
			this.setBtnVerPDF(new Button("Ver Boletin"));
			this.getBtnGenerarPDF().setVisible(true);
			this.getBtnVerPDF().setVisible(false);
			
			
			layout.addComponent(this.getCmbSeleccionAnio());
			layout.addComponent(this.getBtnGenerarPDF());
			layout.addComponent(this.getBtnVerPDF());
			layout.setComponentAlignment(this.getBtnGenerarPDF(), Alignment.BOTTOM_CENTER);
			layout.setComponentAlignment(this.getBtnVerPDF(), Alignment.BOTTOM_CENTER);
			
			return layout;
		}	
		
	private Component generarComandos() {
			
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		this.setBtnLimpiar(new Button("Limpiar"));
		this.setBtnGuardar(new Button("Guardar"));
		this.setBtnEliminar(new Button("Eliminar"));
				
		lay.addComponent(this.getBtnLimpiar());
		lay.addComponent(this.getBtnGuardar());
		lay.addComponent(this.getBtnEliminar());
		return lay;
	}	
		
	private Component generarUplBoletin() {
			
			
		this.setUplBoletin(new Upload("Seleccionar Boletin", receptor));
		this.getUplBoletin().addSucceededListener(receptor);
		this.getUplBoletin().addProgressListener(receptor);
		this.getUplBoletin().addStartedListener(receptor);
		this.getUplBoletin().setButtonCaption("Subir Bolet�n");		
		return this.getUplBoletin();
			
		}	
		
		
		private Component bigBoxes() {
			
			HorizontalLayout lay = new HorizontalLayout();
			lay.setSpacing(true);
			lay.addComponent(generarTxtOrdenanzas());
			lay.addComponent(generarTxtOtros());			
			return lay;
		}
		
		private Component generarTxtOtros() {
			this.setTxtOtros(new TextArea("Otros:"));
			this.getTxtOtros().setWidth("250px");
			this.getTxtOtros().setHeight("200px");
			this.getTxtOtros().setNullSettingAllowed(false);
			this.getTxtOtros().setInputPrompt("Una debajo de la otra");
			this.getTxtOtros().setRequired(true);			
			//Validator stringValidator = new RegexpValidator("^[\\d,/]+$", "Ingrese caracteres validos");
			//this.getTxtOtros().addValidator(stringValidator);
			this.getTxtOtros().setNullSettingAllowed(false);			
			return this.getTxtOtros();
		}

		private Component generarTxtOrdenanzas() {
			
			this.setTxtOrdenanzas(new TextArea("Ordenanzas:"));
			this.getTxtOrdenanzas().setWidth("250px");
			this.getTxtOrdenanzas().setHeight("200px");
			this.getTxtOrdenanzas().setNullSettingAllowed(false);
			this.getTxtOrdenanzas().setInputPrompt("Una debajo de la otra");
			this.getTxtOrdenanzas().setRequired(true);		
			//Validator stringValidator = new RegexpValidator("^[a-zA-Z�������/-0-9]+$", "Ingrese caracteres validos");
			//this.getTxtOrdenanzas().addValidator(stringValidator);
			this.getTxtOrdenanzas().setNullSettingAllowed(false);
			return this.getTxtOrdenanzas();
		}
		
		private Component generarTxtResolucionesHasta() {
			
			this.setTxtResolucionesHasta(new TextField("Resolución hasta:"));
			this.getTxtResolucionesHasta().setWidth("250px");
			this.getTxtResolucionesHasta().setNullSettingAllowed(false);
			this.getTxtResolucionesHasta().setInputPrompt("Número de resolución");
			this.getTxtResolucionesHasta().setRequired(true);			
			//Validator stringValidator = new RegexpValidator("^[0-9]+$", "Ingrese caracteres validos - solo n�meros");
			//this.getTxtResolucionesHasta().addValidator(stringValidator);
			this.getTxtResolucionesHasta().setNullSettingAllowed(false);
			
			return this.getTxtResolucionesHasta();
		}

		private Component generarTxtResolucionesDesde() {
			
			this.setTxtResolucionesDesde(new TextField("Resolución desde:"));
			this.getTxtResolucionesDesde().setWidth("250px");
			this.getTxtResolucionesDesde().setNullSettingAllowed(false);
			this.getTxtResolucionesDesde().setInputPrompt("número de resolución");
			this.getTxtResolucionesDesde().setRequired(true);
			
			//Validator stringValidator = new RegexpValidator("//^[0-9]+$", "Ingrese caracteres validos - solo n�meros");
			//this.getTxtResolucionesDesde().addValidator(stringValidator);
			this.getTxtResolucionesDesde().setNullSettingAllowed(false);
			
			return this.getTxtResolucionesDesde();
		}

		private Component generarDtfFechaDelBoletin() {
			
			this.setDtfFechaDelBoletin(new DateField("Fecha del Boletín:"));
			this.getDtfFechaDelBoletin().setWidth("250px");
			this.getDtfFechaDelBoletin().setDateFormat("dd-MM-yyyy");
			this.getDtfFechaDelBoletin().setRequired(true);
			this.getDtfFechaDelBoletin().setInvalidAllowed(false);
			this.getDtfFechaDelBoletin().setRangeEnd(new Date());
			return this.getDtfFechaDelBoletin();
		}

		private Component generarTxtNumeroDeBoletin() {

			this.setTxtNumeroDeBoletin(new TextField("Número de Boletin:"));
			this.getTxtNumeroDeBoletin().setWidth("250px");
			this.getTxtNumeroDeBoletin().setNullSettingAllowed(false);
			this.getTxtNumeroDeBoletin().setInputPrompt("Número");		
			//Validator stringValidator = new RegexpValidator("^[0-9]+$", "Ingrese caracteres validos - numero/a�o");
			//this.getTxtNumeroDeBoletin().addValidator(stringValidator);
			
			
			this.getTxtNumeroDeBoletin().setRequired(true);
			return this.getTxtNumeroDeBoletin();
		}
		
		private Component generarCmbAnioNumeroDeBoletin() {
			
			this.setCmbAnioBoletin(new ComboBox("Año Boletin:"));
			this.getCmbAnioBoletin().setNewItemsAllowed(false);
			this.getCmbAnioBoletin().setNullSelectionAllowed(false);
			this.getCmbAnioBoletin().setWidth("250px");
			this.getCmbAnioBoletin().addItem("2017");
			this.getCmbAnioBoletin().addItem("2016");
			this.getCmbAnioBoletin().addItem("2015");
			this.getCmbAnioBoletin().addItem("2014");
			this.getCmbAnioBoletin().addItem("2013");
			this.getCmbAnioBoletin().addItem("2012");
			this.getCmbAnioBoletin().addItem("2011");
			this.getCmbAnioBoletin().addItem("2010");
			this.getCmbAnioBoletin().addItem("2009");
			this.getCmbAnioBoletin().addItem("2008");
			this.getCmbAnioBoletin().addItem("2007");
			this.getCmbAnioBoletin().addItem("2006");
			this.getCmbAnioBoletin().addItem("2005");
			this.getCmbAnioBoletin().addItem("2004");
			this.getCmbAnioBoletin().addItem("2003");
			this.getCmbAnioBoletin().addItem("2002");
			this.getCmbAnioBoletin().addItem("2001");
			this.getCmbAnioBoletin().addItem("2000");
			
			return this.getCmbAnioBoletin();
		}
		

		public FilterTable getTablaBoletines() {
			return tablaBoletines;
		}

		public void setTablaBoletines(FilterTable tablaBoletines) {
			this.tablaBoletines = tablaBoletines;
		}

		public TextField getTxtNumeroDeBoletin() {
			return txtNumeroDeBoletin;
		}

		public void setTxtNumeroDeBoletin(TextField txtNumeroDeBoletin) {
			this.txtNumeroDeBoletin = txtNumeroDeBoletin;
		}

		public ComboBox getCmbAnioBoletin() {
			return cmbAnioBoletin;
		}

		public void setCmbAnioBoletin(ComboBox cmbAnioBoletin) {
			this.cmbAnioBoletin = cmbAnioBoletin;
		}

		public DateField getDtfFechaDelBoletin() {
			return dtfFechaDelBoletin;
		}

		public void setDtfFechaDelBoletin(DateField dtfFechaDelBoletin) {
			this.dtfFechaDelBoletin = dtfFechaDelBoletin;
		}

		public TextField getTxtResolucionesDesde() {
			return txtResolucionesDesde;
		}

		public void setTxtResolucionesDesde(TextField txtResolucionesDesde) {
			this.txtResolucionesDesde = txtResolucionesDesde;
		}

		public TextField getTxtResolucionesHasta() {
			return txtResolucionesHasta;
		}

		public void setTxtResolucionesHasta(TextField txtResolucionesHasta) {
			this.txtResolucionesHasta = txtResolucionesHasta;
		}

		public TextArea getTxtOrdenanzas() {
			return txtOrdenanzas;
		}

		public void setTxtOrdenanzas(TextArea txtOrdenanzas) {
			this.txtOrdenanzas = txtOrdenanzas;
		}

		public TextArea getTxtOtros() {
			return txtOtros;
		}

		public void setTxtOtros(TextArea txtOtros) {
			this.txtOtros = txtOtros;
		}

		public Upload getUplBoletin() {
			return uplBoletin;
		}

		public void setUplBoletin(Upload uplBoletin) {
			this.uplBoletin = uplBoletin;
		}

		public Button getBtnGuardar() {
			return btnGuardar;
		}

		public void setBtnGuardar(Button btnGuardar) {
			this.btnGuardar = btnGuardar;
		}

		public Button getBtnLimpiar() {
			return btnLimpiar;
		}

		public void setBtnLimpiar(Button btnLimpiar) {
			this.btnLimpiar = btnLimpiar;
		}

		public Button getBtnEliminar() {
			return btnEliminar;
		}

		public void setBtnEliminar(Button btnEliminar) {
			this.btnEliminar = btnEliminar;
		}

		public BeanFieldGroup<Boletin> getBeanFieldGroup() {
			return beanFieldGroup;
		}

		public void setBeanFieldGroup(BeanFieldGroup<Boletin> beanFieldGroup) {
			this.beanFieldGroup = beanFieldGroup;
		}

		public ComboBox getCmbSeleccionAnio() {
			return cmbSeleccionAnio;
		}

		public void setCmbSeleccionAnio(ComboBox cmbSeleccionAnio) {
			this.cmbSeleccionAnio = cmbSeleccionAnio;
		}

		public Button getBtnGenerarPDF() {
			return btnGenerarPDF;
		}

		public void setBtnGenerarPDF(Button btnGenerarPDF) {
			this.btnGenerarPDF = btnGenerarPDF;
		}

		public Button getBtnVerPDF() {
			return btnVerPDF;
		}

		public void setBtnVerPDF(Button btnVerPDF) {
			this.btnVerPDF = btnVerPDF;
		}

		public CargadorArchivos getReceptor() {
			return receptor;
		}

		public void setReceptor(CargadorArchivos receptor) {
			this.receptor = receptor;
		}
	
	
	

}
