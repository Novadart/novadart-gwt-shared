package com.novadart.gwtshared.client.dialog;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.CssResource;
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
	
	public static interface Style extends CssResource {
		String dialog();
		String focusableBody();
	}

	private final FocusPanel body = new FocusPanel();

	public Dialog(Style style) {
		this(style, true);
	}
	
	
	private boolean scrollWindowOnTop = true;
	
	public Dialog(Style style, boolean hideOnEscKey) {
		style.ensureInjected();
		setModal(true);
		setGlassEnabled(true);
		addStyleName(style.dialog());

		body.setStyleName(style.focusableBody());
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
	
	public void setScrollWindowOnTop(boolean scrollWindowOnTop) {
		this.scrollWindowOnTop = scrollWindowOnTop;
	}
	
	public boolean isScrollWindowOnTop() {
		return scrollWindowOnTop;
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
		
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			
			@Override
			public void execute() {
				setPopupPositionAndShow(new PositionCallback() {

					@Override
					public void setPosition(int offsetWidth, int offsetHeight) {
						if(scrollWindowOnTop){
							Window.scrollTo(0, 0);
						}
						
						int windowHeight = Window.getClientHeight();
						int windowWidth = Window.getClientWidth();

						int x = (windowWidth - offsetWidth) / widthDivisionValue;
						int y = (windowHeight - offsetHeight) / heightDivisionValue;

						Dialog.this.setPopupPosition(x, y);
						body.setFocus(true);
					}
				});
			}
		});
	}

	public void setHeightDivisionValue(int heightDivisionValue) {
		this.heightDivisionValue = heightDivisionValue>0 ? heightDivisionValue : HEIGHT_DIVISION_VALUE;
	}

	public void setWidthDivisionValue(int widthDivisionValue) {
		this.widthDivisionValue = widthDivisionValue>0 ? widthDivisionValue : WIDTH_DIVISION_VALUE;
	}

	public void showCentered(){
		// this is needed because otherwise we have a bad effect of the dialog translating on the page
		// position will be fixed in onLoad and then the dialog will be shown		
		setVisible(false);
		show();
	}

}