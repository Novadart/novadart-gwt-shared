package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.novadart.gwtshared.client.validation.ValidationBundle;

public abstract class ValidatedWidget<ValueType> extends Composite implements TakesValue<ValueType> {
	
	public static interface Handler {
		public void onValidation(boolean value);
	}
	
	public static interface Style extends CssResource {
		String validationOk();
		String validationError();
		String validationBaloon();
		String validationMessage();
	}
	
	private final Style style;
	private boolean valid = false;
	private boolean showMessageOnError = true;
	private ValidationBaloonMessage baloonMessage;
	private ValidationBundle<ValueType> validationBundle;
	private Handler handler;

	public ValidatedWidget(Style style) {
		this(style, null);
	}
	
	public ValidatedWidget(Style style, ValidationBundle<ValueType> validationBundle) {
		if(validationBundle != null) {
			setValidationBundle(validationBundle);
		}
		this.style = style;
		this.style.ensureInjected();
	}
	
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	protected Style getStyle() {
		return style;
	}
	
	public void setShowMessageOnError(boolean showMessageOnError) {
		this.showMessageOnError = showMessageOnError;
	}

	public void setValidationBundle(ValidationBundle<ValueType> validationBundle) {
		if(validationBundle == null) {
			return;
		}

		this.validationBundle =validationBundle;
	}

	@Override
	protected void onUnload() {
		super.onUnload();
		hideMessage();
	}

	protected abstract void updateUI(boolean isValid);

	protected abstract void resetUI();

	public abstract boolean isEmpty();

	public void validate(){
		validate(true);
	}
	
	public void validate(boolean notifyHandler){
		valid = true;

		if(validationBundle == null){
			return;
		}

		valid = validationBundle.isValid(getValue());

		if(valid){
			hideMessage();
			setValidationOkStyle();
		} else {
			if(this.showMessageOnError){
				showErrorMessage(validationBundle.getErrorMessage());
			}
			setValidationErrorStyle();
		}
		
		if(notifyHandler && this.handler != null){
			this.handler.onValidation(valid);
		}

		updateUI(valid);
	}
	

	public void reset() {
		valid = false;
		removeValidationStyles();
		hideMessage();
		resetUI();
	}

	public void hideMessage(){
		if(baloonMessage != null){
			baloonMessage.hide();
		}
	}

	protected void removeValidationStyles(){
		removeStyleName(style.validationError());
		removeStyleName(style.validationOk());
	}

	public void setValidationErrorStyle(){
		removeStyleName(style.validationOk());
		addStyleName(style.validationError());
	}

	public void setValidationOkStyle(){
		removeStyleName(style.validationError());
		addStyleName(style.validationOk());
	}

	protected void showErrorMessage(ValidationBundle<ValueType> failingValidationBundle){
		String errorMessage = failingValidationBundle.getErrorMessage();
		showErrorMessage(errorMessage);
	}

	public void showErrorMessage(String errorMessage){
		if(errorMessage == null){
			return;
		}

		if(baloonMessage == null){
			baloonMessage = new ValidationBaloonMessage(style);
		}

		baloonMessage.setMessage(errorMessage);
		if(getStyleName().contains(style.validationOk())){
			removeStyleName(style.validationOk());
		}
		if(!getStyleName().contains(style.validationError())){
			addStyleName(style.validationError());
		}
		baloonMessage.showNextTo(this);
	}

	public boolean isValid() {
		return valid;
	}

}