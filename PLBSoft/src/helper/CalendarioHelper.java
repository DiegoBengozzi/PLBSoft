package helper;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;

public class CalendarioHelper {

	private static DateTime calendarioSistema;

	public static Calendar c = Calendar.getInstance();
		
	public static Date retornaData() {
		if(c==null){
			c = Calendar.getInstance();
			c.set(getDateTime().getYear(), getDateTime().getMonth(), getDateTime().getDay());
		}
		return c.getTime();
	}

	public static DateTime getDateTime() {
		return calendarioSistema;
	}

	public static void setDateTime(DateTime dateTime) {
		calendarioSistema = dateTime;
		calendarioSistema.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
	}

}
