package com.novadart.gwtshared.client.validation;

import com.google.gwt.regexp.shared.RegExp;

public class Validate {
	
	
	public static boolean isInteger(String value){
		try{
			Integer.parseInt(value);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	public static boolean isFloat(String value){
		try{
			Float.parseFloat(value);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	
	public static boolean isDouble(String value){
		try{
			Double.parseDouble(value);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	public static boolean isEmpty(String value){
		return value==null || value.isEmpty();
	}	
	
	private static final RegExp EMAIL_REGEXp =
			RegExp.compile("[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})");
	public static boolean isEmail(String value){
		return EMAIL_REGEXp.exec(value) != null;
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
