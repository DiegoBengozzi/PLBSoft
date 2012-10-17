package GUI;

import static helper.StatusHelper.mensagemError;
import static helper.StatusHelper.mensagemInfo;
import static helper.StatusHelper.mensagemLimpar;
import static helper.StatusHelper.mensagemWarning;
import static helper.StatusHelper.txtStatus;
import helper.CalendarioHelper;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class JanelaPrincipalGUI {

	protected Shell shellPlbsoft;
	private ScrolledComposite scrolledComposite;

	// private static DateTime calendarioSistema;

	/**
	 * Open the window. Janela Principal do Sistema.
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
	 * É utilizado para carregar os componentes nos Eventos dos Botoes.
	 * 
	 * @param tela
	 */
	@SuppressWarnings("rawtypes")
	public void carregarValores(TelaEdicaoGUI tela) {
		scrolledComposite.setContent(tela);
		scrolledComposite
				.setMinSize(tela.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		tela.carregar();
	}

	// public static DateTime getDateTime() {
	// return calendarioSistema;
	// }
	//
	// public static void setDateTime(DateTime dateTime) {
	// calendarioSistema = dateTime;
	// calendarioSistema.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
	// true, 1, 1));
	// dateTime.setSize(95, 24);
	// }

	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shellPlbsoft = new Shell();
		shellPlbsoft.setMinimumSize(new Point(850, 650));
		shellPlbsoft.setMaximized(true);
		shellPlbsoft.setImage(SWTResourceManager.getImage(
				JanelaPrincipalGUI.class, "/Icone/Logo3-32x32.png"));
		shellPlbsoft.setText("PLBSoft");
		shellPlbsoft.setLayout(new GridLayout(2, false));

		/**
		 * Inicio da Barra de Munus Superior
		 */
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

		MenuItem mntmAdubao = new MenuItem(menu_1, SWT.NONE);
		mntmAdubao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AdubacaoGUI adubacao = new AdubacaoGUI(scrolledComposite,
						SWT.BORDER);
				carregarValores(adubacao);
			}
		});
		mntmAdubao.setText("Aduba\u00E7\u00E3o");

		MenuItem mntmEspcie = new MenuItem(menu_1, SWT.NONE);
		mntmEspcie.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EspecieGUI especie = new EspecieGUI(scrolledComposite,
						SWT.BORDER);
				carregarValores(especie);
			}
		});
		mntmEspcie.setText("Esp\u00E9cie");

		MenuItem mntmPassarela = new MenuItem(menu_1, SWT.NONE);
		mntmPassarela.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PassarelaGUI passarela = new PassarelaGUI(scrolledComposite,
						SWT.BORDER);
				carregarValores(passarela);
			}
		});
		mntmPassarela.setText("Passarela");

		MenuItem mntmRelatorios = new MenuItem(menu, SWT.CASCADE);
		mntmRelatorios.setText("Relatorios");

		Menu menu_2 = new Menu(mntmRelatorios);
		mntmRelatorios.setMenu(menu_2);

		MenuItem mntmRelatoriosNok = new MenuItem(menu_2, SWT.NONE);
		mntmRelatoriosNok.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StatusHelper.mensagemError("Relatórios não disponiveis");
				// CalendarioHelper.getCalendario();
			}
		});
		mntmRelatoriosNok.setText("Relatorios NOK");
		/**
		 * Fim da barra de munu superior e inicio da barra de menus lateral
		 */
		Composite compositeLateral = new Composite(shellPlbsoft, SWT.NONE);
		compositeLateral.setLayout(new GridLayout(1, false));
		GridData gd_compositeLateral = new GridData(SWT.LEFT, SWT.FILL, false,
				true, 1, 1);
		gd_compositeLateral.heightHint = 167;
		compositeLateral.setLayoutData(gd_compositeLateral);

		Group grpCalendrio = new Group(compositeLateral, SWT.NONE);
		grpCalendrio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));
		grpCalendrio.setText("Calend\u00E1rio");
		grpCalendrio.setLayout(new GridLayout(1, false));

		CalendarioHelper.setDateTime(new DateTime(grpCalendrio, SWT.BORDER | SWT.DROP_DOWN));
		//CalendarioHelper.c.set(CalendarioHelper.getDateTime().getYear(), CalendarioHelper.getDateTime().getMonth(), CalendarioHelper.getDateTime().getDay());
		// CalendarioHelper.setInicioCalendario(new DateTime(grpCalendrio,
		// SWT.BORDER
		// | SWT.DROP_DOWN));

		Group groupMenuRapido = new Group(compositeLateral, SWT.NONE);
		groupMenuRapido.setText("Menu R\u00E1pido");
		groupMenuRapido.setLayout(new GridLayout(1, false));
		GridData gd_groupMenuRapido = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1);
		gd_groupMenuRapido.heightHint = 549;
		groupMenuRapido.setLayoutData(gd_groupMenuRapido);

		Button btTest = new Button(groupMenuRapido, SWT.NONE);
		btTest.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		btTest.setSize(95, 25);
		btTest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				mensagemInfo(""+CalendarioHelper.c.getTime());
			}
		});
		btTest.setText("Test");

		Button btTeste = new Button(groupMenuRapido, SWT.NONE);
		btTeste.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		btTeste.setSize(95, 25);
		btTeste.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mensagemWarning("Botao de Teste!! Mensagem de Alerta!! >>");
			}
		});
		btTeste.setText("Teste");

		Button btTeste2 = new Button(groupMenuRapido, SWT.NONE);
		btTeste2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		btTeste2.setSize(95, 25);
		btTeste2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mensagemError("Botao de Teste!! Mensagem de Erro!!");
			}
		});
		btTeste2.setText("Teste2");

		Button btnLimpar = new Button(groupMenuRapido, SWT.NONE);
		btnLimpar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mensagemLimpar();
			}
		});
		btnLimpar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));
		btnLimpar.setText("Limpar");

		scrolledComposite = new ScrolledComposite(shellPlbsoft, SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		/**
		 * Fim da barra de menu lateral e inicio da Barra de Menu Inferior
		 */
		Group grpBarraDeStatus = new Group(shellPlbsoft, SWT.NONE);
		GridData gd_grpBarraDeStatus = new GridData(SWT.FILL, SWT.FILL, true,
				false, 2, 1);
		gd_grpBarraDeStatus.heightHint = 44;
		gd_grpBarraDeStatus.widthHint = 811;
		grpBarraDeStatus.setLayoutData(gd_grpBarraDeStatus);
		grpBarraDeStatus.setText("Barra de Status");
		grpBarraDeStatus.setLayout(new GridLayout(2, false));

		Composite compositeStatus = new Composite(grpBarraDeStatus, SWT.BORDER);
		GridData gd_compositeStatus = new GridData(SWT.FILL, SWT.FILL, true,
				false, 2, 1);
		gd_compositeStatus.heightHint = 25;
		gd_compositeStatus.widthHint = 737;
		compositeStatus.setLayoutData(gd_compositeStatus);
		compositeStatus.setLayout(new GridLayout(1, false));
		txtStatus = StatusHelper.getStatusAtivo(compositeStatus);
	}
}
