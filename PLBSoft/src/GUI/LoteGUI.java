package GUI;

import helper.CalendarioHelper;
import helper.FormatoHelper;
import modelo.Especie;
import modelo.Lote;
import modelo.Safra;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import service.EspecieService;
import service.LoteService;
import service.SafraService;
import filtro.LoteFiltro;
import org.eclipse.swt.widgets.Button;

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
	private TableViewer tvLote;
	private TableViewerColumn tvcFiltroId, tvcFiltroSafra, tvcFiltroNome, tvcFiltroInicio, tvcFiltroFinal,
			tvcFiltroQuantidade, tvcFiltroEspecie, tvcFiltroDescricao;
	private LoteService loteService;
	private SafraService safraService;
	private EspecieService especieService;
	private LoteFiltro filtro;
	private IStructuredSelection valorComboSafra, valorComboEspecie;
	private Table tableOrigemLote;

	public LoteGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() throws Exception {
		entidade.setStatus(false);
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
		
		entidade.setQuantidadePeixe(new Long(tQuantidade.getText().trim()));
		valorComboEspecie = (IStructuredSelection) cvEspecie.getSelection();
		entidade.setEspecieId((Especie) valorComboEspecie.getFirstElement());
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
	}

	@Override
	public void limparDados() {
		CalendarioHelper.limparData();
		comboSafra.deselectAll();
		tNome.setText("");
		// tDataInicio.setText("");
		tDataInicio.setText(FormatoHelper.dataFormat.format(CalendarioHelper
				.retornaData()));
		tDataFim.setText("");
		tQuantidade.setText("");
		comboEspecie.deselectAll();
		tDescricao.setText("");
		entidade = null;
	}

	@Override
	public void carregarComponentes() {
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
		tDescricao.setText(entidade.getDescricao());
	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new LoteFiltro();
		loteService = new LoteService();
		safraService = new SafraService();
		especieService = new EspecieService();

		composite.setLayout(new GridLayout(1, false));

		Group grpLote = new Group(composite, SWT.NONE);
		grpLote.setLayout(new GridLayout(2, false));
		grpLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpLote.setText("Lote");

		Label lblSafra = new Label(grpLote, SWT.NONE);
		lblSafra.setText("Safra:");

		cvSafra = new ComboViewer(grpLote, SWT.READ_ONLY);
		comboSafra = cvSafra.getCombo();
		comboSafra.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
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

		Label lblNome = new Label(grpLote, SWT.NONE);
		lblNome.setText("Nome:");

		tNome = new Text(grpLote, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblDataDeInicio = new Label(grpLote, SWT.NONE);
		lblDataDeInicio.setText("Inicio:");

		tDataInicio = new Text(grpLote, SWT.BORDER);
		tDataInicio.setEditable(false);
		tDataInicio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));

		Label lblFinal = new Label(grpLote, SWT.NONE);
		lblFinal.setText("Final:");

		tDataFim = new Text(grpLote, SWT.BORDER);
		tDataFim.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblQuantidade = new Label(grpLote, SWT.NONE);
		lblQuantidade.setText("Quantidade:");

		tQuantidade = new Text(grpLote, SWT.BORDER);
		tQuantidade.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblEspcie = new Label(grpLote, SWT.NONE);
		lblEspcie.setText("Esp\u00E9cie:");

		cvEspecie = new ComboViewer(grpLote, SWT.READ_ONLY);
		comboEspecie = cvEspecie.getCombo();
		comboEspecie.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cvEspecie.setContentProvider(ArrayContentProvider.getInstance());
		cvEspecie.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Especie) element).getEspecie();
			}
		});
		cvEspecie.setInput(especieService.buscarTodosEspecieAtivo());

		Label lblDescrio = new Label(grpLote, SWT.NONE);
		lblDescrio.setText("Descri\u00E7\u00E3o:");

		tDescricao = new Text(grpLote, SWT.BORDER);
		tDescricao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		
		Group grpOrigemDoLote = new Group(grpLote, SWT.NONE);
		grpOrigemDoLote.setText("Origem do Lote");
		grpOrigemDoLote.setLayout(new GridLayout(2, false));
		grpOrigemDoLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		TableViewer tableViewer = new TableViewer(grpOrigemDoLote, SWT.BORDER | SWT.FULL_SELECTION);
		tableOrigemLote = tableViewer.getTable();
		tableOrigemLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableOrigemLote.setLinesVisible(true);
		tableOrigemLote.setHeaderVisible(true);
		
		TableViewerColumn tvcOrigemId = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn = tvcOrigemId.getColumn();
		tableColumn.setWidth(40);
		tableColumn.setText("Id");
		
		TableViewerColumn tvcOrigemSafra = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_1 = tvcOrigemSafra.getColumn();
		tableColumn_1.setWidth(60);
		tableColumn_1.setText("Safra");
		
		TableViewerColumn tvcOrigemNome = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_2 = tvcOrigemNome.getColumn();
		tableColumn_2.setWidth(80);
		tableColumn_2.setText("Nome");
		
		TableViewerColumn tvcOrigemInicio = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_3 = tvcOrigemInicio.getColumn();
		tableColumn_3.setWidth(80);
		tableColumn_3.setText("Inicio");
		
		TableViewerColumn tvcOrigemFinal = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_4 = tvcOrigemFinal.getColumn();
		tableColumn_4.setWidth(80);
		tableColumn_4.setText("Final");
		
		TableViewerColumn tvcOrigemQuantidade = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_5 = tvcOrigemQuantidade.getColumn();
		tableColumn_5.setWidth(80);
		tableColumn_5.setText("Quantidade");
		
		TableViewerColumn tvcOrigemEspecie = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_6 = tvcOrigemEspecie.getColumn();
		tableColumn_6.setWidth(80);
		tableColumn_6.setText("Especie");
		
		TableViewerColumn tvcOrigemDescricao = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_7 = tvcOrigemDescricao.getColumn();
		tableColumn_7.setWidth(117);
		tableColumn_7.setText("Descri\u00E7\u00E3o");
		
		Button btnAdicionar = new Button(grpOrigemDoLote, SWT.NONE);
		btnAdicionar.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1));
		btnAdicionar.setText("+");

		Label lblFiltro = new Label(grpLote, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpLote, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		tFiltro.setMessage("Filtro de Busca!!");

		tvLote = new TableViewer(grpLote, SWT.BORDER | SWT.FULL_SELECTION);
		tvLote.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvLote
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (Lote) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		tableLote = tvLote.getTable();
		tableLote.setLinesVisible(true);
		tableLote.setHeaderVisible(true);
		tableLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2,
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
		TableColumn tblclmnNewColumn = tvcFiltroSafra.getColumn();
		tblclmnNewColumn.setWidth(60);
		tblclmnNewColumn.setText("Safra");

		tvcFiltroNome = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getNome();
			}
		});
		TableColumn tblclmnNome = tvcFiltroNome.getColumn();
		tblclmnNome.setWidth(80);
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
		tblclmnEspecie.setWidth(80);
		tblclmnEspecie.setText("Especie");

		tvcFiltroDescricao = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFiltroDescricao.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getDescricao();
			}
		});
		TableColumn tblclmnDescrio = tvcFiltroDescricao.getColumn();
		tblclmnDescrio.setWidth(151);
		tblclmnDescrio.setText("Descri\u00E7\u00E3o");

	}
}
