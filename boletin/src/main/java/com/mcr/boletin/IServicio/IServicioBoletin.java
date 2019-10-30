package com.mcr.boletin.IServicio;



import java.util.ArrayList;

import com.mcr.boletin.model.Boletin;
import com.mcr.boletin.model.Usuario;
import com.vaadin.data.util.BeanItemContainer;

public interface IServicioBoletin {

	public BeanItemContainer<Boletin> getBoletines();
	public ArrayList<Boletin> getBoletines(String anio);
	public ArrayList<Boletin> getBoletinesTodos();	
	public BeanItemContainer<Boletin> getBoletinesModificacion();
	public int guardarBoletin(Boletin boletin, Usuario usuario);
	public int modificarBoletin(Boletin boletin, Usuario usuario);
	public int eliminarBoletin(Boletin boletin, Usuario usuario);
	
}
