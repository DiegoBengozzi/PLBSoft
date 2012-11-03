package GUI;

import helper.FormatoHelper;

import java.math.BigDecimal;

import modelo.SistemaProducao;
import modelo.Tanque;
import modelo.TipoTanque;

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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.SistemaProducaoService;
import service.TanqueService;
import service.TipoTanqueService;
import filtro.TanqueFiltro;

public class TanqueGUI extends TelaEdicaoGUI<Tanque> {
	private Table table;
	private Group grpTanque;
	private Text tNome;
	private Text tLaminaAgua;
	private Text tProfundidade;
	private Text tDescricao;
	private Text tFiltro;
	private TableViewer tvTanque;
	private ComboViewer cvTipoTanque, cvSistemaProducao;
	private TableViewerColumn tvcNome, tvcLaminaAgua, tvcProfundidade,
			tvcAcessibilidade, tvcDescricao, tvcTipoTanque, tvcId,
			tvcSistemaProducao;
	private TanqueService tanqueService = new TanqueService();
	private TanqueFiltro filtro;
	private TipoTanqueService tipoTanqueService;
	private SistemaProducaoService sistemaProducaoService;
	private Button rbBaixo, rbMedio, rbAlto;
	private IStructuredSelection valorCombo, valorCombo1;
	private Combo comboTipoTanque, comboSistemaProducao;

	public TanqueGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() throws Exception {
		entidade.setStatus(false);

	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText().trim());
		tvTanque.refresh();
	}

	@Override
	public void salvar() throws Exception {
		if (entidade == null)
			entidade = new Tanque();

		entidade.setNome(tNome.getText().trim());
		entidade.setLaminaAgua(new BigDecimal(tLaminaAgua.getText().trim()
				.replaceAll(",", ".")));
		entidade.setProfundidade(new BigDecimal(tProfundidade.getText().trim()
				.replaceAll(",", ".")));
		entidade.setAcessibilidade(getValorRadio().trim());
		entidade.setDescricao(tDescricao.getText().trim());
		entidade.setStatus(true);

		valorCombo = (IStructuredSelection) cvTipoTanque.getSelection();
		entidade.setTipoTanqueId((TipoTanque) valorCombo.getFirstElement());

		valorCombo1 = (IStructuredSelection) cvSistemaProducao.getSelection();
		entidade.setSistemaProducaoId((SistemaProducao) valorCombo1
				.getFirstElement());

		tanqueService.salvar(entidade);
	}

	@Override
	public void validar() throws Exception {

	}

	@Override
	public void limparDados() {
		tNome.setText("");
		tLaminaAgua.setText("");
		tProfundidade.setText("");
		setValorRadio("");
		tDescricao.setText("");
		comboTipoTanque.deselectAll();
		comboSistemaProducao.deselectAll();
		tFiltro.setText("");
		entidade = null;
	}

	@Override
	public void carregar() {
		tvTanque.setInput(tanqueService.buscarTodosTanqueAtivo());
		tvTanque.refresh();
	}

	@Override
	public void carregarComponentes() {
		tNome.setText(entidade.getNome());
		tLaminaAgua.setText(FormatoHelper.getDecimalFormato().format(
				entidade.getLaminaAgua()));
		tProfundidade.setText(FormatoHelper.getDecimalFormato().format(
				entidade.getProfundidade()));
		setValorRadio(entidade.getAcessibilidade());
		tDescricao.setText(entidade.getDescricao());
		comboTipoTanque.select(tipoTanqueService.buscarTodosTipoTanqueAtivo()
				.indexOf(entidade.getTipoTanqueId()));
		comboSistemaProducao.select(sistemaProducaoService
				.buscarTodosSistemaProducaoAtivo().indexOf(
						entidade.getSistemaProducaoId()));
	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	private void setValorRadio(String radio) {
		if (radio.equalsIgnoreCase("ALTO")) {
			rbAlto.setSelection(true);
		} else if (radio.equalsIgnoreCase("MEDIO")) {
			rbMedio.setSelection(true);
		} else if (radio.equalsIgnoreCase("BAIXO")) {
			rbBaixo.setSelection(true);
		} else {
			rbAlto.setSelection(false);
			rbMedio.setSelection(false);
			rbBaixo.setSelection(false);
		}
	}

	private String getValorRadio() {
		if (rbAlto.getSelection()) {
			return "ALTO";
		} else if (rbMedio.getSelection()) {
			return "MEDIO";
		} else {
			return "BAIXO";
		}
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new TanqueFiltro();
		tipoTanqueService = new TipoTanqueService();
		sistemaProducaoService = new SistemaProducaoService();

		composite.setLayout(new GridLayout(2, false));
		grpTanque = new Group(composite, SWT.NONE);
		grpTanque.setLayout(new GridLayout(14, false));
		grpTanque.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2,
				1));
		grpTanque.setText("Tanque");
		Label lblNome = new Label(grpTanque, SWT.NONE);
		lblNome.setSize(36, 15);
		lblNome.setText("Nome:");

		tNome = new Text(grpTanque, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 13, 1));
		tNome.setSize(439, 21);

		Label lblLaminaDegua = new Label(grpTanque, SWT.NONE);
		lblLaminaDegua.setSize(87, 15);
		lblLaminaDegua.setText("Lamina de \u00E1gua:");

		tLaminaAgua = new Text(grpTanque, SWT.BORDER);
		tLaminaAgua.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				13, 1));
		tLaminaAgua.setSize(439, 21);

		Label lblProfundidade = new Label(grpTanque, SWT.NONE);
		lblProfundidade.setSize(75, 15);
		lblProfundidade.setText("Profundidade:");

		tProfundidade = new Text(grpTanque, SWT.BORDER);
		tProfundidade.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 13, 1));
		tProfundidade.setSize(439, 21);
		tProfundidade.setText("");

		Label lblAcessibilidade = new Label(grpTanque, SWT.NONE);
		lblAcessibilidade.setSize(78, 15);
		lblAcessibilidade.setText("Acessibilidade:");

		rbBaixo = new Button(grpTanque, SWT.RADIO);
		rbBaixo.setText("Baixo");

		rbMedio = new Button(grpTanque, SWT.RADIO);
		rbMedio.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false,
				3, 1));
		rbMedio.setText("Medio");

		rbAlto = new Button(grpTanque, SWT.RADIO);
		rbAlto.setText("Alto");
		new Label(grpTanque, SWT.NONE);
		new Label(grpTanque, SWT.NONE);
		new Label(grpTanque, SWT.NONE);
		new Label(grpTanque, SWT.NONE);
		new Label(grpTanque, SWT.NONE);
		new Label(grpTanque, SWT.NONE);
		new Label(grpTanque, SWT.NONE);
		new Label(grpTanque, SWT.NONE);

		Label lblDescrio = new Label(grpTanque, SWT.NONE);
		lblDescrio.setSize(54, 15);
		lblDescrio.setText("Descri\u00E7\u00E3o:");

		tDescricao = new Text(grpTanque, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_tDescricao = new GridData(SWT.FILL, SWT.FILL, true, false,
				13, 1);
		gd_tDescricao.heightHint = 30;
		tDescricao.setLayoutData(gd_tDescricao);
		tDescricao.setSize(439, 50);

		Label lblTipoDeTanque = new Label(grpTanque, SWT.NONE);
		lblTipoDeTanque.setSize(86, 15);
		lblTipoDeTanque.setText("Tipo de Tanque:");

		cvTipoTanque = new ComboViewer(grpTanque, SWT.READ_ONLY);
		comboTipoTanque = cvTipoTanque.getCombo();
		comboTipoTanque.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 13, 1));
		cvTipoTanque.setContentProvider(ArrayContentProvider.getInstance());
		cvTipoTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((TipoTanque) element).getNome();
			}
		});
		cvTipoTanque.setInput(tipoTanqueService.buscarTodosTipoTanqueAtivo());

		Label lblTipoDeSistema = new Label(grpTanque, SWT.NONE);
		lblTipoDeSistema.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 3, 1));
		lblTipoDeSistema.setText("Tipo de Sistema de Produ\u00E7\u00E3o:");

		cvSistemaProducao = new ComboViewer(grpTanque, SWT.READ_ONLY);
		comboSistemaProducao = cvSistemaProducao.getCombo();
		comboSistemaProducao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 11, 1));
		cvSistemaProducao
				.setContentProvider(ArrayContentProvider.getInstance());
		cvSistemaProducao.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((SistemaProducao) element).getSistemaProducao();
			}
		});
		cvSistemaProducao.setInput(sistemaProducaoService
				.buscarTodosSistemaProducaoAtivo());

		Label lblFiltro = new Label(grpTanque, SWT.NONE);
		lblFiltro.setSize(36, 15);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpTanque, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 13,
				1));
		tFiltro.setSize(439, 21);
		tFiltro.setMessage("Filtro de Busca!!");

		tvTanque = new TableViewer(grpTanque, SWT.BORDER | SWT.FULL_SELECTION);
		tvTanque.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvTanque
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (Tanque) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		table = tvTanque.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 14, 1));
		table.setSize(531, 91);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		tvTanque.addFilter(filtro);
		tvTanque.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Tanque) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(41);
		tblclmnId.setText("Id");

		tvcNome = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Tanque) element).getNome();
			}
		});
		TableColumn tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(54);
		tblclmnNome.setText("Nome");

		tvcLaminaAgua = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcLaminaAgua.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((Tanque) element).getLaminaAgua());
			}
		});
		TableColumn tblclmnLaminaDeAgua = tvcLaminaAgua.getColumn();
		tblclmnLaminaDeAgua.setWidth(97);
		tblclmnLaminaDeAgua.setText("Lamina de \u00C1gua");

		tvcProfundidade = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcProfundidade.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((Tanque) element).getProfundidade());
			}
		});
		TableColumn tblclmnProfundidade = tvcProfundidade.getColumn();
		tblclmnProfundidade.setWidth(86);
		tblclmnProfundidade.setText("Profundidade");

		tvcAcessibilidade = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcAcessibilidade.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Tanque) element).getAcessibilidade();
			}
		});
		TableColumn tblclmnAcessibilidade = tvcAcessibilidade.getColumn();
		tblclmnAcessibilidade.setWidth(90);
		tblclmnAcessibilidade.setText("Acessibilidade");

		tvcDescricao = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcDescricao.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Tanque) element).getDescricao();
			}
		});
		TableColumn tblclmnDescricao = tvcDescricao.getColumn();
		tblclmnDescricao.setWidth(63);
		tblclmnDescricao.setText("Descricao");

		tvcTipoTanque = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcTipoTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Tanque t = (Tanque) element;

				return t.getTipoTanqueId() == null ? "" : t.getTipoTanqueId()
						.getNome();
			}
		});
		TableColumn tblclmnTipoDeTanque = tvcTipoTanque.getColumn();
		tblclmnTipoDeTanque.setWidth(95);
		tblclmnTipoDeTanque.setText("Tipo de Tanque");

		tvcSistemaProducao = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcSistemaProducao.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Tanque t = (Tanque) element;
				return t.getSistemaProducaoId() == null ? "" : t
						.getSistemaProducaoId().getSistemaProducao();
			}
		});
		TableColumn tblclmnSistemaProducao = tvcSistemaProducao.getColumn();
		tblclmnSistemaProducao.setWidth(124);
		tblclmnSistemaProducao.setText("Sistema de Produ\u00E7\u00E3o");

	}

}
