package com.mcr.boletin.tools;

import org.tepi.filtertable.FilterGenerator;

import com.vaadin.data.Container.Filter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Field;

public class DemoFilterGenerator implements FilterGenerator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Filter generateFilter(Object propertyId, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter generateFilter(Object propertyId, Field<?> originatingField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractField<?> getCustomFilterComponent(Object propertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void filterRemoved(Object propertyId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void filterAdded(Object propertyId,
			Class<? extends Filter> filterType, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Filter filterGeneratorFailed(Exception reason, Object propertyId,
			Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
