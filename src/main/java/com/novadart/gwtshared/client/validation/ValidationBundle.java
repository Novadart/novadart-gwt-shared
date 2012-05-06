package com.novadart.gwtshared.client.validation;

public interface ValidationBundle<ValueType> {
	public boolean isValid(ValueType value);
	public String getErrorMessage();
}
