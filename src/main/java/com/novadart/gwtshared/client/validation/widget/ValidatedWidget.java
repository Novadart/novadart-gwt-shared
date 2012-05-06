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
	private ValidationBaloonMessage baloonMessage;
	private ValidationBundle<ValueType> validationBundle;

	public ValidatedWidget(ValidationBundle<ValueType> validationBundle) {
		this.validationBundle = validationBundle;
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
		this.validationBundle = validationBundle;
	}
	
	@Override
	protected void onUnload() {
		super.onUnload();
		hideMessage();
	}
	
	protected abstract void updateUI(boolean isValid);
	
	protected abstract void resetUI();
	
	protected abstract ValueType getValue();

	public void validate(){
		if(validationBundle != null){
			valid = validationBundle.isValid(getValue());
		} else {
			valid = true;
		}

		if(valid){
			hideMessage();
			
			removeStyleName("ValidatedWidget-validationError");
			addStyleName("ValidatedWidget-validationOk");
			
		} else {
			showMessage();
			
			removeStyleName("ValidatedWidget-validationOk");
			addStyleName("ValidatedWidget-validationError");
		}
		
		updateUI(valid);
	}

	public void reset() {
		valid = false;
		removeStyleName("ValidatedWidget-validationError");
		removeStyleName("ValidatedWidget-validationOk");
		hideMessage();
		resetUI();
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