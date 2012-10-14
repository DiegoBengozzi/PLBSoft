package helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class StatusHelper {
	public static Text txtStatus;
	
	public static Text getStatusAtivo(Composite compositeStatus) {
		if (txtStatus == null) {
			txtStatus = new Text(compositeStatus, SWT.BORDER);
			txtStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			txtStatus.setEditable(false);
			txtStatus.setText("Barra de Status!");
			//txtStatus.setMessage("Barra de Status");
		}
		return txtStatus;

	}
	
	public static void mensagemInfo(String message){
		txtStatus.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		txtStatus.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtStatus.setText(message);
	}
	public static void mensagemWarning(String message){
		txtStatus.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtStatus.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		txtStatus.setText(message);
	}
	public static void mensagemError(String message){
		txtStatus.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		txtStatus.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtStatus.setText(message);
	}
	public static void mensagemLimpar(){
		txtStatus.setBackground(null);
		txtStatus.setForeground(null);
		txtStatus.setText("Barra de Status!");
	}

}
