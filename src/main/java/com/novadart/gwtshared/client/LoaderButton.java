package com.novadart.gwtshared.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.novadart.gwtshared.client.resources.ImageResources;

public class LoaderButton extends Composite implements HasClickHandlers, HasText {

	private final SimplePanel wrapper = new SimplePanel();
	private final Button button = new Button();
	private final Image loader = new Image();
	
	public LoaderButton() {
		this(ImageResources.INSTANCE.loader());
	}
	
	public LoaderButton(ImageResource loaderImage) {
		loader.setUrl(loaderImage.getSafeUri());
		initWidget(wrapper);
		wrapper.setWidget(button);
		setStyleName("LoaderButton");
		button.setStyleName(getStylePrimaryName()+"-button");
		loader.setStyleName(getStylePrimaryName()+"-loader");
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
	
}
