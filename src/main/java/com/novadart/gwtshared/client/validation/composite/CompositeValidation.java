package com.novadart.gwtshared.client.validation.composite;

import java.util.ArrayList;
import java.util.List;

import com.novadart.gwtshared.client.validation.widget.ValidatedWidget;

public abstract class CompositeValidation {
	
	private final List<ValidatedWidget<?>> widgets = new ArrayList<ValidatedWidget<?>>();
	private boolean valid = false;
	
	protected abstract boolean validate(List<ValidatedWidget<?>> widgets);
	public abstract String getErrorMessage();
	
	public void addWidget(ValidatedWidget<?> widget){
		widgets.add(widget);
	}
	
	public boolean isValid(){
		return valid;
	}
	
	public void validate(){
		if(widgets.isEmpty()){
			valid = true;
			return;
		}
		
		valid = validate(widgets);
	}
	
	public void reset(){
		valid = false;
		for (ValidatedWidget<?> w : widgets) {
			w.reset();
		}
	}
	
}
