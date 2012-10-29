package com.novadart.gwtshared.client.validation;

public abstract class TextLengthValidation implements ValidationBundle<String> {

	private final int maxLength;
	
	public TextLengthValidation(int maxLength) {
		this.maxLength = maxLength >= 1 ? maxLength : 0;
	}
	
	@Override
	public boolean isValid(String value) {
		return value != null && value.length() <= maxLength; 
	}
	
	public int getMaxLength() {
		return maxLength;
	}
}
