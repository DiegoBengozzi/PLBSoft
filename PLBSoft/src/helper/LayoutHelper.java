package helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LayoutHelper {

	public static Text txtStatus;
	public static Shell shell;

	public static Text getStatusAtivo(Composite compositeStatus) {
		if (txtStatus == null) {
			Text textStatus = new Text(compositeStatus, SWT.BORDER);
			textStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
					false, 1, 1));
			textStatus.setEditable(false);
			textStatus.setMessage("Barra de Status");
			textStatus.setText("BArra de Status de teste");

		}
		return txtStatus;

	}

	public static Shell getShellAtivo() {
		if (shell != null) {
			shell = new Shell();
		}
		return shell;
	}

}
