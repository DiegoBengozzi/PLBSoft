package GUI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class TanqueRedeGUI extends Composite {
	private Text tvNome;
	private Text tvTamanho;
	private Text tvStatus;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TanqueRedeGUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Label lblNome = new Label(this, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome:");
		
		tvNome = new Text(this, SWT.BORDER);
		tvNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblTamanhoM = new Label(this, SWT.NONE);
		lblTamanhoM.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTamanhoM.setText("Tamanho m\u00B2:");
		
		tvTamanho = new Text(this, SWT.BORDER);
		tvTamanho.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblStatus = new Label(this, SWT.NONE);
		lblStatus.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStatus.setText("Status:");
		
		tvStatus = new Text(this, SWT.BORDER);
		tvStatus.setEditable(false);
		tvStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
