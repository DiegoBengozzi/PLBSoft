package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public abstract class TelaEdicaoGUI extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public abstract void excluir();

	public abstract void buscar();

	public abstract void salvar();

	public abstract void adicionarComponentes(Composite composite);

	public TelaEdicaoGUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		Composite compositePrincipal = new Composite(this, SWT.NONE);
		compositePrincipal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		Composite compositeSecundario = new Composite(this, SWT.NONE);
		compositeSecundario.setLayout(new GridLayout(1, false));
		compositeSecundario.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				false, false, 1, 1));
		
				Button btnBuscar = new Button(compositeSecundario, SWT.NONE);
				btnBuscar.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						buscar();
					}
				});
				btnBuscar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
						1, 1));
				btnBuscar.setText("Buscar");
		
				Button btnSalvar = new Button(compositeSecundario, SWT.NONE);
				btnSalvar.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						salvar();
					}
				});
				btnSalvar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
						1, 1));
				btnSalvar.setText("Salvar");
				
						Button btnExcluir = new Button(compositeSecundario, SWT.NONE);
						btnExcluir.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								excluir();
							}
						});
						btnExcluir.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1,
								1));
						btnExcluir.setText("Excluir");
		
				Button btnVoltar = new Button(compositeSecundario, SWT.NONE);
				btnVoltar.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						voltar();
					}
				});
				btnVoltar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
						1, 1));
				btnVoltar.setText("Voltar");

		adicionarComponentes(compositePrincipal);

	}

	public void voltar() {
		dispose();

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
