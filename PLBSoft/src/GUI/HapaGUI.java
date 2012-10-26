package GUI;

import helper.FormatoHelper;

import java.math.BigDecimal;

import modelo.Hapa;
import modelo.Passarela;

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

import service.HapaService;
import service.PassarelaService;
import filtro.HapaFiltro;

public class HapaGUI extends TelaEdicaoGUI<Hapa> {
	private Text tNome;
	private Text tTamanho;
	private Text tFiltro;
	private Table table;
	private TableViewer tvHapa;
	private ComboViewer cvPassarelaId;
	private TableViewerColumn tvcId, tvcNome, tvcTamanho, tvcPassarelaId;
	private IStructuredSelection valorCombo;
	private HapaFiltro filtro;
	private HapaService hapaService;
	private PassarelaService passarelaService;
	private Combo comboPassarela;

	public HapaGUI(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	public void excluir() {
		entidade.setStatus(false);

	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText().trim());
		tvHapa.refresh();

	}

	@Override
	public void salvar() {
		if (entidade == null)
			entidade = new Hapa();
		
		entidade.setNome(tNome.getText().trim());
		entidade.setStatus(true);
		entidade.setTamanho(new BigDecimal(tTamanho.getText().trim()
				.replaceAll(",", ".")));
		
		valorCombo = (IStructuredSelection) cvPassarelaId.getSelection();
		entidade.setPassarelaId((Passarela) valorCombo.getFirstElement());
		
		hapaService.salvar(entidade);
	}

	@Override
	public void validar() throws Exception {

	}

	@Override
	public void carregar() {
		tvHapa.setInput(hapaService.buscarTodosHapaAtivo());
		tvHapa.refresh();

	}

	@Override
	public void limparDados() {
		tNome.setText("");
		tTamanho.setText("");
		comboPassarela.deselectAll();
		entidade = null;

	}

	@Override
	public void carregarComponentes() {
		tNome.setText(entidade.getNome());
		tTamanho.setText(FormatoHelper.getDecimalFormato().format(
				entidade.getTamanho()));
		comboPassarela.select(hapaService.buscarTodosHapaAtivo().indexOf(
				entidade.getPassarelaId()));

	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new HapaFiltro();
		hapaService = new HapaService();
		passarelaService = new PassarelaService();

		composite.setLayout(new GridLayout(1, false));

		Group grpHapa = new Group(composite, SWT.NONE);
		grpHapa.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpHapa.setText("Hapa");
		grpHapa.setLayout(new GridLayout(2, false));

		Label lblNome = new Label(grpHapa, SWT.NONE);
		lblNome.setText("Nome:");

		tNome = new Text(grpHapa, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Label lblTamanho = new Label(grpHapa, SWT.NONE);
		lblTamanho.setText("Tamanho:");

		tTamanho = new Text(grpHapa, SWT.BORDER);
		tTamanho.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));

		Label lblPassarela = new Label(grpHapa, SWT.NONE);
		lblPassarela.setText("Passarela:");

		cvPassarelaId = new ComboViewer(grpHapa, SWT.READ_ONLY);
		comboPassarela = cvPassarelaId.getCombo();
		comboPassarela.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));
		cvPassarelaId.setContentProvider(ArrayContentProvider.getInstance());
		cvPassarelaId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Passarela) element).getNome();
			}
		});
		cvPassarelaId.setInput(passarelaService.buscarTodosPassarelaAtivo());

		Label lblFiltro = new Label(grpHapa, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpHapa, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		tFiltro.setMessage("Filtro de Busca!!");

		tvHapa = new TableViewer(grpHapa, SWT.BORDER | SWT.FULL_SELECTION);
		tvHapa.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvHapa
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (Hapa) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		table = tvHapa.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		tvHapa.addFilter(filtro);
		tvHapa.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvHapa, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Hapa) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(40);
		tblclmnId.setText("Id");

		tvcNome = new TableViewerColumn(tvHapa, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Hapa) element).getNome();
			}
		});
		TableColumn tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(120);
		tblclmnNome.setText("Nome");

		tvcTamanho = new TableViewerColumn(tvHapa, SWT.NONE);
		tvcTamanho.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((Hapa) element).getTamanho());
			}
		});
		TableColumn tblclmnTamanho = tvcTamanho.getColumn();
		tblclmnTamanho.setWidth(141);
		tblclmnTamanho.setText("Tamanho");

		tvcPassarelaId = new TableViewerColumn(tvHapa, SWT.NONE);
		tvcPassarelaId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Hapa h = (Hapa) element;
				return h.getPassarelaId() == null ? "" : h.getPassarelaId()
						.getNome();
			}
		});
		TableColumn tblclmnPassarela = tvcPassarelaId.getColumn();
		tblclmnPassarela.setWidth(127);
		tblclmnPassarela.setText("Passarela");

	}
}
