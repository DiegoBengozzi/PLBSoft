package GUI;

import helper.FormatoHelper;

import java.math.BigDecimal;

import modelo.Biometria;
import modelo.Lote;

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

import service.BiometriaService;
import service.LoteService;
import filtro.BiometriaFiltro;

public class BiometriaGUI extends TelaEdicaoGUI<Biometria> {
	private Text tPesoMedio;
	private Combo comboLote;
	private ComboViewer cvLote;
	private Text tTamanhoMedio;
	private Text tQuantidadeAmostra;
	private Text tFiltro;
	private Table tableBiometria;
	private TableViewer tvBiometria;
	private TableViewerColumn tvcId;
	private TableViewerColumn tvcLote;
	private TableViewerColumn tvcPesoMedio;
	private TableViewerColumn tvcTamanhoMedio;
	private TableViewerColumn tvcQuantAmostra;
	private BiometriaFiltro filtro;
	private BiometriaService biometriaService;
	private IStructuredSelection valorCombo;
	private LoteService loteService;

	public BiometriaGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() throws Exception {
		entidade.setStatus(false);
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvBiometria.refresh();
	}

	@Override
	public void salvar() throws Exception {
		if (entidade == null)
			entidade = new Biometria();
		valorCombo = (IStructuredSelection) cvLote.getSelection();
		entidade.setLoteId((Lote) valorCombo.getFirstElement());
		entidade.setStatus(true);
		entidade.setPesoMedio(new BigDecimal(tPesoMedio.getText().trim()));
		entidade.setQuantidadeAmostra(new BigDecimal(tQuantidadeAmostra
				.getText().trim()));
		entidade.setTamanhoMedio(new BigDecimal(tTamanhoMedio.getText().trim()));
		biometriaService.salvar(entidade);

	}

	@Override
	public void validar() throws Exception {

	}

	@Override
	public void carregar() {
		tvBiometria.setInput(biometriaService.buscarTodosBiometriaAtivo());
		tvBiometria.refresh();
	}

	@Override
	public void limparDados() {
		comboLote.deselectAll();
		tFiltro.setText("");
		tQuantidadeAmostra.setText("");
		tPesoMedio.setText("");
		tTamanhoMedio.setText("");
		entidade = null;
	}

	@Override
	public void carregarComponentes() {
		comboLote.select(loteService.buscarTodosLoteAtivo().indexOf(
				entidade.getLoteId()));
		tQuantidadeAmostra.setText(FormatoHelper.getDecimalFormato().format(
				entidade.getQuantidadeAmostra()));
		tPesoMedio.setText(FormatoHelper.getDecimalFormato().format(
				entidade.getPesoMedio()));
		tTamanhoMedio.setText(FormatoHelper.getDecimalFormato().format(
				entidade.getTamanhoMedio()));
	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new BiometriaFiltro();
		biometriaService = new BiometriaService();
		loteService = new LoteService();

		composite.setLayout(new GridLayout(1, false));

		Group grpBiometria = new Group(composite, SWT.NONE);
		grpBiometria.setLayout(new GridLayout(5, false));
		grpBiometria.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		grpBiometria.setText("Biometria");

		Label lblLote = new Label(grpBiometria, SWT.NONE);
		lblLote.setText("Lote:");

		cvLote = new ComboViewer(grpBiometria, SWT.READ_ONLY);
		comboLote = cvLote.getCombo();
		comboLote.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				4, 1));
		cvLote.setContentProvider(ArrayContentProvider.getInstance());
		cvLote.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Lote) element).getNome();
			}
		});
		cvLote.setInput(loteService.buscarTodosLoteAtivo());

		Label lblPesoMdio = new Label(grpBiometria, SWT.NONE);
		lblPesoMdio.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 2, 1));
		lblPesoMdio.setText("Peso m\u00E9dio:");

		tPesoMedio = new Text(grpBiometria, SWT.BORDER);
		tPesoMedio.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 3, 1));

		Label lblTamanhoMdio = new Label(grpBiometria, SWT.NONE);
		lblTamanhoMdio.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1));
		lblTamanhoMdio.setText("Tamanho m\u00E9dio:");

		tTamanhoMedio = new Text(grpBiometria, SWT.BORDER);
		tTamanhoMedio.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1));

		Label lblQuantidadeDeAmostras = new Label(grpBiometria, SWT.NONE);
		lblQuantidadeDeAmostras.setLayoutData(new GridData(SWT.LEFT,
				SWT.CENTER, false, false, 4, 1));
		lblQuantidadeDeAmostras.setText("Quantidade de amostras:");

		tQuantidadeAmostra = new Text(grpBiometria, SWT.BORDER);
		tQuantidadeAmostra.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		Label lblFiltro = new Label(grpBiometria, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpBiometria, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				4, 1));

		tvBiometria = new TableViewer(grpBiometria, SWT.BORDER
				| SWT.FULL_SELECTION);
		tvBiometria.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvBiometria
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (Biometria) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		tableBiometria = tvBiometria.getTable();
		tableBiometria.setLinesVisible(true);
		tableBiometria.setHeaderVisible(true);
		tableBiometria.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 5, 1));

		tvBiometria.addFilter(filtro);
		tvBiometria.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvBiometria, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Biometria) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(40);
		tblclmnId.setText("Id");

		tvcLote = new TableViewerColumn(tvBiometria, SWT.NONE);
		tvcLote.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Biometria t = (Biometria) element;
				return t.getLoteId() == null ? "" : t.getLoteId().getNome();
			}
		});
		TableColumn tblclmnLote = tvcLote.getColumn();
		tblclmnLote.setWidth(100);
		tblclmnLote.setText("Lote");

		tvcPesoMedio = new TableViewerColumn(tvBiometria, SWT.NONE);
		tvcPesoMedio.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((Biometria) element).getPesoMedio());
			}
		});
		TableColumn tblclmnPesoMdio = tvcPesoMedio.getColumn();
		tblclmnPesoMdio.setWidth(100);
		tblclmnPesoMdio.setText("Peso M\u00E9dio");

		tvcTamanhoMedio = new TableViewerColumn(tvBiometria, SWT.NONE);
		tvcTamanhoMedio.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((Biometria) element).getTamanhoMedio());
			}
		});
		TableColumn tblclmnTamenhoMdio = tvcTamanhoMedio.getColumn();
		tblclmnTamenhoMdio.setWidth(100);
		tblclmnTamenhoMdio.setText("Tamanho M\u00E9dio");

		tvcQuantAmostra = new TableViewerColumn(tvBiometria, SWT.NONE);
		tvcQuantAmostra.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((Biometria) element).getQuantidadeAmostra());
			}
		});
		TableColumn tblclmnQuantidadeDeAmostra = tvcQuantAmostra.getColumn();
		tblclmnQuantidadeDeAmostra.setWidth(150);
		tblclmnQuantidadeDeAmostra.setText("Quantidade de Amostra");

	}

}
