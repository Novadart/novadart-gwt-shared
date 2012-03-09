package com.novadart.gwtshared.client.textbox;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.novadart.gwtshared.client.textbox.resources.TextBoxResources;

public class UpdateLabel extends Composite {
	
	public interface Handler {
		void onNewValue(String value);
	}
	
	private final SimplePanel container = new SimplePanel();
	
	private final Label label = new Label();
	private final TextBox textBox = new TextBox();
	private String value = "";
	
	
	public UpdateLabel(Handler handler) {
		this("", handler);
	}
	
	public UpdateLabel(String value, final Handler handler) {
		this.value = value==null ? "" : value;
		label.setText(value);
		label.setWidth("100%");
		label.setHeight("100%");
		textBox.setWidth("100%");
		textBox.setHeight("100%");
		
		container.setWidget(label);
		initWidget(container);
		setStyleName(TextBoxResources.get.style().updateLabel());
		label.setStyleName(TextBoxResources.get.style().updateLabelLabel());
		textBox.setStyleName(TextBoxResources.get.style().updateLabelTextbox());
		
		label.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				textBox.setText(label.getText());
				container.setWidget(textBox);
			}
		});
		
		textBox.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				String newValue = textBox.getText();
				label.setText(newValue);
				container.setWidget(label);
				
				if(!newValue.equals(UpdateLabel.this.value)){
					UpdateLabel.this.value = newValue;
					
					if(handler != null){
						handler.onNewValue(newValue);
					}
				}
			}
		});
		
	}

}
