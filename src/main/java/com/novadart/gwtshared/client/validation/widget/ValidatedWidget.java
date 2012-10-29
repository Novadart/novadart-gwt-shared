package com.novadart.gwtshared.client.validation.widget;

import java.util.ArrayList;
import java.util.List;

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
	private List<ValidationBundle<ValueType>> validationBundleList;

	public ValidatedWidget(ValidationBundle<ValueType> validationBundle) {
		if(validationBundle != null) {
			addValidationBundle(validationBundle);
		}
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

	public void addValidationBundle(ValidationBundle<ValueType> validationBundle) {
		if(validationBundle == null) {
			return;
		}
		
		if(validationBundleList == null){
			validationBundleList = new ArrayList<ValidationBundle<ValueType>>();
		}
		this.validationBundleList.add(validationBundle);
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
		if(validationBundleList == null){
			valid = true;
			return;
		}

		ValidationBundle<ValueType> failingValidationBundle = null;

		for (ValidationBundle<ValueType> vb : validationBundleList) {
			valid = vb.isValid(getValue());
			if(!valid) {
				failingValidationBundle = vb;
				break;
			}
		}

		if(valid){
			hideMessage();

			removeStyleName("ValidatedWidget-validationError");
			addStyleName("ValidatedWidget-validationOk");

		} else {
			showErrorMessage(failingValidationBundle);

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

	protected void hideMessage(){
		if(baloonMessage != null){
			baloonMessage.hide();
		}
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