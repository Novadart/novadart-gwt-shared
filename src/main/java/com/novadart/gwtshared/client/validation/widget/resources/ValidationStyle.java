package com.novadart.gwtshared.client.validation.widget.resources;

import com.google.gwt.resources.client.CssResource;

public interface ValidationStyle extends CssResource {

	String validationError();

	String validationOk();

	String validationMessage();
	
	@ClassName("ValidatedTextBox")
	String validatedTextBox();
	
	@ClassName("ValidationBaloonMessage")
	String validationBaloonMessage();

}
