package GUI;

import static helper.StatusHelper.mensagemError;
import helper.FormatoHelper;

import java.math.BigDecimal;

import modelo.Tanque;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
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

import service.TanqueService;
import filtro.TanqueFiltro;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.DoubleClickEvent;

public class TanqueGUI extends TelaEdicaoGUI {
	private Table table;
	private Text tNome;
	private Text tLaminaAgua;
	private Text tProfundidade;
	private Text tAcessibilidade;
	private Text tDescricao;
	private Text tFiltro;
	private TableViewer tvTanque;
	private TableViewerColumn tvcNome, tvcLaminaAgua, tvcProfundidade,
			tvcAcessibilidade, tvcDescricao, tvcTipoTanque, tvcId;
	private TanqueService tanqueService = new TanqueService();
	private Tanque entidade;
	private TanqueFiltro filtro;

	public TanqueGUI(Composite parent, int style) {
		super(parent, style);
		entidade = new Tanque();
	}

	@Override
	public void excluir() {
		mensagemError("nao implementado");

	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvTanque.refresh();
	}

	@Override
	public void salvar() throws Exception{ 
		
			entidade.setNome(tNome.getText());
			entidade.setLaminaAgua(new BigDecimal(tLaminaAgua.getText().replaceAll(",", ".")));
			entidade.setProfundidade(new BigDecimal(tProfundidade.getText().replaceAll(",", ".")));
			entidade.setAcessibilidade(new Integer(tAcessibilidade.getText().replaceAll(",", ".")));
			entidade.setDescricao(tDescricao.getText());
			entidade.setStatus(true);
			tanqueService.salvar(entidade);
			entidade = new Tanque();

	}
	@Override
	public void limparDados(){
		tNome.setText("");
		tLaminaAgua.setText("");
		tProfundidade.setText("");
		tAcessibilidade.setText("");
		tDescricao.setText("");
	}

	@Override
	public void carregar() {
		tvTanque.setInput(tanqueService.buscarTodos());
		tvTanque.refresh();
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new TanqueFiltro();
		composite.setLayout(new GridLayout(2, false));

		Group grpTanque = new Group(composite, SWT.NONE);
		grpTanque.setLayout(new GridLayout(2, false));
		grpTanque.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2,
				1));
		grpTanque.setText("Tanque");
		Label lblNome = new Label(grpTanque, SWT.NONE);
		lblNome.setSize(36, 15);
		lblNome.setText("Nome:");

		tNome = new Text(grpTanque, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		tNome.setSize(439, 21);

		Label lblLaminaDegua = new Label(grpTanque, SWT.NONE);
		lblLaminaDegua.setSize(87, 15);
		lblLaminaDegua.setText("Lamina de \u00E1gua:");

		tLaminaAgua = new Text(grpTanque, SWT.BORDER);
		tLaminaAgua.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));
		tLaminaAgua.setSize(439, 21);

		Label lblProfundidade = new Label(grpTanque, SWT.NONE);
		lblProfundidade.setSize(75, 15);
		lblProfundidade.setText("Profundidade:");

		tProfundidade = new Text(grpTanque, SWT.BORDER);
		tProfundidade.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));
		tProfundidade.setSize(439, 21);
		tProfundidade.setText("");

		Label lblAcessibilidade = new Label(grpTanque, SWT.NONE);
		lblAcessibilidade.setSize(78, 15);
		lblAcessibilidade.setText("Acessibilidade:");

		tAcessibilidade = new Text(grpTanque, SWT.BORDER);
		tAcessibilidade.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));
		tAcessibilidade.setSize(439, 21);

		Label lblDescrio = new Label(grpTanque, SWT.NONE);
		lblDescrio.setSize(54, 15);
		lblDescrio.setText("Descri\u00E7\u00E3o:");

		tDescricao = new Text(grpTanque, SWT.BORDER | SWT.MULTI);
		GridData gd_tDescricao = new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1);
		gd_tDescricao.heightHint = 30;
		tDescricao.setLayoutData(gd_tDescricao);
		tDescricao.setSize(439, 50);

		Label lblTipoDeTanque = new Label(grpTanque, SWT.NONE);
		lblTipoDeTanque.setSize(86, 15);
		lblTipoDeTanque.setText("Tipo de Tanque:");

		Combo cTipoTanque = new Combo(grpTanque, SWT.NONE);
		cTipoTanque.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));
		cTipoTanque.setSize(439, 23);

		Label lblFiltro = new Label(grpTanque, SWT.NONE);
		lblFiltro.setSize(36, 15);
		lblFiltro.setText("Filtro...");

		tFiltro = new Text(grpTanque, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		tFiltro.setSize(439, 21);
		tFiltro.setMessage("filtro de Busca!!");

		tvTanque = new TableViewer(grpTanque, SWT.BORDER | SWT.FULL_SELECTION);
		tvTanque.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvTanque.getSelection();
				if(itemSelecao.isEmpty())
					return;
				entidade = (Tanque) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		table = tvTanque.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setSize(531, 91);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tvTanque.addFilter(filtro);
		tvTanque.setContentProvider(ArrayContentProvider.getInstance());
		
		tvcId = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Tanque)element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(29);
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
				return FormatoHelper.getDecimalFormato().format(
						((Tanque) element).getAcessibilidade());
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
				return "Nok"; // falta implementar
			}
		});
		TableColumn tblclmnTipoDeTanque = tvcTipoTanque.getColumn();
		tblclmnTipoDeTanque.setWidth(95);
		tblclmnTipoDeTanque.setText("Tipo de Tanque");
		// TODO Auto-generated method stub

	}

	@Override
	public void carregarComponentes() {
		tNome.setText(entidade.getNome());
		tLaminaAgua.setText(FormatoHelper.getDecimalFormato().format(entidade.getLaminaAgua()));
		tProfundidade.setText(FormatoHelper.getDecimalFormato().format(entidade.getProfundidade()));
		tAcessibilidade.setText(FormatoHelper.getDecimalFormato().format(entidade.getAcessibilidade()));
		tDescricao.setText(entidade.getDescricao());
	}

}
