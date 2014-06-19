package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.user.client.ui.Widget;
import com.novadart.gwtshared.client.validation.ValidationBundle;


public abstract class FocusableValidatedWidget<W extends HasBlurHandlers, ValueType> extends ValidatedWidget<ValueType> {

	public FocusableValidatedWidget(ValidatedWidget.Style style) {
		super(style);
	}
	
	public FocusableValidatedWidget(ValidatedWidget.Style style, ValidationBundle<ValueType> validationBundle) {
		super(style, validationBundle);
	}
	
	public abstract W getBaseWidget();
	
	@Override
	protected void initWidget(Widget widget) {
		super.initWidget(widget);

		getBaseWidget().addBlurHandler(new BlurHandler() {

			public void onBlur(BlurEvent event) {
				validate();
			}
		});
	}
	
	
}