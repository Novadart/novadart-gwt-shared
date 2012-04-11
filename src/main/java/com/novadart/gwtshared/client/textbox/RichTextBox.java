package com.novadart.gwtshared.client.textbox;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.uibinder.client.UiConstructor;
import com.novadart.gwtshared.client.validation.widget.ValidatedTextBox;

public class RichTextBox extends ValidatedTextBox {
	
	private final String label;
	
	public @UiConstructor RichTextBox(String label) {
		this.label = label != null ? label : "";
		
		addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				if(getText().equalsIgnoreCase("")){
					setText(RichTextBox.this.label);
				}
				addStyleName("empty");
			}
		});
		
		addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				if(getText().equalsIgnoreCase(RichTextBox.this.label)){
					setText("");
				}
				removeStyleName("empty");
			}
		});
		
		setText(label);
		addStyleName("empty");
	}

}
