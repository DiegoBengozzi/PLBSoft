package GUI;

import helper.StatusHelper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class JanelaPrincipalGUI {

	protected Shell shellPlbsoft;
	private ScrolledComposite scrolledComposite;
	@SuppressWarnings("unused")
	private Text txtStatus;
	private DateTime dateTime;

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellPlbsoft.open();
		shellPlbsoft.layout();
		while (!shellPlbsoft.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shellPlbsoft = new Shell();
		shellPlbsoft.setMinimumSize(new Point(600, 400));
		//shellPlbsoft = LayoutHelper.getShellAtivo();
	    //shellPlbsoft = ShellHelper.getShellAtivo();
		shellPlbsoft.setSize(453, 317);
		shellPlbsoft.setText("PLBSoft");
		shellPlbsoft.setLayout(new GridLayout(2, false));

		Menu menu = new Menu(shellPlbsoft, SWT.BAR);
		shellPlbsoft.setMenuBar(menu);

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
		
		MenuItem mntmTipoDeTanque = new MenuItem(menu_1, SWT.NONE);
		mntmTipoDeTanque.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TipoTanqueGUI tipoTanque = new TipoTanqueGUI(scrolledComposite,
						SWT.BORDER);
				scrolledComposite.setContent(tipoTanque);
				scrolledComposite.setMinSize(tipoTanque.computeSize(
						SWT.DEFAULT, SWT.DEFAULT));
			}
		});
		mntmTipoDeTanque.setText("Tipo de Tanque");

		MenuItem mntmRelatorios = new MenuItem(menu, SWT.CASCADE);
		mntmRelatorios.setText("Relatorios");

		Menu menu_2 = new Menu(mntmRelatorios);
		mntmRelatorios.setMenu(menu_2);
		
		MenuItem mntmRelatoriosNok = new MenuItem(menu_2, SWT.NONE);
		mntmRelatoriosNok.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StatusHelper.mensagemError("Relatórios não disponiveis");
			}
		});
		mntmRelatoriosNok.setText("Relatorios NOK");

		Composite compositeLateral = new Composite(shellPlbsoft, SWT.NONE);
		GridData gd_compositeLateral = new GridData(SWT.LEFT, SWT.FILL, false,
				true, 1, 1);
		gd_compositeLateral.heightHint = 167;
		compositeLateral.setLayoutData(gd_compositeLateral);
		compositeLateral.setLayout(new GridLayout(1, false));
		
		setDateTime(new DateTime(compositeLateral, SWT.BORDER | SWT.DROP_DOWN));

		Button btInicio = new Button(compositeLateral, SWT.NONE);
		btInicio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btInicio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btInicio.setText("Inicio");

		Button btTanque = new Button(compositeLateral, SWT.NONE);
		btTanque.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btTanque.setText("Tanques");

		Button btAgenda = new Button(compositeLateral, SWT.NONE);
		btAgenda.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StatusHelper.mensagemError("mensagem de erro");	
			}
		});
		btAgenda.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btAgenda.setText("Test");

		Button button_3 = new Button(compositeLateral, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StatusHelper.mensagemWarning("mensagem de alerta amarela");
			}
		});
		button_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		button_3.setText("Teste");

		Button button_4 = new Button(compositeLateral, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StatusHelper.mensagemInfo("mensagem informacao");
			}
		});
		button_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		button_4.setText("Teste2");

		scrolledComposite = new ScrolledComposite(shellPlbsoft, SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);

		Composite compositeStatus = new Composite(shellPlbsoft, SWT.NONE);
		compositeStatus.setLayout(new GridLayout(1, false));
		compositeStatus.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true,
				false, 2, 1));

		txtStatus = StatusHelper.getStatusAtivo(compositeStatus);

	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}
}
