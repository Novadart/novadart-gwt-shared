package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.novadart.gwtshared.client.validation.ValidationBundle;

public class ValidatedTextBox extends FocusableValidatedWidget<TextBox, String> 
implements HasText, HasBlurHandlers, HasFocusHandlers, HasKeyUpHandlers, Focusable {

	private final TextBox textBox = new TextBox();
	private boolean autoTrim = true;

	public ValidatedTextBox(Style style, ValidationBundle<String> validationBundle) {
		super(style, validationBundle);
		initWidget(textBox);
	}

	public ValidatedTextBox(Style style) {
		this(style, null);
	}

	public boolean isAutoTrim() {
		return autoTrim;
	}

	public void setAutoTrim(boolean autoTrim) {
		this.autoTrim = autoTrim;
	}
	
	public void setSelectionRange(int pos, int length){
		textBox.setSelectionRange(pos, length);
	}
	
	public void selectAll(){
		textBox.selectAll();
	}
	
	@Override
	protected TextBox getBaseWidget() {
		return textBox;
	}
	
	@Override
	protected void resetUI() {
		setText("");
	}

	@Override
	protected void updateUI(boolean isValid) {

	}

	@Override
	public String getValue() {
		return getText();
	}
	
	@Override
	public void setValue(String value) {
		setText(value);
	}

	@Override
	public String getText() {
		return autoTrim ? textBox.getText().trim() : textBox.getText();
	}

	@Override
	public void setText(String text) {
		textBox.setText(text==null ? "" : (autoTrim ? text.trim() : text));
	}

	public void setReadOnly(boolean readonly){
		textBox.setReadOnly(readonly);
	}

	public void setEnabled(boolean enabled){
		textBox.setEnabled(enabled);
	}
	
	@Override
	public boolean isEmpty() {
		return getText().isEmpty();
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return textBox.addFocusHandler(handler);
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return textBox.addBlurHandler(handler);
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return textBox.addKeyUpHandler(handler);
	}

	@Override
	public int getTabIndex() {
		return textBox.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		textBox.setAccessKey(key);
	}

	@Override
	public void setFocus(boolean focused) {
		textBox.setFocus(focused);
	}

	@Override
	public void setTabIndex(int index) {
		textBox.setTabIndex(index);
	}

	public void setMaxLength(int length) {
		textBox.setMaxLength(length);
	}
}
