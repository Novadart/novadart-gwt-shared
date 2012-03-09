package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.novadart.gwtshared.client.validation.widget.resources.ValidationResources;
import com.novadart.gwtshared.client.validation.widget.resources.ValidationStyle;

public abstract class ValidatedTextBox extends TextBox {


	private boolean valid = false;
	private ValidationBaloonMessage baloonMessage;
	private final String errorMessage;
	private final ValidationStyle style;

	public ValidatedTextBox(String errorMessage) {
		this(ValidationResources.get.style(), errorMessage);
	}

	public ValidatedTextBox(ValidationStyle style, String errorMessage) {
		this.errorMessage = errorMessage;
		this.style = style;
		this.style.ensureInjected();
		setStyleName(style.validatedTextBox());

		addBlurHandler(new BlurHandler() {

			public void onBlur(BlurEvent event) {
				validate();
			}
		});
	}
	
	@Override
	protected void onUnload() {
		super.onUnload();
		hideMessage();
	}

	protected abstract boolean validate(String text);

	public void validate(){
		valid = validate(getText());

		if(valid){
			hideMessage();
			
			if(!getText().isEmpty()){
				removeStyleName(style.validationError());
				addStyleName(style.validationOk());
			} else {
				removeStyleName(style.validationError());
				removeStyleName(style.validationOk());
			}
		} else {
			showMessage();
			removeStyleName(style.validationOk());
			addStyleName(style.validationError());
		}
	}

	public void reset() {
		setText("");
		removeStyleName(style.validationError());
		removeStyleName(style.validationOk());
		hideMessage();
	}

	private void hideMessage(){
		if(baloonMessage != null){
			baloonMessage.hide();
		}
	}

	private void showMessage(){
		if(baloonMessage == null){
			baloonMessage = new ValidationBaloonMessage(this.style, this.errorMessage);
		}

		baloonMessage.showNextTo(this);
	}

	public boolean isValid() {
		return valid;
	}

}
