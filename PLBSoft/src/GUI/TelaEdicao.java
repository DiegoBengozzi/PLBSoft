package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class TelaEdicao extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TelaEdicao(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Composite compositePrincipal = new Composite(this, SWT.NONE);
		compositePrincipal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite compositeSecundario = new Composite(this, SWT.NONE);
		compositeSecundario.setLayout(new GridLayout(1, false));
		compositeSecundario.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Button btnSalvar = new Button(compositeSecundario, SWT.NONE);
		btnSalvar.setText("Salvar");
		
		Button btnBuscar = new Button(compositeSecundario, SWT.NONE);
		btnBuscar.setText("Buscar");
		
		Button btnVoltar = new Button(compositeSecundario, SWT.NONE);
		btnVoltar.setText("Voltar");
		
		Button btnNovo = new Button(compositeSecundario, SWT.NONE);
		btnNovo.setText("Novo");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
