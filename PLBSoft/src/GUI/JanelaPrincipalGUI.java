package GUI;

import static helper.StatusHelper.mensagemInfo;
import static helper.StatusHelper.mensagemWarning;
import static helper.StatusHelper.txtStatus;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class JanelaPrincipalGUI {

	protected Shell shellPlbsoft;
	private ScrolledComposite scrolledComposite;
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

	@SuppressWarnings("rawtypes")
	public void carregarValores(TelaEdicaoGUI tela) {
		scrolledComposite.setContent(tela);
		scrolledComposite.setMinSize(tela.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		tela.carregar();
	}
	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shellPlbsoft = new Shell();
		shellPlbsoft.setMinimumSize(new Point(800, 600));
		shellPlbsoft.setImage(SWTResourceManager.getImage(
				JanelaPrincipalGUI.class, "/Icone/LOGO_bluefish_pequena.png"));
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
				carregarValores(tanqueRede);
			}
		});
		mntmTanqueRede.setText("Tanque Rede");

		MenuItem mntmTipoDeTanque = new MenuItem(menu_1, SWT.NONE);
		mntmTipoDeTanque.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TipoTanqueGUI tipoTanque = new TipoTanqueGUI(scrolledComposite,
						SWT.BORDER);
				carregarValores(tipoTanque);
			}
		});
		mntmTipoDeTanque.setText("Tipo de Tanque");

		MenuItem mntmCadastroDeTanque = new MenuItem(menu_1, SWT.NONE);
		mntmCadastroDeTanque.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TanqueGUI tanque = new TanqueGUI(scrolledComposite, SWT.BORDER);
				carregarValores(tanque);
			}
		});
		mntmCadastroDeTanque.setText("Tanque");
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
		compositeLateral.setLayout(new GridLayout(1, false));
		GridData gd_compositeLateral = new GridData(SWT.LEFT, SWT.FILL, false,
				true, 1, 1);
		gd_compositeLateral.heightHint = 167;
		compositeLateral.setLayoutData(gd_compositeLateral);

		Group groupMenuRapido = new Group(compositeLateral, SWT.NONE);
		groupMenuRapido.setText("Menu R\u00E1pido");
		groupMenuRapido.setLayout(new GridLayout(1, false));
		groupMenuRapido.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true,
				true, 1, 1));

		setDateTime(new DateTime(groupMenuRapido, SWT.BORDER | SWT.DROP_DOWN));

		Button btAgenda = new Button(groupMenuRapido, SWT.NONE);
		btAgenda.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		btAgenda.setSize(95, 25);
		btAgenda.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mensagemInfo("Agenda nao implementada!!");
			}
		});
		btAgenda.setText("Test");

		Button button_3 = new Button(groupMenuRapido, SWT.NONE);
		button_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		button_3.setSize(95, 25);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mensagemWarning("mensagem de alerta amarela");
			}
		});
		button_3.setText("Teste");

		Button button_4 = new Button(groupMenuRapido, SWT.NONE);
		button_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		button_4.setSize(95, 25);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StatusHelper.mensagemInfo("mensagem informacao");
			}
		});
		button_4.setText("Teste2");

		scrolledComposite = new ScrolledComposite(shellPlbsoft, SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);

		Group grpBarraDeStatus = new Group(shellPlbsoft, SWT.NONE);
		GridData gd_grpBarraDeStatus = new GridData(SWT.FILL, SWT.FILL, true,
				false, 2, 1);
		gd_grpBarraDeStatus.widthHint = 811;
		grpBarraDeStatus.setLayoutData(gd_grpBarraDeStatus);
		grpBarraDeStatus.setText("Barra de Status");
		grpBarraDeStatus.setLayout(new GridLayout(2, false));
		new Label(grpBarraDeStatus, SWT.NONE);

		Composite compositeStatus = new Composite(grpBarraDeStatus, SWT.NONE);
		compositeStatus.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));
		compositeStatus.setLayout(new GridLayout(1, false));
		txtStatus = StatusHelper.getStatusAtivo(compositeStatus);
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		dateTime.setSize(95, 24);
	}

}
