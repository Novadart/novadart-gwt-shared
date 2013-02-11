package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.novadart.gwtshared.client.validation.TextLengthValidation;
import com.novadart.gwtshared.client.validation.ValidationBundle;

public class ValidatedTextArea extends ValidatedWidget<TextArea, String> implements 
HasText, HasBlurHandlers, HasFocusHandlers, HasKeyUpHandlers, Focusable, HasEnabled {

	private final TextArea textArea = new TextArea();
	
	public ValidatedTextArea(Style style, TextLengthValidation textValidationBundle) {
		this(style, textValidationBundle, null);
	}
	
	public ValidatedTextArea(Style style, final TextLengthValidation textValidationBundle, ValidationBundle<String> validationBundle) {
		super(style, validationBundle);
		initWidget(textArea);
		
		addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(!textValidationBundle.isValid(getText())){
					setText(getText().substring(0, textValidationBundle.getMaxLength()));
					showErrorMessage(textValidationBundle);
				} else {
					hideMessage();
					removeValidationStyles();
				}
			}
			
		});
		
		addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				hideMessage();
				removeValidationStyles();
			}
		});
	}
	
	
	@Override
	protected void updateUI(boolean isValid) {
	}

	@Override
	protected void resetUI() {
		setText("");
	}

	@Override
	protected String getValue() {
		return getText();
	}
	
	@Override
	public int getTabIndex() {
		return textArea.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		textArea.setAccessKey(key);
	}

	@Override
	public void setFocus(boolean focused) {
		textArea.setFocus(focused);
	}

	@Override
	public void setTabIndex(int index) {
		textArea.setTabIndex(index);
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return textArea.addKeyUpHandler(handler);
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return textArea.addFocusHandler(handler);
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return textArea.addBlurHandler(handler);
	}

	@Override
	public String getText() {
		return textArea.getText();
	}

	@Override
	public void setText(String text) {
		textArea.setText(text);
	}
	
	@Override
	public boolean isEmpty() {
		return getText().isEmpty();
	}
	
	@Override
	public boolean isEnabled() {
		return textArea.isEnabled();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		textArea.setEnabled(enabled);
	}

}
