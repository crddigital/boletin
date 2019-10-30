package com.mcr.boletin.servicio;

import java.io.Serializable;
import java.util.ArrayList;

import com.mcr.boletin.IServicio.IServicioBoletin;
import com.mcr.boletin.dao.DaoBoletin;
import com.mcr.boletin.model.Boletin;
import com.mcr.boletin.model.Usuario;
import com.vaadin.data.util.BeanItemContainer;


public class ServicioBoletin implements Serializable, IServicioBoletin{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioBoletin instance;
	
	
	private ServicioBoletin () {
	
	}
	
	public static ServicioBoletin getInstance(){
		
		if(instance == null){
			instance = new ServicioBoletin();
		}
		return instance;
	}

	@Override
	public BeanItemContainer<Boletin> getBoletines() {
		
		BeanItemContainer<Boletin> containerBoletines = new BeanItemContainer<Boletin>(Boletin.class,
				DaoBoletin.getInstance().getBoletines());		
		return containerBoletines;
	}

	@Override
	public int guardarBoletin(Boletin boletin, Usuario usuario) {
		
		return DaoBoletin.getInstance().guardarBoletin(boletin,usuario);
	}

	@Override
	public BeanItemContainer<Boletin> getBoletinesModificacion() {
	
		BeanItemContainer<Boletin> containerBoletines = new BeanItemContainer<Boletin>(Boletin.class,
				DaoBoletin.getInstance().getBoletinesModificacion());
		
		return containerBoletines;
	}

	@Override
	public int modificarBoletin(Boletin boletin, Usuario usuario) {
		
		return DaoBoletin.getInstance().modificarBoletin(boletin,usuario);
	}

	@Override
	public int eliminarBoletin(Boletin boletin, Usuario usuario) {
		
		return DaoBoletin.getInstance().eliminarBoletin(boletin,usuario);
	}

	@Override
	public ArrayList<Boletin> getBoletines(String anio) {
			
		return DaoBoletin.getInstance().getBoletines(anio);
	}

	@Override
	public ArrayList<Boletin> getBoletinesTodos() {
		
		return DaoBoletin.getInstance().getBoletinesTodos();
	}
	
	
	

}
