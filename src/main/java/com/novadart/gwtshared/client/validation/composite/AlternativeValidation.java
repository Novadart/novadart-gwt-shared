package com.novadart.gwtshared.client.validation.composite;

import java.util.List;

import com.novadart.gwtshared.client.validation.widget.ValidatedWidget;

/**
 * This validation Object makes sure that all the given validated widgets are valid (either empty or with valid content)
 * @author Giordano Battilana
 *
 */
public abstract class AlternativeValidation extends CompositeValidation {
	
	@Override
	protected boolean validate(List<ValidatedWidget<?, ?>> widgets) {
		boolean allValid = true;
		boolean oneNotEmpty = false;
		
		for (ValidatedWidget<?, ?> w : widgets) {
			
			if(w.isEmpty()){
				w.setValidationOkStyle();
				w.hideMessage(); //in case the baloon is shown
			} else {
				w.validate();
				
				if(!w.isValid()){
					allValid = false;
					break;
				} else {
					oneNotEmpty = true;
				}
			}
			
		}
		
		if( ! (allValid && oneNotEmpty) ){
			for (ValidatedWidget<?, ?> w : widgets) {
				w.setValidationErrorStyle();
			}
		}
		
		return allValid && oneNotEmpty;
	}

}
