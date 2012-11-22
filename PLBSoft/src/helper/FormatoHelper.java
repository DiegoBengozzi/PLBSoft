package helper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormatoHelper {

	public static Locale BRASIL = new Locale("pt","BR");
	
	private static DecimalFormat decimalFormato = (DecimalFormat) DecimalFormat.getInstance(BRASIL);
	
	public static DecimalFormat getDecimalFormato() {
		decimalFormato.setMinimumFractionDigits(2);
		return decimalFormato;
	}
	
	public static SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");

}
