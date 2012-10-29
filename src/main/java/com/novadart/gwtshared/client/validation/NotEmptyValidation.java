package com.novadart.gwtshared.client.validation;


public abstract class NotEmptyValidation implements ValidationBundle<String> {

	@Override
	public boolean isValid(String text) {
		return !text.isEmpty();
	}

}
