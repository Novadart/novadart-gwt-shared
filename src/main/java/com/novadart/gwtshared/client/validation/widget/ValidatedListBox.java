package com.novadart.gwtshared.client.validation.widget;

import com.google.gwt.user.client.ui.ListBox;
import com.novadart.gwtshared.client.validation.ValidationBundle;

public class ValidatedListBox extends ValidatedWidget<ListBox, Integer> {
	
	private static class ValidatedListBoxBundle implements ValidationBundle<Integer>{
		private final String errorMessage;
		
		public ValidatedListBoxBundle(String errorMessage) {
			this.errorMessage = errorMessage==null ? "" : errorMessage;
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
	
	public ValidatedListBox(String errorMessage) {
		super(new ValidatedListBoxBundle(errorMessage));
		listBox.addItem("");
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
	
	public String getItemText(int index){
		return listBox.getItemText(index);
	}
	
	public String getSelectedItemText(){
		return listBox.getItemText(listBox.getSelectedIndex());
	}
	
	public void addItem(String item){
		listBox.addItem(item);
	}
	
	public void setEnabled(boolean enabled){
		listBox.setEnabled(enabled);
	}
}
