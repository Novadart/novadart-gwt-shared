package com.novadart.gwtshared.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.novadart.gwtshared.client.resources.ImageResources;

public class LoaderButton extends Composite implements HasClickHandlers, HasText {
	
	public interface Style extends CssResource {
		String button();
		String loader();
	}
	
	public interface DefaultBundle extends ClientBundle {
		@Source("LoaderButton.css")
		Style defaultStyle();
	}
	
	private static DefaultBundle defaultBundle = null;
	private static DefaultBundle defaultBundle() {
		if(defaultBundle == null){
			defaultBundle = (DefaultBundle)GWT.create(DefaultBundle.class);
		}
		return defaultBundle;
	}

	private final SimplePanel wrapper = new SimplePanel();
	private final Button button = new Button();
	private final Image loader = new Image();
	
	public LoaderButton() {
		this(ImageResources.INSTANCE.loader(), defaultBundle().defaultStyle());
	}
	
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
	
}
