package com.novadart.gwtshared.client.validation;

import com.novadart.gwtshared.client.validation.ValidationBundle;

public class DefaultValidation<T> implements ValidationBundle<T> {

	@Override
	public boolean isValid(T value) {
		return true;
	}

	@Override
	public String getErrorMessage() {
		return null;
	}
}
