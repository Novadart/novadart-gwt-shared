package com.novadart.gwtshared.client.textbox.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface TextBoxResources extends ClientBundle {
	public static final TextBoxResources get = GWT.create(TextBoxResources.class);

	TextBoxStyle style();

}
