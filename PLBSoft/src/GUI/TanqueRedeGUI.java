package GUI;

import helper.FormatoHelper;

import java.math.BigDecimal;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.TanqueRedeService;
import service.TanqueService;
import filtro.TanqueRedeFiltro;

public class TanqueRedeGUI extends TelaEdicaoGUI<TanqueRede> {
	private Text tNome;
	private Text tTamanho;

	private Label lblTanque;
	private Table table;
	private TableViewer tvTanqueRede;
	private TableColumn tblclmnNome;
	private TableViewerColumn tvcNome;
	private TableColumn tblclmnTamanho;
	private TableViewerColumn tvcTamanho;
	private TanqueRedeFiltro filtro;
	private Label lblFiltro;
	private Text tFiltro;

	private TanqueRedeService tanqueRedeService = new TanqueRedeService();
	private TanqueRede entidade;
	private Group grpTanqueRede;
	private TableColumn tblclmnId;
	private TableViewerColumn tvcId;
	private Combo combo;
	private ComboViewer cvTanque;
	private TanqueService tanqueSerice;
	private TableColumn tblclmnTanque;
	private TableViewerColumn tvcTanque;

	public TanqueRedeGUI(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	public void excluir() throws Exception {
		entidade.setStatus(false);
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvTanqueRede.refresh();
	}

	@Override
	public void salvar() throws Exception {
		if (entidade == null)
			entidade = new TanqueRede();

		entidade.setNome(tNome.getText().trim());
		entidade.setTamanho(new BigDecimal(tTamanho.getText().trim()));
		entidade.setStatus(true);
		IStructuredSelection valorCombo = (IStructuredSelection) cvTanque.getSelection();
		entidade.setTanqueId((Tanque)valorCombo.getFirstElement());

		tanqueRedeService.salvar(entidade);

	}
	
	@Override
	public void validar() throws Exception {

	}

	@Override
	public void limparDados() {

		tNome.setText("");
		tTamanho.setText("");
		entidade = null;

	}

	@Override
	public void carregar() {
		tvTanqueRede.setInput(tanqueRedeService.buscarTodosTanqueRedeAtivo());
		tvTanqueRede.refresh();
	}

	@Override
	public void carregarComponentes() {
		tNome.setText(entidade.getNome());
		tTamanho.setText(FormatoHelper.getDecimalFormato().format(
				entidade.getTamanho()));
	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new TanqueRedeFiltro();
		tanqueSerice = new TanqueService();
		
		composite.setLayout(new GridLayout(3, false));
		grpTanqueRede = new Group(composite, SWT.NONE);
		grpTanqueRede.setText("Tanque Rede");
		grpTanqueRede.setLayout(new GridLayout(2, false));
		grpTanqueRede.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 3, 1));
		Label lblNome = new Label(grpTanqueRede, SWT.NONE);
		lblNome.setSize(36, 15);
		lblNome.setText("Nome:");

		tNome = new Text(grpTanqueRede, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		tNome.setSize(196, 21);

		Label lblTamanho = new Label(grpTanqueRede, SWT.NONE);
		lblTamanho.setSize(71, 15);
		lblTamanho.setText("Tamanha m\u00B3:");

		tTamanho = new Text(grpTanqueRede, SWT.BORDER);
		tTamanho.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		tTamanho.setSize(196, 21);

		lblTanque = new Label(grpTanqueRede, SWT.NONE);
		lblTanque.setSize(43, 15);
		lblTanque.setText("Tanque:");

		cvTanque = new ComboViewer(grpTanqueRede, SWT.NONE);
		combo = cvTanque.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		cvTanque.setContentProvider(ArrayContentProvider.getInstance());
		cvTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Tanque) element).getNome();
			}
		});
		cvTanque.setInput(tanqueSerice.buscarTodosTanqueAtivo());

		lblFiltro = new Label(grpTanqueRede, SWT.NONE);
		lblFiltro.setSize(36, 15);
		lblFiltro.setText("Filtro...");

		tFiltro = new Text(grpTanqueRede, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		tFiltro.setSize(196, 21);
		tFiltro.setMessage("Filtro de Buscar!!!");

		tvTanqueRede = new TableViewer(grpTanqueRede, SWT.BORDER
				| SWT.FULL_SELECTION);
		tvTanqueRede.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvTanqueRede
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				entidade = (TanqueRede) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		table = tvTanqueRede.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setSize(272, 77);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tvTanqueRede.addFilter(filtro);
		tvTanqueRede.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvTanqueRede, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((TanqueRede) element).getId().toString();
			}
		});
		tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(39);
		tblclmnId.setText("Id");

		tvcNome = new TableViewerColumn(tvTanqueRede, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((TanqueRede) element).getNome();
			}
		});
		tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(107);
		tblclmnNome.setText("Nome");

		tvcTamanho = new TableViewerColumn(tvTanqueRede, SWT.NONE);
		tvcTamanho.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((TanqueRede) element).getTamanho());
			}
		});
		tblclmnTamanho = tvcTamanho.getColumn();
		tblclmnTamanho.setWidth(127);
		tblclmnTamanho.setText("Tamanho m\u00B3");

		tvcTanque = new TableViewerColumn(tvTanqueRede, SWT.NONE);
		tvcTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TanqueRede t = (TanqueRede) element;
				return t.getTanqueId() == null ? "" : t.getTanqueId().getNome();
			}
		});
		tblclmnTanque = tvcTanque.getColumn();
		tblclmnTanque.setWidth(100);
		tblclmnTanque.setText("Tanque");

	}

}
