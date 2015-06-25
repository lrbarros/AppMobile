package com.Sos_Salgados.Util;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
	
public class HoraData {
	private String dataFormatada;
	public HoraData(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"+" \t----\t\t"+"dd/MM/yyyy");
		Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
		dataFormatada = sdf.format(hora);
	}
	@Override
	public String toString() {
		return dataFormatada;
	}
	
}
