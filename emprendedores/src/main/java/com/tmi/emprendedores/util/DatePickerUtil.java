package com.tmi.emprendedores.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DatePickerUtil {

	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
	
	public static DateFormat getDateformater() {
		return new SimpleDateFormat(DATE_FORMAT);
	}
}
