package GUI;

import helper.FormatoHelper;
import modelo.Passarela;
import modelo.Tanque;

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

import service.PassarelaService;
import service.TanqueService;
import filtro.PassarelaFiltro;

public class PassarelaGUI extends TelaEdicaoGUI<Passarela> {
	private Text tNome;
	private Text tCapacidade;
	private Table table;
	private Text tFiltro;
	private TableViewer tvPassarela;
	private TableViewerColumn tvcId, tvcNome, tvcCapacidadeHapa, tvcTanqueId;
	private PassarelaFiltro filtro;
	private PassarelaService passarelaService;
	private IStructuredSelection valorCombo;
	private ComboViewer cvTanque;
	private TanqueService tanqueService;
	

	public PassarelaGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() {
		entidade.setStatus(false);
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText().trim());
		tvPassarela.refresh();
	}

	@Override
	public void salvar() {
		if(entidade == null)
			entidade = new Passarela();
		
		entidade.setNome(tNome.getText().trim());
		entidade.setCapacidade(new Long(tCapacidade.getText().trim()));
		entidade.setStatus(true);
		valorCombo = (IStructuredSelection) cvTanque.getSelection();
		entidade.setTanqueId((Tanque)valorCombo.getFirstElement());
		passarelaService.salvar(entidade);
	}

	@Override
	public void validar() throws Exception {

	}

	@Override
	public void carregar() {
		tvPassarela.setInput(passarelaService.buscarTodosPassarelaAtivo());
		tvPassarela.refresh();
	}

	@Override
	public void limparDados() {
		tNome.setText("");
		tCapacidade.setText("");
		entidade = null;
	}

	@Override
	public void carregarComponentes() {
		tNome.setText(entidade.getNome());
		tCapacidade.setText(FormatoHelper.getDecimalFormato().format(entidade.getCapacidade()));
	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new PassarelaFiltro();
		passarelaService = new PassarelaService();
		tanqueService = new TanqueService();

		composite.setLayout(new GridLayout(1, false));

		Group grpPassarela = new Group(composite, SWT.NONE);
		grpPassarela.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		grpPassarela.setText("Passarela");
		grpPassarela.setLayout(new GridLayout(2, false));

		Label lblNome = new Label(grpPassarela, SWT.NONE);
		lblNome.setText("Nome:");

		tNome = new Text(grpPassarela, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Label lblCapacidadeDeHapas = new Label(grpPassarela, SWT.NONE);
		lblCapacidadeDeHapas.setText("Capacidade de Hapa:");

		tCapacidade = new Text(grpPassarela, SWT.BORDER);
		tCapacidade.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));

		Label lblTanque = new Label(grpPassarela, SWT.NONE);
		lblTanque.setText("Tanque:");

		cvTanque = new ComboViewer(grpPassarela, SWT.NONE);
		Combo combo = cvTanque.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		cvTanque.setContentProvider(ArrayContentProvider.getInstance());
		cvTanque.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Tanque)element).getNome();
			}
		});
		cvTanque.setInput(tanqueService.buscarTodosTanqueAtivo());

		Label lblFiltro = new Label(grpPassarela, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpPassarela, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));

		tvPassarela = new TableViewer(grpPassarela, SWT.BORDER
				| SWT.FULL_SELECTION);
		tvPassarela.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvPassarela
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (Passarela) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		table = tvPassarela.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tvPassarela.addFilter(filtro);
		tvPassarela.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvPassarela, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Passarela) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(41);
		tblclmnId.setText("Id");

		tvcNome = new TableViewerColumn(tvPassarela, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Passarela) element).getNome();
			}
		});
		TableColumn tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(100);
		tblclmnNome.setText("Nome");

		tvcCapacidadeHapa = new TableViewerColumn(tvPassarela, SWT.NONE);
		tvcCapacidadeHapa.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((Passarela) element).getCapacidade());
			}
		});
		TableColumn tblclmnCapacidadeDeHapa = tvcCapacidadeHapa.getColumn();
		tblclmnCapacidadeDeHapa.setWidth(100);
		tblclmnCapacidadeDeHapa.setText("Capacidade de Hapa");

		tvcTanqueId = new TableViewerColumn(tvPassarela, SWT.NONE);
		tvcTanqueId.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				Passarela p = (Passarela) element;
				return p.getTanqueId() == null ? "" : p.getTanqueId()
						.getNome();
			}
		});
		
		TableColumn tblclmnTanque = tvcTanqueId.getColumn();
		tblclmnTanque.setWidth(100);
		tblclmnTanque.setText("Tanque");

	}
}
