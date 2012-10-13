package GUI;

import modelo.TipoTanque;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.TipoTanqueService;
import filtro.TipoTanqueFiltro;

public class TipoTanqueGUI extends TelaEdicaoGUI<TipoTanque> {

	private TipoTanqueService tipoTanqueService = new TipoTanqueService();
	private Text tNome;
	private Text tRevestimento;
	private Group grpTipoDeTanque;
	private Label lblFiltro;
	private Text tFiltro;
	private Table table;
	private TableViewer tvTipoTanque;
	private TableColumn tblclmnNome;
	private TableViewerColumn tvcNome;
	private TableColumn tblclmnRevestimento;
	private TableViewerColumn tvcRevestimento;
	private TableColumn tblclmnId;
	private TableViewerColumn tvcId;
	private TipoTanqueFiltro filtro;
	private TipoTanque entidade;

	public TipoTanqueGUI(Composite parent, int style) {
		super(parent, style);
		entidade = new TipoTanque();
	}

	@Override
	public void excluir() throws Exception {
		entidade.setStatus(false);
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvTipoTanque.refresh();
	}

	@Override
	public void salvar() throws Exception {
		if (entidade == null)
			entidade = new TipoTanque();

		entidade.setNome(tNome.getText());
		entidade.setRevestimento(tRevestimento.getText());
		entidade.setStatus(true);

		tipoTanqueService.salvar(entidade);

	}

	@Override
	public void limparDados() {
		tNome.setText("");
		tRevestimento.setText("");
		entidade = null;
	}

	@Override
	public void carregar() {
		tvTipoTanque.setInput(tipoTanqueService.buscarTodosTipoTanqueAtivo());
		tvTipoTanque.refresh();

	}

	@Override
	public void carregarComponentes() {
		tNome.setText(entidade.getNome());
		tRevestimento.setText(entidade.getRevestimento());

	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new TipoTanqueFiltro();

		composite.setLayout(new GridLayout(2, false));

		grpTipoDeTanque = new Group(composite, SWT.NONE);
		grpTipoDeTanque.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1));
		grpTipoDeTanque.setText("Tipo de Tanque");
		grpTipoDeTanque.setLayout(new GridLayout(2, false));

		Label lblNome = new Label(grpTipoDeTanque, SWT.NONE);
		lblNome.setText("Nome:");

		tNome = new Text(grpTipoDeTanque, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Label lblRevestimento = new Label(grpTipoDeTanque, SWT.NONE);
		lblRevestimento.setText("Revestimento:");

		tRevestimento = new Text(grpTipoDeTanque, SWT.BORDER);
		tRevestimento.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));
		tRevestimento.setText("");

		lblFiltro = new Label(grpTipoDeTanque, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpTipoDeTanque, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		tvTipoTanque = new TableViewer(grpTipoDeTanque, SWT.BORDER
				| SWT.FULL_SELECTION);
		tvTipoTanque.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvTipoTanque.getSelection();
				if (itemSelecao.isEmpty()) return;
				entidade = (TipoTanque) itemSelecao.getFirstElement();
				entidade = (TipoTanque) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		table = tvTipoTanque.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tvTipoTanque.addFilter(filtro);
		tvTipoTanque.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvTipoTanque, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((TipoTanque) element).getId().toString();
			}
		});
		tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(100);
		tblclmnId.setText("Id");

		tvcNome = new TableViewerColumn(tvTipoTanque, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((TipoTanque) element).getNome();
			}
		});
		tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(100);
		tblclmnNome.setText("Nome");

		tvcRevestimento = new TableViewerColumn(tvTipoTanque, SWT.NONE);
		tvcRevestimento.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((TipoTanque) element).getRevestimento();
			}
		});
		tblclmnRevestimento = tvcRevestimento.getColumn();
		tblclmnRevestimento.setWidth(100);
		tblclmnRevestimento.setText("Revestimento");

	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

}
