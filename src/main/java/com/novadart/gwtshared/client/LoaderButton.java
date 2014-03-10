package com.novadart.gwtshared.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

public class LoaderButton extends Composite implements HasClickHandlers, HasText, HasEnabled {
	
	public static interface Style extends CssResource {
		String button();
		String loader();
	}

	private final SimplePanel wrapper = new SimplePanel();
	private final Button button = new Button();
	private final Image loader = new Image();
	
	public LoaderButton(ImageResource loaderImage, Style style) {
		loader.setUrl(loaderImage.getSafeUri());
		initWidget(wrapper);
		wrapper.setWidget(button);
		setStyleName("LoaderButton");
		button.setStyleName(style.button());
		loader.setStyleName(style.loader());
	}
	
	public void showLoader(boolean value){
		wrapper.setWidget(value ? loader : button);
	}
	
	public void reset(){
		showLoader(false);
	}
	
	public Button getButton() {
		return button;
	}
	
	public Image getLoader() {
		return loader;
	}
	
	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return button.addClickHandler(handler);
	}

	@Override
	public String getText() {
		return button.getText();
	}

	@Override
	public void setText(String text) {
		button.setText(text);
	}

	@Override
	public boolean isEnabled() {
		return button.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		button.setEnabled(enabled);
	}
	
}
