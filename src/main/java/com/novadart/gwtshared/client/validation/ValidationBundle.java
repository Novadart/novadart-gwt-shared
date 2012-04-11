package com.novadart.gwtshared.client.validation;

public interface ValidationBundle {
	public boolean isValid(String text);
	public String getErrorMessage();
}
