package com.novadart.gwtshared.client.validation;


public abstract class EmailValidation extends OptionalFieldValidation<String> {

	public EmailValidation() {
		super(false);
	}

	public EmailValidation(boolean optional) {
		super(optional);
	}

	@Override
	public boolean isValid(String value) {
		return isOptional() ? (value.isEmpty() || Validation.isEmail(value)) : Validation.isEmail(value);
	}

}
