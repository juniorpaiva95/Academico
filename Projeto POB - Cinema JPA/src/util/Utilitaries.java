package util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Utilitaries {
	private static SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat formatDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static String formatarHora(Date hora) {
		return formatHora.format(hora);
	}
	public static String formatarDataEHora(Date dateTime){
		return formatDataHora.format(dateTime);
	}
	public static String formatarData(Date data) {
		return formatData.format(data);
	}
}
