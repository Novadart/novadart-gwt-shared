package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ListBox;
import com.novadart.gwtshared.client.validation.ValidationBundle;

public class ValidatedListBox extends ValidatedWidget<ListBox, Integer> implements HasChangeHandlers {
	
	private static class ValidatedListBoxBundle implements ValidationBundle<Integer>{
		private final String errorMessage;
		
		public ValidatedListBoxBundle(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		@Override
		public boolean isValid(Integer value) {
			return value != null && value != 0;
		}

		@Override
		public String getErrorMessage() {
			return this.errorMessage;
		}
		
	}

	private final ListBox listBox = new ListBox();
	
	public ValidatedListBox() {
		this("", null);
	}
	
	public ValidatedListBox(String errorMessage) {
		this("", errorMessage);
	}
	
	public ValidatedListBox(String defaultItem, String errorMessage) {
		super(new ValidatedListBoxBundle(errorMessage));
		listBox.addItem(defaultItem);
		initWidget(listBox);
		addStyleName("ValidatedListBox");
	}
	
	@Override
	protected void updateUI(boolean isValid) {
		
	}

	@Override
	protected void resetUI() {
		listBox.setSelectedIndex(0);
	}
	
	@Override
	protected Integer getValue() {
		return getSelectedIndex();
	}

	public int getSelectedIndex(){
		return listBox.getSelectedIndex();
	}
	
	public void setSelectedIndex(int index){
		listBox.setSelectedIndex(index);
	}
	
	public void setSelectedItem(String item){
		int selectedIndex = 0;
		if(item != null) {
			for (int i=0; i<listBox.getItemCount(); i++) {
				if(item.equalsIgnoreCase(listBox.getItemText(i))){
					selectedIndex = i;
					break;
				}
			}
		}
		listBox.setSelectedIndex(selectedIndex);
	}
	
	public void setSelectedItemByValue(String value){
		int selectedIndex = 0;
		if(value != null) {
			for (int i=0; i<listBox.getItemCount(); i++) {
				if(value.equalsIgnoreCase(listBox.getValue(i))){
					selectedIndex = i;
					break;
				}
			}
		}
		listBox.setSelectedIndex(selectedIndex);
	}
	
	public String getItemText(int index){
		return listBox.getItemText(index);
	}
	
	public String getSelectedItemText(){
		return listBox.getItemText(listBox.getSelectedIndex());
	}
	
	public String getSelectedItemValue(){
		return listBox.getValue(listBox.getSelectedIndex());
	}
	
	public void addItem(String item){
		listBox.addItem(item);
	}
	
	public void addItem(String item, String value){
		listBox.addItem(item, value);
	}
	
	public void setEnabled(boolean enabled){
		listBox.setEnabled(enabled);
	}
	
	@Override
	public boolean isEmpty() {
		return getSelectedIndex() == 0;
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return listBox.addChangeHandler(handler);
	}
}
