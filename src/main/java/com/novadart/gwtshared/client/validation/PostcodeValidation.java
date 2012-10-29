package com.novadart.gwtshared.client.validation;


public abstract class PostcodeValidation implements ValidationBundle<String> {

	@Override
	public boolean isValid(String text) {
		return Validation.isInteger(text) && text.length() == 5;
	}

}
