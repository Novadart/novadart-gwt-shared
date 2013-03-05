package com.novadart.gwtshared.client.validation.composite;

import java.util.List;

import com.novadart.gwtshared.client.validation.widget.ValidatedWidget;

/**
 * This validation Object makes sure that all the given validated widgets are valid.
 * They cannot be empty
 * @author Giordano Battilana
 *
 */
public abstract class MandatoryValidation extends CompositeValidation {
	
	@Override
	protected boolean validate(List<ValidatedWidget<?>> widgets) {
		for (ValidatedWidget<?> w : widgets) {
			w.validate();
			
			if(!w.isValid()){
				return false;
			}
		}
		return true;
	}

}
