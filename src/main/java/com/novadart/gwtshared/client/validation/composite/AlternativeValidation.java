package com.novadart.gwtshared.client.validation.composite;

import java.util.List;

import com.novadart.gwtshared.client.validation.widget.ValidatedWidget;

/**
 * This validation Object makes sure that all the given validated widgets are valid (either empty or with valid content)
 * @author Giordano Battilana
 *
 */
public abstract class AlternativeValidation extends CompositeValidation {
	
	private ValidatedWidget.Handler validationHandler = new ValidatedWidget.Handler() {
		@Override
		public void onValidation(boolean value) {
			validate();
		}
	};
	
	public void addWidget(ValidatedWidget<?> widget) {
		widget.setHandler(validationHandler);
		super.addWidget(widget);
	};
	
	@Override
	protected boolean validate(List<ValidatedWidget<?>> widgets) {
		boolean allValid = true;
		boolean allEmpty = true;
		
		for (ValidatedWidget<?> w : widgets) {
			
			if(w.isEmpty()){
				w.setValidationOkStyle();
				w.hideMessage(); //in case the baloon is shown
			} else {
				allEmpty = false;

				w.validate(false);
				allValid &= w.isValid();
			}
			
		}
		
		if( allEmpty ){
			for (ValidatedWidget<?> w : widgets) {
				w.setValidationErrorStyle();
			}
		}
		
		return allValid && !allEmpty;
	}

}
