package com.novadart.gwtshared.client.validation;

import com.google.gwt.regexp.shared.RegExp;
import com.novadart.gwtshared.client.RegularExpressionConstants;

public abstract class VatIdValidation extends OptionalFieldValidation<String> {
	
	private static final RegExp VATID_REGEXP = 
			RegExp.compile(RegularExpressionConstants.VAT_ID_REGEX);
	
	
	public VatIdValidation() {
		super(false);
	}
	
	public VatIdValidation(boolean optional) {
		super(optional);
	}

	@Override
	public boolean isValid(String text) {
		return isOptional() ? (text.isEmpty() || isVatId(text)) : isVatId(text);
	}

	public static boolean isVatId(String text){
		return VATID_REGEXP.exec(text) != null;
	}

}
