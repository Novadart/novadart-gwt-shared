package com.novadart.gwtshared.client.validation;

import java.util.Date;


public abstract class NotEmptyDateValidation implements ValidationBundle<Date> {

	@Override
	public boolean isValid(Date date) {
		return date != null;
	}

}
