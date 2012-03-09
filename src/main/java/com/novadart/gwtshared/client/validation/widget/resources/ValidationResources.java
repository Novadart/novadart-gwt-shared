package com.novadart.gwtshared.client.validation.widget.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface ValidationResources extends ClientBundle {
	public static final ValidationResources get = GWT.create(ValidationResources.class);

	ValidationStyle style();

}
