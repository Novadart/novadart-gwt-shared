package com.novadart.gwtshared.client.validation;

import com.google.gwt.regexp.shared.RegExp;
import com.novadart.gwtshared.client.RegularExpressionConstants;

public abstract class SsnValidation extends OptionalFieldValidation<String> {
	
	private static final RegExp SSN_REGEXP = 
			RegExp.compile(RegularExpressionConstants.SSN_REGEX);

	
	public SsnValidation() {
		super(false);
	}
	
	public SsnValidation(boolean optional) {
		super(optional);
	}

	@Override
	public boolean isValid(String text) {
		return isOptional() ? (text.isEmpty() || isSSN(text)) : isSSN(text);
	}

	public static boolean isSSN(String text){
		return SSN_REGEXP.exec(text) != null;
	}
}
