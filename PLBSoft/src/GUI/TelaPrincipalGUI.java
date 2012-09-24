package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.custom.ScrolledComposite;

public class TelaPrincipalGUI extends Composite {
	private Text txtBarrastatus;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TelaPrincipalGUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Composite compositeSuperior = new Composite(this, SWT.NONE);
		compositeSuperior.setLayout(new GridLayout(2, false));
		compositeSuperior.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		ToolBar toolBar = new ToolBar(compositeSuperior, SWT.FLAT | SWT.RIGHT);
		
		ToolItem tltmCadastros = new ToolItem(toolBar, SWT.NONE);
		tltmCadastros.setText("Cadastros");
		
		ToolItem tltmRelatorios = new ToolItem(toolBar, SWT.NONE);
		tltmRelatorios.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		tltmRelatorios.setText("Relatorios");
		
		DateTime dateTime = new DateTime(compositeSuperior, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Composite compositeLateral = new Composite(this, SWT.NONE);
		compositeLateral.setLayout(new GridLayout(1, false));
		compositeLateral.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		
		Button btnInicio = new Button(compositeLateral, SWT.NONE);
		btnInicio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnInicio.setBounds(0, 0, 75, 25);
		btnInicio.setText("Inicio");
		
		Button btnTanques = new Button(compositeLateral, SWT.NONE);
		btnTanques.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnTanques.setText("Tanques");
		
		Button btnAgenda = new Button(compositeLateral, SWT.NONE);
		btnAgenda.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnAgenda.setText("Agenda");
		
		Button btnTeste = new Button(compositeLateral, SWT.NONE);
		btnTeste.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnTeste.setText("Teste");
		
		Button btnTeste_1 = new Button(compositeLateral, SWT.NONE);
		btnTeste_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnTeste_1.setText("Teste2");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		
		Composite compositeInferior = new Composite(this, SWT.NONE);
		compositeInferior.setLayout(new GridLayout(1, false));
		compositeInferior.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 2, 1));
		
		txtBarrastatus = new Text(compositeInferior, SWT.BORDER);
		txtBarrastatus.setText("BarraStatus");
		txtBarrastatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
