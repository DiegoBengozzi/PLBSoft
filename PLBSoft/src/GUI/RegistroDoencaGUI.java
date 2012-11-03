package GUI;

import helper.CalendarioHelper;
import helper.FormatoHelper;
import modelo.Doenca;
import modelo.Lote;
import modelo.RegistroDoenca;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
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

import service.DoencaService;
import service.LoteService;
import service.RegistroDoencaService;
import filtro.RegistroDoencaFiltro;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.DoubleClickEvent;

public class RegistroDoencaGUI extends TelaEdicaoGUI<RegistroDoenca> {
	private Text tFiltro;
	private Text tData;
	private Text tDescricao;
	private Table tableRegistroDoenca;
	private ComboViewer cvLote, cvDoenca;
	private Combo comboLote, comboDoenca;
	private TableViewer tvRegistroDoenca;
	private TableViewerColumn tvcId, tvcLote, tvcDoenca, tvcData, tvcDescricao;
	private RegistroDoencaFiltro filtro;
	private RegistroDoencaService registroDoencaService;
	private IStructuredSelection valorComboDoenca, valorComboLote;
	private LoteService loteService;
	private DoencaService doencaService;

	public RegistroDoencaGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() throws Exception {
		entidade.setStatus(false);
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvRegistroDoenca.refresh();
	}

	@Override
	public void salvar() throws Exception {
		if (entidade == null)
			entidade = new RegistroDoenca();

		entidade.setData(CalendarioHelper.retornaData());
		entidade.setDescricao(tDescricao.getText().trim());
		entidade.setStatus(true);

		valorComboDoenca = (IStructuredSelection) cvDoenca.getSelection();
		entidade.setDoencaId((Doenca) valorComboDoenca.getFirstElement());

		valorComboLote = (IStructuredSelection) cvLote.getSelection();
		entidade.setLoteId((Lote) valorComboLote.getFirstElement());

		registroDoencaService.salvar(entidade);

	}

	@Override
	public void validar() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void carregar() {
		tvRegistroDoenca.setInput(registroDoencaService
				.buscarTodosRegistroDoencaAtivo());
		tvRegistroDoenca.refresh();

	}

	@Override
	public void limparDados() {
		CalendarioHelper.limparData();
		tData.setText("");
		tDescricao.setText("");
		comboDoenca.deselectAll();
		comboLote.deselectAll();
		tFiltro.setText("");
		entidade = null;

	}

	@Override
	public void carregarComponentes() {
		comboDoenca.select(doencaService.buscarTodosDoencaAtivo().indexOf(
				entidade.getDoencaId()));
		comboLote.select(loteService.buscarTodosLoteAtivo().indexOf(
				entidade.getLoteId()));

		tData.setText(FormatoHelper.dataFormat.format(entidade.getData()));
		tDescricao.setText(entidade.getDescricao());

	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new RegistroDoencaFiltro();
		registroDoencaService = new RegistroDoencaService();
		loteService = new LoteService();
		doencaService = new DoencaService();

		composite.setLayout(new GridLayout(1, false));

		Group grpRegistroDeDoenas = new Group(composite, SWT.NONE);
		grpRegistroDeDoenas.setLayout(new GridLayout(2, false));
		grpRegistroDeDoenas.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true, 1, 1));
		grpRegistroDeDoenas.setText("Registro de Doen\u00E7as");

		Label lblLote = new Label(grpRegistroDeDoenas, SWT.NONE);
		lblLote.setText("Lote:");

		cvLote = new ComboViewer(grpRegistroDeDoenas, SWT.READ_ONLY);
		comboLote = cvLote.getCombo();
		comboLote.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		cvLote.setContentProvider(ArrayContentProvider.getInstance());
		cvLote.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getNome();
			}
		});
		cvLote.setInput(loteService.buscarTodosLoteAtivo());

		Label lblDoena = new Label(grpRegistroDeDoenas, SWT.NONE);
		lblDoena.setText("Doen\u00E7a:");

		cvDoenca = new ComboViewer(grpRegistroDeDoenas, SWT.READ_ONLY);
		comboDoenca = cvDoenca.getCombo();
		comboDoenca.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cvDoenca.setContentProvider(ArrayContentProvider.getInstance());
		cvDoenca.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Doenca) element).getNome();
			}
		});
		cvDoenca.setInput(doencaService.buscarTodosDoencaAtivo());

		Label lblData = new Label(grpRegistroDeDoenas, SWT.NONE);
		lblData.setText("Data:");

		tData = new Text(grpRegistroDeDoenas, SWT.BORDER);
		tData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblDescrio = new Label(grpRegistroDeDoenas, SWT.NONE);
		lblDescrio.setText("Descri\u00E7\u00E3o:");

		tDescricao = new Text(grpRegistroDeDoenas, SWT.BORDER);
		tDescricao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblFiltro = new Label(grpRegistroDeDoenas, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpRegistroDeDoenas, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		tvRegistroDoenca = new TableViewer(grpRegistroDeDoenas, SWT.BORDER
				| SWT.FULL_SELECTION);
		tvRegistroDoenca.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvRegistroDoenca
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (RegistroDoenca) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		tableRegistroDoenca = tvRegistroDoenca.getTable();
		tableRegistroDoenca.setLinesVisible(true);
		tableRegistroDoenca.setHeaderVisible(true);
		tableRegistroDoenca.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true, 2, 1));

		tvRegistroDoenca.addFilter(filtro);
		tvRegistroDoenca.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvRegistroDoenca, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((RegistroDoenca) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(40);
		tblclmnId.setText("Id");

		tvcLote = new TableViewerColumn(tvRegistroDoenca, SWT.NONE);
		tvcLote.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				RegistroDoenca t = (RegistroDoenca) element;
				return t.getLoteId() == null ? "" : t.getLoteId().getNome();
			}
		});
		TableColumn tblclmnLote = tvcLote.getColumn();
		tblclmnLote.setWidth(100);
		tblclmnLote.setText("Lote");

		tvcDoenca = new TableViewerColumn(tvRegistroDoenca, SWT.NONE);
		tvcDoenca.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				RegistroDoenca t = (RegistroDoenca) element;
				return t.getDoencaId() == null ? "" : t.getDoencaId().getNome();
			}
		});
		TableColumn tblclmnDoena = tvcDoenca.getColumn();
		tblclmnDoena.setWidth(100);
		tblclmnDoena.setText("Doen\u00E7a");

		tvcData = new TableViewerColumn(tvRegistroDoenca, SWT.NONE);
		tvcData.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((RegistroDoenca) element)
						.getData());
			}
		});
		TableColumn tblclmnData = tvcData.getColumn();
		tblclmnData.setWidth(100);
		tblclmnData.setText("Data");

		tvcDescricao = new TableViewerColumn(tvRegistroDoenca, SWT.NONE);
		tvcDescricao.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((RegistroDoenca)element).getDescricao();
			}
		});
		TableColumn tblclmnDescrio = tvcDescricao.getColumn();
		tblclmnDescrio.setWidth(120);
		tblclmnDescrio.setText("Descri\u00E7\u00E3o");

	}

}
