package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.novadart.gwtshared.client.validation.ValidationBundle;

public class ValidatedTextBox extends TextBox {


	private boolean valid = false;
	private ValidationBaloonMessage baloonMessage;
	private ValidationBundle validationBundle;

	public ValidatedTextBox(ValidationBundle validationBundle) {
		this.validationBundle = validationBundle;
	}

	public ValidatedTextBox() {
		setStyleName("ValidatedTextBox");

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
				removeStyleName("ValidatedTextBox-validationError");
				addStyleName("ValidatedTextBox-validationOk");
			} else {
				removeStyleName("ValidatedTextBox-validationError");
				removeStyleName("ValidatedTextBox-validationOk");
			}
		} else {
			showMessage();
			removeStyleName("ValidatedTextBox-validationOk");
			addStyleName("ValidatedTextBox-validationError");
		}
	}

	public void reset() {
		setText("");
		removeStyleName("ValidatedTextBox-validationError");
		removeStyleName("ValidatedTextBox-validationOk");
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
			baloonMessage = new ValidationBaloonMessage(errorMessage);
		}

		baloonMessage.showNextTo(this);
	}

	public boolean isValid() {
		return valid;
	}

}
