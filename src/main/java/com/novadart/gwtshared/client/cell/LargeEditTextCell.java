package com.novadart.gwtshared.client.cell;

import static com.google.gwt.dom.client.BrowserEvents.BLUR;
import static com.google.gwt.dom.client.BrowserEvents.CLICK;
import static com.google.gwt.dom.client.BrowserEvents.KEYDOWN;
import static com.google.gwt.dom.client.BrowserEvents.KEYUP;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.text.shared.SafeHtmlRenderer;

/**
 * Code derived from EditTextCell
 * @author "Giordano Battilana"
 *
 */
public class LargeEditTextCell extends AbstractEditableCell<String, LargeEditTextCell.ViewData> {
	interface Template extends SafeHtmlTemplates {
		@Template("<textarea tabindex='-1' rows='5' style='width:100%;'>{0}</textarea>")
		SafeHtml textarea(String value);
	}

	/**
	 * The view data object used by this cell. We need to store both the text and
	 * the state because this cell is rendered differently in edit mode. If we did
	 * not store the edit state, refreshing the cell with view data would always
	 * put us in to edit state, rendering a text box instead of the new text
	 * string.
	 */
	static class ViewData {

		private boolean isEditing;

		/**
		 * If true, this is not the first edit.
		 */
		private boolean isEditingAgain;

		/**
		 * Keep track of the original value at the start of the edit, which might be
		 * the edited value from the previous edit and NOT the actual value.
		 */
		private String original;

		private String text;

		/**
		 * Construct a new ViewData in editing mode.
		 *
		 * @param text the text to edit
		 */
		public ViewData(String text) {
			this.original = text;
			this.text = text;
			this.isEditing = true;
			this.isEditingAgain = false;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			ViewData vd = (ViewData) o;
			return equalsOrBothNull(original, vd.original)
					&& equalsOrBothNull(text, vd.text) && isEditing == vd.isEditing
					&& isEditingAgain == vd.isEditingAgain;
		}

		public String getOriginal() {
			return original;
		}

		public String getText() {
			return text;
		}

		@Override
		public int hashCode() {
			return original.hashCode() + text.hashCode()
					+ Boolean.valueOf(isEditing).hashCode() * 29
					+ Boolean.valueOf(isEditingAgain).hashCode();
		}

		public boolean isEditing() {
			return isEditing;
		}

		public boolean isEditingAgain() {
			return isEditingAgain;
		}

		public void setEditing(boolean isEditing) {
			boolean wasEditing = this.isEditing;
			this.isEditing = isEditing;

			// This is a subsequent edit, so start from where we left off.
			if (!wasEditing && isEditing) {
				isEditingAgain = true;
				original = text;
			}
		}

		public void setText(String text) {
			this.text = text;
		}

		private boolean equalsOrBothNull(Object o1, Object o2) {
			return (o1 == null) ? o2 == null : o1.equals(o2);
		}
	}

	/**
	 * This renderer substitutes new lines with <br>
	 * @author "Giordano Battilana"
	 *
	 */
	static class TextAreaSafeHtmlRenderer extends AbstractSafeHtmlRenderer<String> {
		
		private static TextAreaSafeHtmlRenderer instance = null;
		
		public static TextAreaSafeHtmlRenderer getInstance(){
			if(instance == null) {
				instance = new TextAreaSafeHtmlRenderer();
			}
			return instance;
		}
		
		private TextAreaSafeHtmlRenderer() {}

		@Override
		public SafeHtml render(String object) {
			SafeHtmlBuilder shb = new SafeHtmlBuilder();
			
			String[] strings = object.split("\n");
			for (int i=0; i<strings.length-1; i++) {
				shb.appendEscaped(strings[i]);
				shb.appendHtmlConstant("<br>");
			}
			shb.appendEscaped(strings[strings.length-1]);
			
			return shb.toSafeHtml();
		}
	}
	
	
	
	private static Template template;

	private final SafeHtmlRenderer<String> renderer;

	
	public LargeEditTextCell() {
		super(CLICK, KEYUP, KEYDOWN, BLUR);
		if (template == null) {
			template = GWT.create(Template.class);
		}
		this.renderer = TextAreaSafeHtmlRenderer.getInstance();
	}

	@Override
	public boolean isEditing(Context context, Element parent, String value) {
		ViewData viewData = getViewData(context.getKey());
		return viewData == null ? false : viewData.isEditing();
	}

	@Override
	public void onBrowserEvent(Context context, Element parent, String value,
			NativeEvent event, ValueUpdater<String> valueUpdater) {
		Object key = context.getKey();
		ViewData viewData = getViewData(key);
		if (viewData != null && viewData.isEditing()) {
			// Handle the edit event.
			editEvent(context, parent, value, viewData, event, valueUpdater);
		} else {
			String type = event.getType();
			int keyCode = event.getKeyCode();
			boolean enterPressed = KEYUP.equals(type)
					&& keyCode == KeyCodes.KEY_ENTER;
			if (CLICK.equals(type) || enterPressed) {
				// Go into edit mode.
				if (viewData == null) {
					viewData = new ViewData(value);
					setViewData(key, viewData);
				} else {
					viewData.setEditing(true);
				}
				edit(context, parent, value);
			}
		}
	}

	@Override
	public void render(Context context, String value, SafeHtmlBuilder sb) {
		// Get the view data.
		Object key = context.getKey();
		ViewData viewData = getViewData(key);
		if (viewData != null && !viewData.isEditing() && value != null
				&& value.equals(viewData.getText())) {
			clearViewData(key);
			viewData = null;
		}

		String toRender = value;
		if (viewData != null) {
			String text = viewData.getText();
			if (viewData.isEditing()) {
				/*
				 * Do not use the renderer in edit mode because the value of a text
				 * textarea element is always treated as text. SafeHtml isn't valid in the
				 * context of the value attribute.
				 */
				sb.append(template.textarea(text));
				return;
			} else {
				// The user pressed enter, but view data still exists.
				toRender = text;
			}
		}

		if (toRender != null && toRender.trim().length() > 0) {
			sb.append(renderer.render(toRender));
		} else {
			/*
			 * Render a blank space to force the rendered element to have a height.
			 * Otherwise it is not clickable.
			 */
			sb.appendHtmlConstant("\u00A0");
		}
	}

	@Override
	public boolean resetFocus(Context context, Element parent, String value) {
		if (isEditing(context, parent, value)) {
			getTextAreaElement(parent).focus();
			return true;
		}
		return false;
	}

	/**
	 * Convert the cell to edit mode.
	 *
	 * @param context the {@link Context} of the cell
	 * @param parent the parent element
	 * @param value the current value
	 */
	protected void edit(Context context, Element parent, String value) {
		setValue(context, parent, value);
		TextAreaElement textArea = getTextAreaElement(parent);
		textArea.focus();
		textArea.select();
	}

	/**
	 * Convert the cell to non-edit mode.
	 * 
	 * @param context the context of the cell
	 * @param parent the parent Element
	 * @param value the value associated with the cell
	 */
	private void cancel(Context context, Element parent, String value) {
		cleartextarea(getTextAreaElement(parent));
		setValue(context, parent, value);
	}

	/**
	 * Clear selected from the textarea element. Both Firefox and IE fire spurious
	 * onblur events after the textarea is removed from the DOM if selection is not
	 * cleared.
	 *
	 * @param textarea the textarea element
	 */
	private native void cleartextarea(Element textarea) /*-{
		    if (textarea.selectionEnd)
		      textarea.selectionEnd = textarea.selectionStart;
		    else if ($doc.selection)
		      $doc.selection.clear();
		  }-*/;

	/**
	 * Commit the current value.
	 * 
	 * @param context the context of the cell
	 * @param parent the parent Element
	 * @param viewData the {@link ViewData} object
	 * @param valueUpdater the {@link ValueUpdater}
	 */
	private void commit(Context context, Element parent, ViewData viewData,
			ValueUpdater<String> valueUpdater) {
		String value = updateViewData(parent, viewData, false);
		cleartextarea(getTextAreaElement(parent));
		setValue(context, parent, viewData.getOriginal());
		if (valueUpdater != null) {
			valueUpdater.update(value);
		}
	}

	private void editEvent(Context context, Element parent, String value,
			ViewData viewData, NativeEvent event, ValueUpdater<String> valueUpdater) {
		String type = event.getType();
		boolean keyUp = KEYUP.equals(type);
		boolean keyDown = KEYDOWN.equals(type);
		if (keyUp || keyDown) {
			int keyCode = event.getKeyCode();
			
//			Here we disable KEY_ENTER, to allow entering multiple lines
			
//			if (keyUp && keyCode == KeyCodes.KEY_ENTER) {
//				// Commit the change.
//				commit(context, parent, viewData, valueUpdater);
//			} else if (keyUp && keyCode == KeyCodes.KEY_ESCAPE) {
			if (keyUp && keyCode == KeyCodes.KEY_ESCAPE) {
				// Cancel edit mode.
				String originalText = viewData.getOriginal();
				if (viewData.isEditingAgain()) {
					viewData.setText(originalText);
					viewData.setEditing(false);
				} else {
					setViewData(context.getKey(), null);
				}
				cancel(context, parent, value);
			} else {
				// Update the text in the view data on each key.
				updateViewData(parent, viewData, true);
			}
		} else if (BLUR.equals(type)) {
			// Commit the change. Ensure that we are blurring the textarea element and
			// not the parent element itself.
			EventTarget eventTarget = event.getEventTarget();
			if (Element.is(eventTarget)) {
				Element target = Element.as(eventTarget);
				if ("textarea".equals(target.getTagName().toLowerCase())) {
					commit(context, parent, viewData, valueUpdater);
				}
			}
		}
	}

	/**
	 * Get the textarea element in edit mode.
	 */
	private TextAreaElement getTextAreaElement(Element parent) {
		return parent.getFirstChild().<TextAreaElement> cast();
	}

	/**
	 * Update the view data based on the current value.
	 *
	 * @param parent the parent element
	 * @param viewData the {@link ViewData} object to update
	 * @param isEditing true if in edit mode
	 * @return the new value
	 */
	private String updateViewData(Element parent, ViewData viewData,
			boolean isEditing) {
		TextAreaElement textarea = (TextAreaElement) parent.getFirstChild();
		String value = textarea.getValue();
		viewData.setText(value);
		viewData.setEditing(isEditing);
		return value;
	}
}
