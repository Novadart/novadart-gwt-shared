package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.novadart.gwtshared.client.validation.ValidationBundle;

public class ValidatedTextBox extends ValidatedWidget<TextBox, String> implements HasText, HasBlurHandlers, HasFocusHandlers {
	
	private final TextBox textBox = new TextBox();
	
	public ValidatedTextBox(ValidationBundle<String> validationBundle) {
		super(validationBundle);
		initWidget(textBox);
		addStyleName("ValidatedTextBox");
	}

	public ValidatedTextBox() {
		this(null);
	}
	
	@Override
	protected void resetUI() {
		setText("");
	}
	
	@Override
	protected void updateUI(boolean isValid) {
		
	}
	
	@Override
	protected String getValue() {
		return getText();
	}

	@Override
	public String getText() {
		return textBox.getText();
	}

	@Override
	public void setText(String text) {
		textBox.setText(text);
	}
	
	public void setReadOnly(boolean readonly){
		textBox.setReadOnly(readonly);
	}
	
	public void setEnabled(boolean enabled){
		textBox.setEnabled(enabled);
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return textBox.addFocusHandler(handler);
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return textBox.addBlurHandler(handler);
	}

}
