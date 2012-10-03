package GUI;

import static helper.StatusHelper.mensagemInfo;
import static helper.StatusHelper.mensagemWarning;
import helper.FormatoHelper;

import java.math.BigDecimal;

import modelo.Tanque;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.TanqueService;
import conexao.HibernateConnection;
import filtro.TanqueFiltro;

public class TanqueGUI extends TelaEdicaoGUI {
	private Text tNome;
	private Text tLaminaAgua;
	private Text tProfundidade;
	private Text tAcessibilidade;
	private Text tDescricao;
	private Text tFiltro;
	private TableViewer tvTanque;
	private TanqueFiltro filtro;

	private TanqueService tanqueService = new TanqueService();
	private Tanque entidade;

	private Table table;

	public TanqueGUI(Composite parent, int style) {
		super(parent, style);
		entidade = new Tanque();
	}

	@Override
	public void excluir() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvTanque.refresh();
	}

	@Override
	public void salvar() {
		try {
			entidade.setNome(tNome.getText());
			entidade.setLaminaAgua(new BigDecimal(tLaminaAgua.getText()));
			entidade.setProfundidade(new BigDecimal(tProfundidade.getText()));
			entidade.setAcessibilidade(new Integer(tAcessibilidade.getText()));
			entidade.setDescricao(tDescricao.getText());
			entidade.setStatus(true);
			tanqueService.salvar(entidade);
			mensagemInfo("Cadastro Realizado!");
			HibernateConnection.commit();
		} catch (Exception e) {
			mensagemWarning("Erro de cadastro!" + e.getMessage());
		}

	}

	@Override
	public void carregar() {
		tvTanque.setInput(tanqueService.buscarTodos());
		tvTanque.refresh();

	}

	@Override
	public void adicionarComponentes(Composite composite) {
		composite.setLayout(new GridLayout(2, false));

		Label lblNome = new Label(composite, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblNome.setText("Nome:");

		tNome = new Text(composite, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblLaminaDegua = new Label(composite, SWT.NONE);
		lblLaminaDegua.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblLaminaDegua.setText("Lamina de \u00E1gua:");

		tLaminaAgua = new Text(composite, SWT.BORDER);
		tLaminaAgua.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblProfundidade = new Label(composite, SWT.NONE);
		lblProfundidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblProfundidade.setText("Profundidade:");

		tProfundidade = new Text(composite, SWT.BORDER);
		tProfundidade.setText("");
		tProfundidade.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblAcessibilidade = new Label(composite, SWT.NONE);
		lblAcessibilidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblAcessibilidade.setText("Acessibilidade:");

		tAcessibilidade = new Text(composite, SWT.BORDER);
		tAcessibilidade.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblDescrio = new Label(composite, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblDescrio.setText("Descri\u00E7\u00E3o:");

		tDescricao = new Text(composite, SWT.BORDER | SWT.MULTI);
		GridData gd_tDescricao = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1);
		gd_tDescricao.heightHint = 44;
		tDescricao.setLayoutData(gd_tDescricao);

		Label lblTipoDeTanque = new Label(composite, SWT.NONE);
		lblTipoDeTanque.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblTipoDeTanque.setText("Tipo de Tanque:");

		Combo cTipoTanque = new Combo(composite, SWT.NONE);
		cTipoTanque.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblFiltro = new Label(composite, SWT.NONE);
		lblFiltro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblFiltro.setText("Filtro...");

		tFiltro = new Text(composite, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		tFiltro.setMessage("filtro de Busca!!");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		tvTanque = new TableViewer(composite, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tvTanque.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tvTanque.addFilter(filtro);
		tvTanque.setContentProvider(ArrayContentProvider.getInstance());
		
		TableViewerColumn tvcNome = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Tanque)element).getNome();
			}
		});
		TableColumn tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(45);
		tblclmnNome.setText("Nome");

		TableViewerColumn tvcLaminaAgua = new TableViewerColumn(tvTanque,SWT.NONE);
		tvcLaminaAgua.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(((Tanque)element).getLaminaAgua());
			}
		});
		TableColumn tblclmnLaminaDeAgua = tvcLaminaAgua.getColumn();
		tblclmnLaminaDeAgua.setWidth(95);
		tblclmnLaminaDeAgua.setText("Lamina de Agua");

		TableViewerColumn tvcProfundidade = new TableViewerColumn(tvTanque,
				SWT.NONE);
		TableColumn tblclmnProfundidade = tvcProfundidade.getColumn();
		tblclmnProfundidade.setWidth(85);
		tblclmnProfundidade.setText("Profundidade");

		TableViewerColumn tvcAcessibilidade = new TableViewerColumn(tvTanque, SWT.NONE);
		tvcAcessibilidade.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(((Tanque)element).getAcessibilidade());
			}
		});
		TableColumn tblclmnAcessibilidade = tvcAcessibilidade.getColumn();
		tblclmnAcessibilidade.setWidth(85);
		tblclmnAcessibilidade.setText("Acessibilidade");

		TableViewerColumn tvcDescricao = new TableViewerColumn(tvTanque,
				SWT.NONE);
		tvcDescricao.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Tanque)element).getDescricao();
			}
		});
		TableColumn tblclmnDescricao = tvcDescricao.getColumn();
		tblclmnDescricao.setWidth(61);
		tblclmnDescricao.setText("Descricao");

		TableViewerColumn tvcTipoTanque = new TableViewerColumn(tvTanque,
				SWT.NONE);
		TableColumn tblclmnTipoDeTanque = tvcTipoTanque.getColumn();
		tblclmnTipoDeTanque.setWidth(100);
		tblclmnTipoDeTanque.setText("Tipo de Tanque");
		// TODO Auto-generated method stub

	}

}
