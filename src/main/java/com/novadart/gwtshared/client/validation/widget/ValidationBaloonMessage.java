package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.novadart.gwtshared.client.validation.widget.ValidatedWidget.Style;

public class ValidationBaloonMessage extends PopupPanel {

	private static ValidationBaloonMessageUiBinder uiBinder = GWT
			.create(ValidationBaloonMessageUiBinder.class);

	interface ValidationBaloonMessageUiBinder extends
			UiBinder<Widget, ValidationBaloonMessage> {
	}
	
	@UiField(provided=true) Label validationMessage;
	
	public ValidationBaloonMessage(Style style) {
		this(style, "");
	}
	
	public ValidationBaloonMessage(Style style, String message) {
		validationMessage = new Label();
		validationMessage.setText(message);
		validationMessage.setStyleName(style.validationMessage());
		setWidget(uiBinder.createAndBindUi(this));
		addStyleName(style.validationBaloon());
	}
	
	@UiHandler("body")
	void onPanelClicked(ClickEvent e){
		hide();
	}
	
	public void setMessage(String message){
		validationMessage.setText(message);
	}

	public void showNextTo(final Widget widget){
		setPopupPositionAndShow(new PositionCallback() {

			public void setPosition(int offsetWidth, int offsetHeight) {
				Widget w = widget;
				int widgX = w.getAbsoluteLeft();
				int widgY = w.getAbsoluteTop();
				int widgWidth = w.getOffsetWidth();
				int widgHeight = w.getOffsetHeight();
				
				int x = widgX + widgWidth;
				int y = widgY + (widgHeight - getOffsetHeight()) / 2;
				
				setPopupPosition(x, y);
			}
			
		});
	}
	
}
