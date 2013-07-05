package com.novadart.gwtshared.client.validation;

import com.google.gwt.regexp.shared.RegExp;

public class Validation {

	private static final RegExp POSITIVE_NUMBER_REGEXP = 
			RegExp.compile("^\\d+$");

	public static boolean isPositiveNumber(String value){
		if(value == null){
			return false;
		}
		return POSITIVE_NUMBER_REGEXP.exec(value) != null;
	}

	public static boolean isInteger(String value){
		if(value == null){
			return false;
		}
		try{
			Integer.parseInt(value);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}

	public static boolean isFloat(String value){
		if(value == null){
			return false;
		}
		try{
			Float.parseFloat(value);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}


	public static boolean isDouble(String value){
		if(value == null){
			return false;
		}
		try{
			Double.parseDouble(value);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	public static boolean isLongerThanSize(String string, int size){
		return string.length() > size;
	}

	public static boolean isWithinSize(String string, int size){
		return string.length() <= size;
	}
	
	public static boolean isEmpty(String value){
		return value==null || value.isEmpty();
	}	

	//	private static final RegExp EMAIL_REGEXP =
	//			RegExp.compile("[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})");

	// see http://www.w3schools.com/js/js_form_validation.asp
	public static boolean isEmail(String value){
		if(value == null){
			return false;
		}
		int atpos = value.indexOf('@');
		int dotpos = value.lastIndexOf('.');

		return !(atpos<1 || dotpos<atpos+2 || dotpos+2>=value.length());
	}


	private static final RegExp VATID_REGEXP = 
			RegExp.compile("^\\w{2}[0-9]{11}$");
	public static boolean isVatId(String value){
		return VATID_REGEXP.exec(value) != null;
	}


	private static final RegExp SSN_REGEXP = 
			RegExp.compile("^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$");
	public static boolean isSSN(String value){
		return SSN_REGEXP.exec(value) != null;
	}




}
