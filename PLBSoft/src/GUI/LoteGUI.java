package GUI;

import helper.CalendarioHelper;
import helper.FormatoHelper;

import java.math.BigDecimal;
import java.util.List;

import modelo.Especie;
import modelo.Hapa;
import modelo.Lote;
import modelo.Passarela;
import modelo.Safra;
import modelo.Tanque;
import modelo.TanqueRede;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.EspecieService;
import service.HapaService;
import service.LoteService;
import service.PassarelaService;
import service.SafraService;
import service.TanqueRedeService;
import service.TanqueService;
import filtro.LoteFiltro;

public class LoteGUI extends TelaEdicaoGUI<Lote> {
	private Text tDataInicio;
	private Text tDataFim;
	private Text tNome;
	private Text tDescricao;
	private Text tQuantidade;
	private Table tableLote;
	private Text tFiltro;
	private ComboViewer cvSafra, cvEspecie;
	private Combo comboEspecie, comboSafra;
	private TableViewer tvLote, tvOrigemLote;
	private TableViewerColumn tvcFiltroId, tvcFiltroSafra, tvcFiltroNome,
			tvcFiltroInicio, tvcFiltroFinal, tvcFiltroQuantidade,
			tvcFiltroEspecie, tvcFiltroDescricao, tvcOrigemId, tvcOrigemSafra,
			tvcOrigemNome, tvcOrigemInicio, tvcOrigemFinal,
			tvcOrigemQuantidade, tvcOrigemEspecie, tvcOrigemDescricao;
	private LoteService loteService;
	private SafraService safraService;
	private EspecieService especieService;
	private TanqueService tanqueService;
	private HapaService hapaService;
	private PassarelaService passarelaService;
	private LoteFiltro filtro;
	private IStructuredSelection valorComboSafra, valorComboEspecie,
			valorComboTanque, valorComboTanqueRede, valorComboHapa;
	private Table tableOrigemLote;
	private Button btnAdd;
	private Combo comboTanqueRede;
	private Combo comboTanque;
	private ComboViewer cvTanque;
	private ComboViewer cvTanqueRede;
	private TableViewerColumn tvcOrigemTanque;
	private TableViewerColumn tvcFiltroTanque;
	private TableViewerColumn tvcOrigemTanqueRede;
	private TableViewerColumn tvcFiltroTanqueRede;
	private Combo comboHapa;
	private ComboViewer cvHapa;
	private TableViewerColumn tvcOrigemPassarela;
	private TableViewerColumn tvcOrigemHapa;
	private TableViewerColumn tvcFiltroPassarela;
	private TableViewerColumn tvcFiltroHapa;
	private TanqueRedeService tanqueRedeService;
	private Text tId;
	private ComboViewer cvPassarela;
	private Combo comboPassarela;

	public LoteGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() throws Exception {
		entidade.setStatus(false);
		entidade.setDataFimLote(FormatoHelper.dataFormat.parse(tDataFim
				.getText().trim()));

	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText().trim());
		tvLote.refresh();
	}

	@Override
	public void salvar() throws Exception {
		if (entidade == null)
			entidade = new Lote();

		valorComboSafra = (IStructuredSelection) cvSafra.getSelection();
		entidade.setSafraId((Safra) valorComboSafra.getFirstElement());

		entidade.setNome(tNome.getText().trim());
		entidade.setDataInicioLote(FormatoHelper.dataFormat.parse(tDataInicio
				.getText().trim()));
		entidade.setDataFimLote(FormatoHelper.dataFormat.parse(tDataFim
				.getText().trim()));
		entidade.setQuantidadePeixe(new BigDecimal(tQuantidade.getText().trim()
				.replaceAll(",", ".")));

		valorComboEspecie = (IStructuredSelection) cvEspecie.getSelection();
		entidade.setEspecieId((Especie) valorComboEspecie.getFirstElement());

		valorComboTanque = (IStructuredSelection) cvTanque.getSelection();
		entidade.setTanqueId((Tanque) valorComboTanque.getFirstElement());

		valorComboTanqueRede = (IStructuredSelection) cvTanqueRede
				.getSelection();
		entidade.setTanqueRedeId((TanqueRede) valorComboTanqueRede
				.getFirstElement());

		valorComboHapa = (IStructuredSelection) cvHapa.getSelection();
		entidade.setHapaId((Hapa) valorComboHapa.getFirstElement());

		entidade.setDescricao(tDescricao.getText().trim());
		entidade.setStatus(true);
		loteService.salvar(entidade);

	}

	@Override
	public void validar() throws Exception {

	}

	@Override
	public void carregar() {
		tvLote.setInput(loteService.buscarTodosLoteAtivo());
		tvLote.refresh();
		tDataInicio.setText(FormatoHelper.dataFormat.format(CalendarioHelper
				.retornaData()));
		tDataFim.setText("01/01/0001");
	}

	@Override
	public void limparDados() {
		tId.setText("");
		CalendarioHelper.limparData();
		comboSafra.deselectAll();
		tNome.setText("");
		tDataFim.setText("01/01/0001");
		tDataInicio.setText(FormatoHelper.dataFormat.format(CalendarioHelper
				.retornaData()));
		tQuantidade.setText("");
		comboEspecie.deselectAll();
		comboTanque.deselectAll();
		comboTanqueRede.deselectAll();
		comboPassarela.deselectAll();
		comboHapa.deselectAll();
		tDescricao.setText("");
		tFiltro.setText("");
		entidade = new Lote();
		tvOrigemLote.setInput(null);
		tvOrigemLote.refresh();
	}

	@Override
	public void carregarComponentes() {
		tId.setText(entidade.getId().toString());
		comboSafra.select(safraService.buscarTodosSafraAtivo().indexOf(
				entidade.getSafraId()));
		tNome.setText(entidade.getNome());
		tDataInicio.setText(FormatoHelper.dataFormat.format(entidade
				.getDataInicioLote()));
		tDataFim.setText(FormatoHelper.dataFormat.format(entidade
				.getDataFimLote()));
		tQuantidade.setText(entidade.getQuantidadePeixe().toString());

		comboEspecie.select(especieService.buscarTodosEspecieAtivo().indexOf(
				entidade.getEspecieId()));

		comboTanque.select(tanqueService.buscarTodosTanqueAtivo().indexOf(
				entidade.getTanqueId()));

		comboTanqueRede.select(tanqueRedeService.buscarTodosTanqueRedeAtivo()
				.indexOf(entidade.getTanqueRedeId()));

//		comboPassarela.select(passarelaService.buscarTodosPassarelaAtivo()
//				.indexOf(entidade.getHapaId().getPassarelaId()));

		comboHapa.select(hapaService.buscarTodosHapaAtivo().indexOf(
				entidade.getHapaId()));

		tDescricao.setText(entidade.getDescricao());

		tvOrigemLote.setInput(loteService.buscarLoteOrigem(entidade.getId()));
		tvOrigemLote.refresh();
		tvLote.setInput(loteService.buscarTodosLoteAtivo());

	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		entidade = new Lote();
		filtro = new LoteFiltro();
		loteService = new LoteService();
		hapaService = new HapaService();
		safraService = new SafraService();
		tanqueService = new TanqueService();
		especieService = new EspecieService();
		passarelaService = new PassarelaService();
		tanqueRedeService = new TanqueRedeService();

		composite.setLayout(new GridLayout(1, false));

		Group grpLote = new Group(composite, SWT.NONE);
		grpLote.setLayout(new GridLayout(8, false));
		grpLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpLote.setText("Lote");

		Label lblId = new Label(grpLote, SWT.NONE);
		lblId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblId.setText("Id:");

		tId = new Text(grpLote, SWT.BORDER);
		GridData gd_tId = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_tId.widthHint = 49;
		tId.setLayoutData(gd_tId);
		tId.setEditable(false);

		Label lblSafra = new Label(grpLote, SWT.NONE);
		lblSafra.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblSafra.setText("Safra:");

		cvSafra = new ComboViewer(grpLote, SWT.READ_ONLY);
		comboSafra = cvSafra.getCombo();
		comboSafra.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 2, 1));
		cvSafra.setContentProvider(ArrayContentProvider.getInstance());
		cvSafra.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Safra) element)
						.getDataInicio())
						+ " -- "
						+ FormatoHelper.dataFormat.format(((Safra) element)
								.getDataFim());
			}
		});
		cvSafra.setInput(safraService.buscarTodosSafraAtivo());

		Label label = new Label(grpLote, SWT.NONE);
		label.setText("Nome:");
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));

		tNome = new Text(grpLote, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2,
				1));

		Label lblDataDeInicio = new Label(grpLote, SWT.NONE);
		lblDataDeInicio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblDataDeInicio.setText("Inicio:");

		tDataInicio = new Text(grpLote, SWT.BORDER);
		tDataInicio.setEditable(false);
		tDataInicio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				false, 4, 1));

		Label lblNewLabel_1 = new Label(grpLote, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setText("Final:");

		tDataFim = new Text(grpLote, SWT.BORDER);
		tDataFim.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				2, 1));

		Label lblQuantidade = new Label(grpLote, SWT.NONE);
		lblQuantidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblQuantidade.setText("Quantidade:");

		tQuantidade = new Text(grpLote, SWT.BORDER);
		tQuantidade.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 4, 1));

		Label lblEspcie = new Label(grpLote, SWT.NONE);
		lblEspcie.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblEspcie.setText("Esp\u00E9cie:");

		cvEspecie = new ComboViewer(grpLote, SWT.READ_ONLY);
		comboEspecie = cvEspecie.getCombo();
		comboEspecie.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1));
		cvEspecie.setContentProvider(ArrayContentProvider.getInstance());
		cvEspecie.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Especie) element).getEspecie();
			}
		});
		cvEspecie.setInput(especieService.buscarTodosEspecieAtivo());

		Label lblDescrio = new Label(grpLote, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 2));
		lblDescrio.setText("Descri\u00E7\u00E3o:");

		tDescricao = new Text(grpLote, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_tDescricao = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 7, 2);
		gd_tDescricao.heightHint = 50;
		tDescricao.setLayoutData(gd_tDescricao);

		Label lblTanque = new Label(grpLote, SWT.NONE);
		lblTanque.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblTanque.setText("Tanque:");

		cvTanque = new ComboViewer(grpLote, SWT.READ_ONLY);
		comboTanque = cvTanque.getCombo();
		// comboTanque.addSelectionListener(new SelectionAdapter() {
		// @Override
		// public void widgetSelected(SelectionEvent e) {
		// valorComboTanque = (IStructuredSelection) cvTanque
		// .getSelection();
		// cvTanqueRede.setInput(tanqueRedeService
		// .buscarTanqueRedeTanque(((Tanque) valorComboTanque
		// .getFirstElement())));
		// cvTanqueRede.setInput(tanqueService.buscarTodosTanqueAtivo());
		// }
		// });
		GridData gd_comboTanque = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 4, 1);
		gd_comboTanque.widthHint = 324;
		comboTanque.setLayoutData(gd_comboTanque);
		cvTanque.setContentProvider(ArrayContentProvider.getInstance());
		cvTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Tanque) element).getNome();
			}
		});
		cvTanque.setInput(tanqueService.buscarTodosTanqueAtivo());

		Label label_1 = new Label(grpLote, SWT.NONE);
		label_1.setText("Tanque Rede:");
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));

		cvTanqueRede = new ComboViewer(grpLote, SWT.READ_ONLY);
		comboTanqueRede = cvTanqueRede.getCombo();
		comboTanqueRede.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1));
		cvTanqueRede.setContentProvider(ArrayContentProvider.getInstance());
		cvTanqueRede.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((TanqueRede) element).getNome();
			}
		});
		cvTanqueRede.setInput(tanqueRedeService.buscarTodosTanqueRedeLivre());

		Label lblPassarela = new Label(grpLote, SWT.NONE);
		lblPassarela.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblPassarela.setText("Passarela:");

		cvPassarela = new ComboViewer(grpLote, SWT.READ_ONLY);
		comboPassarela = cvPassarela.getCombo();
		comboPassarela.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 4, 1));
		cvPassarela.setContentProvider(ArrayContentProvider.getInstance());
		cvPassarela.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Passarela) element).getNome();
			}
		});
		cvPassarela.setInput(passarelaService.buscarTodosPassarelaAtivo());

		Label lblHapa = new Label(grpLote, SWT.NONE);
		lblHapa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblHapa.setText("Hapa:");

		cvHapa = new ComboViewer(grpLote, SWT.READ_ONLY);
		comboHapa = cvHapa.getCombo();
		comboHapa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				2, 1));
		cvHapa.setContentProvider(ArrayContentProvider.getInstance());
		cvHapa.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Hapa) element).getNome();
			}
		});
		cvHapa.setInput(hapaService.buscarTodasHapaLivre());

		Group grpOrigemDoLote = new Group(grpLote, SWT.NONE);
		grpOrigemDoLote.setText("Origem do Lote");
		grpOrigemDoLote.setLayout(new GridLayout(2, false));
		grpOrigemDoLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 8, 1));

		tvOrigemLote = new TableViewer(grpOrigemDoLote, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableOrigemLote = tvOrigemLote.getTable();
		GridData gd_tableOrigemLote = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 2);
		gd_tableOrigemLote.widthHint = 840;
		tableOrigemLote.setLayoutData(gd_tableOrigemLote);
		tableOrigemLote.setLinesVisible(true);
		tableOrigemLote.setHeaderVisible(true);

		tvOrigemLote.addFilter(filtro);
		tvOrigemLote.setContentProvider(ArrayContentProvider.getInstance());
		tvOrigemLote.setInput(entidade.getListaLote());

		tvcOrigemId = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getId().toString();
			}
		});
		TableColumn tvctextextId = tvcOrigemId.getColumn();
		tvctextextId.setWidth(40);
		tvctextextId.setText("Id");

		tvcOrigemSafra = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemSafra.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Lote) element)
						.getSafraId().getDataInicio());
			}
		});
		TableColumn tvcTextSafra = tvcOrigemSafra.getColumn();
		tvcTextSafra.setWidth(60);
		tvcTextSafra.setText("Safra");

		tvcOrigemNome = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getNome();
			}
		});
		TableColumn tvcTextNome = tvcOrigemNome.getColumn();
		tvcTextNome.setWidth(49);
		tvcTextNome.setText("Nome");

		tvcOrigemInicio = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemInicio.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Lote) element)
						.getDataInicioLote());
			}
		});
		TableColumn tvcTextInicio = tvcOrigemInicio.getColumn();
		tvcTextInicio.setWidth(80);
		tvcTextInicio.setText("Inicio");

		tvcOrigemFinal = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemFinal.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Lote) element)
						.getDataFimLote());
			}
		});
		TableColumn tvcTextFinal = tvcOrigemFinal.getColumn();
		tvcTextFinal.setWidth(80);
		tvcTextFinal.setText("Final");

		tvcOrigemQuantidade = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemQuantidade.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getQuantidadePeixe().toString();
			}
		});
		TableColumn tvcTextQuantidade = tvcOrigemQuantidade.getColumn();
		tvcTextQuantidade.setWidth(80);
		tvcTextQuantidade.setText("Quantidade");

		tvcOrigemEspecie = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemEspecie.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getEspecieId().getEspecie();
			}
		});
		TableColumn tvcTextEspecie = tvcOrigemEspecie.getColumn();
		tvcTextEspecie.setWidth(51);
		tvcTextEspecie.setText("Especie");

		tvcOrigemDescricao = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemDescricao.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getDescricao();
			}
		});
		TableColumn tvcTextDescricao = tvcOrigemDescricao.getColumn();
		tvcTextDescricao.setWidth(65);
		tvcTextDescricao.setText("Descri\u00E7\u00E3o");

		tvcOrigemTanque = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Tanque x = ((Lote) element).getTanqueId();
				if (x == null)
					return "";
				return ((Lote) element).getTanqueId().getNome();
			}
		});
		TableColumn tblclmnTanque_1 = tvcOrigemTanque.getColumn();
		tblclmnTanque_1.setWidth(56);
		tblclmnTanque_1.setText("Tanque");

		tvcOrigemTanqueRede = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemTanqueRede.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TanqueRede x = ((Lote) element).getTanqueRedeId();
				if (x == null)
					return "";
				return ((Lote) element).getTanqueRedeId().getNome();
			}
		});
		TableColumn tblclmnTanqueRede_1 = tvcOrigemTanqueRede.getColumn();
		tblclmnTanqueRede_1.setWidth(83);
		tblclmnTanqueRede_1.setText("Tanque Rede");

		tvcOrigemPassarela = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemPassarela.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Hapa x = ((Lote) element).getHapaId();
				if (x == null)
					return "";
				return ((Lote) element).getHapaId().getPassarelaId().getNome();
			}
		});
		TableColumn tblclmnPassarela_1 = tvcOrigemPassarela.getColumn();
		tblclmnPassarela_1.setWidth(65);
		tblclmnPassarela_1.setText("Passarela");

		tvcOrigemHapa = new TableViewerColumn(tvOrigemLote, SWT.NONE);
		tvcOrigemHapa.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Hapa x = ((Lote) element).getHapaId();
				if (x == null)
					return "";
				return ((Lote) element).getHapaId().getNome();
			}
		});
		TableColumn tblclmnHapa_1 = tvcOrigemHapa.getColumn();
		tblclmnHapa_1.setWidth(46);
		tblclmnHapa_1.setText("Hapa");

		btnAdd = new Button(grpOrigemDoLote, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvLote
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				List<Lote> lista = (List<Lote>) itemSelecao.toList();
				entidade.getListaLote().addAll(lista);
				tvOrigemLote.refresh();
			}
		});
		btnAdd.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false,
				1, 2));
		btnAdd.setText(" + ");

		Label lblFiltro = new Label(grpLote, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpLote, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				7, 1));
		tFiltro.setMessage("Filtro de Busca!!");

		tvLote = new TableViewer(grpLote, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.MULTI);
		tvLote.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvLote
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (Lote) itemSelecao.getFirstElement();
				carregarComponentes();
				tvLote.remove(entidade);

			}
		});
		tableLote = tvLote.getTable();
		tableLote.setLinesVisible(true);
		tableLote.setHeaderVisible(true);
		tableLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8,
				1));

		tvLote.addFilter(filtro);
		tvLote.setContentProvider(ArrayContentProvider.getInstance());

		tvcFiltroId = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcFiltroId.getColumn();
		tblclmnId.setWidth(40);
		tblclmnId.setText("Id");

		tvcFiltroSafra = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroSafra.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Lote) element)
						.getSafraId().getDataInicio());

			}
		});
		TableColumn tblclmnSafra = tvcFiltroSafra.getColumn();
		tblclmnSafra.setWidth(60);
		tblclmnSafra.setText("Safra");

		tvcFiltroNome = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getNome();
			}
		});
		TableColumn tblclmnNome = tvcFiltroNome.getColumn();
		tblclmnNome.setWidth(56);
		tblclmnNome.setText("Nome");

		tvcFiltroInicio = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroInicio.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Lote) element)
						.getDataInicioLote());
			}
		});
		TableColumn tblclmnInicio = tvcFiltroInicio.getColumn();
		tblclmnInicio.setWidth(80);
		tblclmnInicio.setText("Inicio");

		tvcFiltroFinal = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroFinal.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Lote) element)
						.getDataFimLote());
			}
		});
		TableColumn tblclmnFinal = tvcFiltroFinal.getColumn();
		tblclmnFinal.setWidth(80);
		tblclmnFinal.setText("Final");

		tvcFiltroQuantidade = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroQuantidade.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getQuantidadePeixe().toString();
			}
		});
		TableColumn tblclmnQuantidade = tvcFiltroQuantidade.getColumn();
		tblclmnQuantidade.setWidth(80);
		tblclmnQuantidade.setText("Quantidade");

		tvcFiltroEspecie = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroEspecie.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getEspecieId().getEspecie();
			}
		});
		TableColumn tblclmnEspecie = tvcFiltroEspecie.getColumn();
		tblclmnEspecie.setWidth(61);
		tblclmnEspecie.setText("Especie");

		tvcFiltroDescricao = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroDescricao.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getDescricao();
			}
		});
		TableColumn tblclmnDescrio = tvcFiltroDescricao.getColumn();
		tblclmnDescrio.setWidth(60);
		tblclmnDescrio.setText("Descri\u00E7\u00E3o");

		tvcFiltroTanque = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Tanque x = ((Lote) element).getTanqueId();
				if (x == null)
					return "";
				return ((Lote) element).getTanqueId().getNome();
			}
		});
		TableColumn tblclmnTanque = tvcFiltroTanque.getColumn();
		tblclmnTanque.setWidth(60);
		tblclmnTanque.setText("Tanque");

		tvcFiltroTanqueRede = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroTanqueRede.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TanqueRede x = ((Lote) element).getTanqueRedeId();
				if (x == null)
					return "";
				return ((Lote) element).getTanqueRedeId().getNome();
			}
		});
		TableColumn tblclmnTanqueRede = tvcFiltroTanqueRede.getColumn();
		tblclmnTanqueRede.setWidth(88);
		tblclmnTanqueRede.setText("Tanque Rede");

		tvcFiltroPassarela = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroPassarela.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Hapa x = ((Lote) element).getHapaId();
				if (x == null)
					return "";
				return ((Lote) element).getHapaId().getPassarelaId().getNome();
			}
		});
		TableColumn tblclmnPassarela = tvcFiltroPassarela.getColumn();
		tblclmnPassarela.setWidth(66);
		tblclmnPassarela.setText("Passarela");

		tvcFiltroHapa = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroHapa.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Hapa x = ((Lote) element).getHapaId();
				if (x == null)
					return "";
				return ((Lote) element).getHapaId().getNome();
			}
		});
		TableColumn tblclmnHapa = tvcFiltroHapa.getColumn();
		tblclmnHapa.setWidth(43);
		tblclmnHapa.setText("Hapa");

	}
}
