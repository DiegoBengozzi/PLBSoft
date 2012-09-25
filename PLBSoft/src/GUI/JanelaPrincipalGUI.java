package GUI;

import helper.LayoutHelper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class JanelaPrincipalGUI {

	protected Shell shlPlbsoft;
	private ScrolledComposite scrolledComposite;
	@SuppressWarnings("unused")
	private Text txtStatus;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			JanelaPrincipalGUI window = new JanelaPrincipalGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPlbsoft.open();
		shlPlbsoft.layout();
		while (!shlPlbsoft.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPlbsoft = new Shell();
		// shlPlbsoft = ShellHelper.getShellAtivo();
		shlPlbsoft.setSize(453, 317);
		shlPlbsoft.setText("PLBSoft");
		shlPlbsoft.setLayout(new GridLayout(2, false));

		Menu menu = new Menu(shlPlbsoft, SWT.BAR);
		shlPlbsoft.setMenuBar(menu);

		MenuItem mntmCadastros = new MenuItem(menu, SWT.CASCADE);
		mntmCadastros.setText("Cadastros");

		Menu menu_1 = new Menu(mntmCadastros);
		mntmCadastros.setMenu(menu_1);

		MenuItem mntmTanqueRede = new MenuItem(menu_1, SWT.NONE);
		mntmTanqueRede.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TanqueRedeGUI tanqueRede = new TanqueRedeGUI(scrolledComposite,
						SWT.BORDER);
				scrolledComposite.setContent(tanqueRede);
				scrolledComposite.setMinSize(tanqueRede.computeSize(
						SWT.DEFAULT, SWT.DEFAULT));
			}
		});
		mntmTanqueRede.setText("Tanque Rede");

		MenuItem mntmRelatorios = new MenuItem(menu, SWT.CASCADE);
		mntmRelatorios.setText("Relatorios");

		Menu menu_2 = new Menu(mntmRelatorios);
		mntmRelatorios.setMenu(menu_2);

		Composite compositeLateral = new Composite(shlPlbsoft, SWT.NONE);
		GridData gd_compositeLateral = new GridData(SWT.LEFT, SWT.FILL, false,
				true, 1, 1);
		gd_compositeLateral.heightHint = 167;
		compositeLateral.setLayoutData(gd_compositeLateral);
		compositeLateral.setLayout(new GridLayout(1, false));

		Button btInicio = new Button(compositeLateral, SWT.NONE);
		btInicio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btInicio.setText("Inicio");

		Button btTanque = new Button(compositeLateral, SWT.NONE);
		btTanque.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btTanque.setText("Tanques");

		Button btAgenda = new Button(compositeLateral, SWT.NONE);
		btAgenda.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btAgenda.setText("Agenda");

		Button button_3 = new Button(compositeLateral, SWT.NONE);
		button_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		button_3.setText("Teste");

		Button button_4 = new Button(compositeLateral, SWT.NONE);
		button_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		button_4.setText("Teste2");

		scrolledComposite = new ScrolledComposite(shlPlbsoft, SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);

		Composite compositeStatus = new Composite(shlPlbsoft, SWT.NONE);
		compositeStatus.setLayout(new GridLayout(1, false));
		compositeStatus.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true,
				false, 2, 1));

		txtStatus = LayoutHelper.getStatusAtivo(compositeStatus);

	}

}
