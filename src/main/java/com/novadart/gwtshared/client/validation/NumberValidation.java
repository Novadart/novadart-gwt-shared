package com.novadart.gwtshared.client.validation;


public abstract class NumberValidation extends OptionalFieldValidation<String> {

	public NumberValidation() {
		super(false);
	}

	public NumberValidation(boolean optional) {
		super(optional);
	}

	@Override
	public boolean isValid(String text) {
		return isOptional() ? (text.isEmpty() || Validation.isPositiveNumber(text)) : Validation.isPositiveNumber(text);
	}

}
