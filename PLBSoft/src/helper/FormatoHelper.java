package helper;

import java.text.DecimalFormat;
import java.util.Locale;

public class FormatoHelper {

	public static Locale BRASIL = new Locale("pt","BR");
	
	private static DecimalFormat decimalFormato = (DecimalFormat) DecimalFormat.getInstance(BRASIL);
	
	public static DecimalFormat getDecimalFormato() {
		decimalFormato.setMinimumFractionDigits(2);
		return decimalFormato;
	}

}
