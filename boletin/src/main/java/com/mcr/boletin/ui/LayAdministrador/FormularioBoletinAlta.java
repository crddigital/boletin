package com.mcr.boletin.ui.LayAdministrador;

import java.util.Date;

import com.mcr.boletin.tools.CargadorArchivos;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;



public class FormularioBoletinAlta extends GridLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	
	
	//Usados para upload
	//Cargador receptor = new Cargador();
	private ProgressBar progressBar = new ProgressBar(0.0f);
	private Label textualProgress = new Label();
	private Label result = new Label();
	
	
	//
	private CargadorArchivos receptor = new CargadorArchivos(this.getProgressBar());
	
	
	
	
	public FormularioBoletinAlta() {
	
		
		setCaptionAsHtml(true);
		setCaption("<strong><big>Alta Boletines Oficiales");
		setMargin(true);
		setSpacing(true);
		setColumns(1);
		
		GridLayout grid = new GridLayout(3,2);
		grid.setSizeFull();
		grid.setSpacing(true);
		grid.addComponent(generarTxtNumeroDeBoletin());
		grid.addComponent(generarCmbAnioNumeroDeBoletin());
		grid.addComponent(generarDtfFechaDelBoletin());
		grid.addComponent(generarTxtResolucionesDesde());
		grid.addComponent(generarTxtResolucionesHasta());
		//grid.addComponent(generarTxtOrdenanzas());
		//grid.addComponent(generarTxtOtros());
		
		
		addComponent(grid);
		addComponent(bigBoxes());	
		addComponent(new com.vaadin.ui.Label("<hr/>",ContentMode.HTML));
		addComponent(generarUplBoletin());

		progressBar.setSizeFull();
		addComponent(progressBar);
		//progressBar.setVisible(false);
		addComponent(generarComandos());
		
		
		
		//UI.getCurrent().equals("$('.gwt-FileUpload').attr('accept', 'application/pdf');");		
		
	}

	private Component generarCmbAnioNumeroDeBoletin() {
		
		this.setCmbAnioBoletin(new ComboBox("Año Boletin:"));
		//this.getCmbAnioBoletin().setCaptionAsHtml(true);
		this.getCmbAnioBoletin().setNewItemsAllowed(false);
		this.getCmbAnioBoletin().setNullSelectionAllowed(false);
		this.getCmbAnioBoletin().setWidth("250px");
		this.getCmbAnioBoletin().addItem("2020");
		this.getCmbAnioBoletin().addItem("2019");
		this.getCmbAnioBoletin().addItem("2018");
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

	private Component bigBoxes() {
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		lay.addComponent(generarTxtOrdenanzas());
		lay.addComponent(generarTxtOtros());
		
		return lay;
	}

	private Component generarComandos() {
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		this.setBtnGuardar(new Button("Guardar"));
		this.setBtnLimpiar(new Button("Limpiar"));
		
		lay.addComponent(this.getBtnGuardar());
		lay.addComponent(this.getBtnLimpiar());
		
		return lay;
	}

	private Component generarUplBoletin() {
		
	
		this.setUplBoletin(new Upload("Seleccionar Boletin", receptor));
		this.getUplBoletin().addSucceededListener(receptor);
		this.getUplBoletin().addProgressListener(receptor);
		this.getUplBoletin().addStartedListener(receptor);		
		this.getUplBoletin().setButtonCaption("Subir Boletín");		
		return this.getUplBoletin();
		
	}

	private Component generarTxtOtros() {
		this.setTxtOtros(new TextArea("Otros:"));
		this.getTxtOtros().setWidth("250px");
		this.getTxtOtros().setHeight("200px");
		this.getTxtOtros().setNullSettingAllowed(false);
		this.getTxtOtros().setInputPrompt("Una debajo de la otra");
		//this.getTxtOtros().setRequired(true);
		
		//Validator stringValidator = new RegexpValidator("^[\\d,/]+$", "Ingrese caracteres validos");
		//this.getTxtOtros().addValidator(stringValidator);
		
		
		return this.getTxtOtros();
	}

	private Component generarTxtOrdenanzas() {
		
		this.setTxtOrdenanzas(new TextArea("Ordenanzas:"));
		this.getTxtOrdenanzas().setWidth("250px");
		this.getTxtOrdenanzas().setHeight("200px");
		this.getTxtOrdenanzas().setNullSettingAllowed(false);
		this.getTxtOrdenanzas().setInputPrompt("Una debajo de la otra");
		//this.getTxtOrdenanzas().setRequired(true);		
		//Validator stringValidator = new RegexpValidator("^[a-zA-Z�������0-9]+$", "Ingrese caracteres validos");
		//this.getTxtOrdenanzas().addValidator(stringValidator);
		return this.getTxtOrdenanzas();
	}

	private Component generarTxtResolucionesHasta() {
		
		this.setTxtResolucionesHasta(new TextField("Resolución hasta:"));
		this.getTxtResolucionesHasta().setWidth("250px");
		this.getTxtResolucionesHasta().setNullSettingAllowed(false);
		this.getTxtResolucionesHasta().setInputPrompt("número de resolución");
		//this.getTxtResolucionesHasta().setRequired(true);
		
		//Validator stringValidator = new RegexpValidator("^[0-9]+$", "Ingrese caracteres validos - solo n�meros");
		//this.getTxtResolucionesHasta().addValidator(stringValidator);
		
		return this.getTxtResolucionesHasta();
	}

	private Component generarTxtResolucionesDesde() {
		
		this.setTxtResolucionesDesde(new TextField("Resolución desde:"));
		this.getTxtResolucionesDesde().setWidth("250px");
		this.getTxtResolucionesDesde().setNullSettingAllowed(false);
		this.getTxtResolucionesDesde().setInputPrompt("número de resolución");
		//this.getTxtResolucionesDesde().setRequired(true);
		
		//Validator stringValidator = new RegexpValidator("^[0-9]+$", "Ingrese caracteres validos - solo n�meros");
		//this.getTxtResolucionesDesde().addValidator(stringValidator);
		
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
		this.getTxtNumeroDeBoletin().setInputPrompt("número");		
		//Validator stringValidator = new RegexpValidator("^[0-9]+$", "Ingrese caracteres validos - numero/a�o");
		//this.getTxtNumeroDeBoletin().addValidator(stringValidator);	
		this.getTxtNumeroDeBoletin().setRequired(true);
		return this.getTxtNumeroDeBoletin();
	}

	public TextField getTxtNumeroDeBoletin() {
		return txtNumeroDeBoletin;
	}

	public void setTxtNumeroDeBoletin(TextField txtNumeroDeBoletin) {
		this.txtNumeroDeBoletin = txtNumeroDeBoletin;
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
	
	

	public ComboBox getCmbAnioBoletin() {
		return cmbAnioBoletin;
	}

	public void setCmbAnioBoletin(ComboBox cmbAnioBoletin) {
		this.cmbAnioBoletin = cmbAnioBoletin;
	}

	public CargadorArchivos getReceptor() {
		return receptor;
	}

	public void setReceptor(CargadorArchivos receptor) {
		this.receptor = receptor;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public Label getTextualProgress() {
		return textualProgress;
	}

	public void setTextualProgress(Label textualProgress) {
		this.textualProgress = textualProgress;
	}

	public Label getResult() {
		return result;
	}

	public void setResult(Label result) {
		this.result = result;
	}
	
	
	
	

}
