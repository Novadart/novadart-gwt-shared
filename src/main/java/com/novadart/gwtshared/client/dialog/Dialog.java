package com.novadart.gwtshared.client.dialog;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;


public class Dialog extends PopupPanel {
	private static final int HEIGHT_DIVISION_VALUE = 2;
	private static final int WIDTH_DIVISION_VALUE = 2;

	private int heightDivisionValue = HEIGHT_DIVISION_VALUE;
	private int widthDivisionValue = WIDTH_DIVISION_VALUE;

	private final FocusPanel body = new FocusPanel();

	public Dialog() {
		this(true);
	}
	
	
	public Dialog(boolean hideOnEscKey) {
		setModal(true);
		setGlassEnabled(true);
		addStyleName("Dialog");

		body.setStyleName("Dialog-focusableBody");
		if(hideOnEscKey) {
			body.addKeyUpHandler(new KeyUpHandler() {

				@Override
				public void onKeyUp(KeyUpEvent event) {
					if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE){
						hide();
					}
				}
			});
		}
	}

	@Override
	public void setWidget(IsWidget w) {
		if(body.getParent() != null){
			body.removeFromParent();
		}
		body.setWidget(w);
		super.setWidget(body);
	}

	@Override
	public void setWidget(Widget w) {
		setWidget((IsWidget)w);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		body.setFocus(true);
	}

	public void setHeightDivisionValue(int heightDivisionValue) {
		this.heightDivisionValue = heightDivisionValue>0 ? heightDivisionValue : HEIGHT_DIVISION_VALUE;
	}

	public void setWidthDivisionValue(int widthDivisionValue) {
		this.widthDivisionValue = widthDivisionValue>0 ? widthDivisionValue : WIDTH_DIVISION_VALUE;
	}

	@Override
	public void show() {
		Window.scrollTo(0, 0);
		super.show();
	}

	public void showCentered(){

		setPopupPositionAndShow(new PositionCallback() {

			@Override
			public void setPosition(int offsetWidth, int offsetHeight) {
				int windowHeight = Window.getClientHeight();
				int windowWidth = Window.getClientWidth();

				int x = (windowWidth - offsetWidth) / widthDivisionValue;
				int y = (windowHeight - offsetHeight) / heightDivisionValue;

				Dialog.this.setPopupPosition(x, y);
			}
		});
	}

}