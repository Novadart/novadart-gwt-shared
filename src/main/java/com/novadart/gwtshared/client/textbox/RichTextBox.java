package com.novadart.gwtshared.client.textbox;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.uibinder.client.UiConstructor;
import com.novadart.gwtshared.client.validation.ValidationBundle;
import com.novadart.gwtshared.client.validation.widget.ValidatedTextBox;

public class RichTextBox extends ValidatedTextBox {

	private final String label;

	public RichTextBox(String label, ValidationBundle<String> validationBundle) {
		this.label = label != null ? label.trim() : "";
		setValidationBundle(validationBundle);

		addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				if(RichTextBox.super.getText().equalsIgnoreCase("")){
					setText(RichTextBox.this.label);
					addStyleName("RichTextBox-labelStyle");
				}
			}
		});

		addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				if(RichTextBox.super.getText().equalsIgnoreCase(RichTextBox.this.label)){
					setText("");
				}
				removeStyleName("RichTextBox-labelStyle");
			}
		});

		setText(label);
		addStyleName("RichTextBox RichTextBox-labelStyle");
	}
	
	@Override
	protected void resetUI() {
		setText(label);
	}

	public @UiConstructor RichTextBox(String label) {
		this(label, null);
	}
	
	@Override
	public String getText() {
		String text = super.getText();
		if(text.trim().equalsIgnoreCase(label)){
			return "";
		} else {
			return text;
		}
	}

}
