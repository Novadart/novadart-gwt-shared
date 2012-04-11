package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.novadart.gwtshared.client.validation.ValidationBundle;
import com.novadart.gwtshared.client.validation.widget.resources.ValidationResources;
import com.novadart.gwtshared.client.validation.widget.resources.ValidationStyle;

public class ValidatedTextBox extends TextBox {


	private boolean valid = false;
	private ValidationBaloonMessage baloonMessage;
	private final ValidationStyle style;
	private ValidationBundle validationBundle;

	public ValidatedTextBox(ValidationBundle validationBundle) {
		this(ValidationResources.get.style());
		this.validationBundle = validationBundle;
	}
	
	public ValidatedTextBox() {
		this(ValidationResources.get.style());
	}

	public ValidatedTextBox(ValidationStyle style) {
		this.style = style;
		this.style.ensureInjected();
		setStyleName(style.validatedTextBox());

		addBlurHandler(new BlurHandler() {

			public void onBlur(BlurEvent event) {
				validate();
			}
		});
	}
	
	public void setValidationBundle(ValidationBundle validationBundle) {
		this.validationBundle = validationBundle;
	}
	
	@Override
	protected void onUnload() {
		super.onUnload();
		hideMessage();
	}

	public void validate(){
		if(validationBundle != null){
			valid = validationBundle.isValid(getText());
		} else {
			valid = true;
		}

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
			String errorMessage = validationBundle.getErrorMessage();
			if(errorMessage == null){
				return;
			}
			baloonMessage = new ValidationBaloonMessage(this.style, errorMessage);
		}

		baloonMessage.showNextTo(this);
	}

	public boolean isValid() {
		return valid;
	}

}
