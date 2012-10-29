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
	private TableViewerColumn tvcId, tvcSafra, tvcNome, tvcInicio, tvcFinal,
			tvcQuantidade, tvcEspecie, tvcDescricao;
	private LoteService loteService;
	private SafraService safraService;
	private EspecieService especieService;
	private LoteFiltro filtro;
	private IStructuredSelection valorComboSafra, valorComboEspecie;

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

		tvcId = new TableViewerColumn(tvLote, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(40);
		tblclmnId.setText("Id");

		tvcSafra = new TableViewerColumn(tvLote, SWT.NONE);
		tvcSafra.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Lote) element)
						.getSafraId().getDataInicio());

			}
		});
		TableColumn tblclmnNewColumn = tvcSafra.getColumn();
		tblclmnNewColumn.setWidth(60);
		tblclmnNewColumn.setText("Safra");

		tvcNome = new TableViewerColumn(tvLote, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getNome();
			}
		});
		TableColumn tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(80);
		tblclmnNome.setText("Nome");

		tvcInicio = new TableViewerColumn(tvLote, SWT.NONE);
		tvcInicio.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Lote) element)
						.getDataInicioLote());
			}
		});
		TableColumn tblclmnInicio = tvcInicio.getColumn();
		tblclmnInicio.setWidth(80);
		tblclmnInicio.setText("Inicio");

		tvcFinal = new TableViewerColumn(tvLote, SWT.NONE);
		tvcFinal.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Lote) element)
						.getDataFimLote());
			}
		});
		TableColumn tblclmnFinal = tvcFinal.getColumn();
		tblclmnFinal.setWidth(80);
		tblclmnFinal.setText("Final");

		tvcQuantidade = new TableViewerColumn(tvLote, SWT.NONE);
		tvcQuantidade.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getQuantidadePeixe().toString();
			}
		});
		TableColumn tblclmnQuantidade = tvcQuantidade.getColumn();
		tblclmnQuantidade.setWidth(80);
		tblclmnQuantidade.setText("Quantidade");

		tvcEspecie = new TableViewerColumn(tvLote, SWT.NONE);
		tvcEspecie.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getEspecieId().getEspecie();
			}
		});
		TableColumn tblclmnEspecie = tvcEspecie.getColumn();
		tblclmnEspecie.setWidth(80);
		tblclmnEspecie.setText("Especie");

		tvcDescricao = new TableViewerColumn(tvLote, SWT.NONE);
		tvcDescricao.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getDescricao();
			}
		});
		TableColumn tblclmnDescrio = tvcDescricao.getColumn();
		tblclmnDescrio.setWidth(200);
		tblclmnDescrio.setText("Descri\u00E7\u00E3o");

	}
}
