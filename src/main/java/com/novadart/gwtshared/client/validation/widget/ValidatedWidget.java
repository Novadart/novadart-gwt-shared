package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Widget;
import com.novadart.gwtshared.client.validation.ValidationBundle;

public abstract class ValidatedWidget<W extends HasBlurHandlers, ValueType> extends Composite {

	private boolean valid = false;
	private boolean showMessageOnError = true;
	private ValidationBaloonMessage baloonMessage;
	private ValidationBundle<ValueType> validationBundle;

	public ValidatedWidget(ValidationBundle<ValueType> validationBundle) {
		if(validationBundle != null) {
			setValidationBundle(validationBundle);
		}
	}
	
	public void setShowMessageOnError(boolean showMessageOnError) {
		this.showMessageOnError = showMessageOnError;
	}

	@Override
	protected void initWidget(Widget widget) {
		super.initWidget(widget);

		setStyleName("ValidatedWidget");

		((FocusWidget)getWidget()).addBlurHandler(new BlurHandler() {

			public void onBlur(BlurEvent event) {
				validate();
			}
		});
	}

	public ValidatedWidget() {
		this(null);
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

	protected abstract ValueType getValue();

	public abstract boolean isEmpty();

	public void validate(){
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
		removeStyleName("ValidatedWidget-validationError");
		removeStyleName("ValidatedWidget-validationOk");
	}

	public void setValidationErrorStyle(){
		removeStyleName("ValidatedWidget-validationOk");
		addStyleName("ValidatedWidget-validationError");
	}

	public void setValidationOkStyle(){
		removeStyleName("ValidatedWidget-validationError");
		addStyleName("ValidatedWidget-validationOk");
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
			baloonMessage = new ValidationBaloonMessage();
		}

		baloonMessage.setMessage(errorMessage);
		if(getStyleName().contains("ValidatedWidget-validationOk")){
			removeStyleName("ValidatedWidget-validationOk");
		}
		if(!getStyleName().contains("ValidatedWidget-validationError")){
			addStyleName("ValidatedWidget-validationError");
		}
		baloonMessage.showNextTo(this);
	}

	public boolean isValid() {
		return valid;
	}

}