package br.com.foursys.locadora.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

	public static String getDataAtual() {
		return new SimpleDateFormat(Constantes.FORMATO_DATA).format(new Date());
	}

	public static Date getDateAtual() {
		Calendar currentDate = Calendar.getInstance();
		return currentDate.getTime();
	}

	public static Date getMaxDate(int dias) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, dias);
		return currentDate.getTime();
	}
	
	public static String getDateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(Constantes.FORMATO_DATA);
		return format.format(date);
	}
	
	public static Date getStringToDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(Constantes.FORMATO_DATA);
		return new Date(format.parse(date).getTime());
		
	}

}
