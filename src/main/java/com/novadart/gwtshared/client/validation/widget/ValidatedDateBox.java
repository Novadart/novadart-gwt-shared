package com.novadart.gwtshared.client.validation.widget;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;
import com.novadart.gwtshared.client.validation.ValidationBundle;

public class ValidatedDateBox extends ValidatedWidget<Date> implements HasEnabled, HasValue<Date> {
	
	private final DateBox dateBox = new DateBox();

	public ValidatedDateBox(ValidatedWidget.Style style) {
		super(style);
		initWidget(dateBox);
	}
	
	public ValidatedDateBox(ValidatedWidget.Style style, ValidationBundle<Date> validationBundle) {
		super(style, validationBundle);
		initWidget(dateBox);
	}
	
	public void setFormat(Format format){
		dateBox.setFormat(format);
	}

	@Override
	protected void updateUI(boolean isValid) {
	}

	@Override
	protected void resetUI() {
		dateBox.setValue(null);
	}

	@Override
	public Date getValue() {
		return dateBox.getValue();
	}
	
	public TextBox getTextBox(){
		return dateBox.getTextBox();
	}

	@Override
	public boolean isEmpty() {
		return getValue() == null || getTextBox().getText().isEmpty();
	}

	@Override
	public boolean isEnabled() {
		return dateBox.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		dateBox.setEnabled(enabled);
	}

	public void setValue(Date d) {
		dateBox.setValue(d);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
		return dateBox.addValueChangeHandler(handler);
	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		dateBox.setValue(value, fireEvents);
	}
	
}
