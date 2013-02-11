package com.novadart.gwtshared.client.textbox;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.uibinder.client.UiConstructor;
import com.novadart.gwtshared.client.validation.ValidationBundle;
import com.novadart.gwtshared.client.validation.widget.ValidatedTextBox;

public class RichTextBox extends ValidatedTextBox {
	
	public static interface Style extends ValidatedTextBox.Style {
		String label();
	}

	private final String label;

	public @UiConstructor RichTextBox(Style style, String label) {
		this(style, label, null);
	}
	
	public RichTextBox(Style style, String label, ValidationBundle<String> validationBundle) {
		super(style);
		this.label = label != null ? label.trim() : "";
		setValidationBundle(validationBundle);

		addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				if(RichTextBox.super.getText().equalsIgnoreCase("")){
					setText(RichTextBox.this.label);
					addStyleName(((Style)getStyle()).label());
				}
			}
		});

		addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				if(RichTextBox.super.getText().equalsIgnoreCase(RichTextBox.this.label)){
					setText("");
				}
				removeStyleName(((Style)getStyle()).label());
			}
		});

		setText(label);
		addStyleName(((Style)getStyle()).label());
	}
	
	@Override
	protected void resetUI() {
		setText(label);
	}
	
	@Override
	public void setText(String text) {
		if(text == null){
			return;
		}
		
		if(! text.equalsIgnoreCase(label)){
			removeStyleName(((Style)getStyle()).label());
		}
		
		super.setText(text);
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
