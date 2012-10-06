package GUI;

import static helper.StatusHelper.mensagemError;
import static helper.StatusHelper.mensagemInfo;
import static helper.StatusHelper.mensagemWarning;
import helper.FormatoHelper;

import java.math.BigDecimal;

import modelo.TanqueRede;

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

import service.TanqueRedeService;
import conexao.HibernateConnection;
import filtro.TanqueRedeFiltro;
import org.eclipse.swt.widgets.Group;

public class TanqueRedeGUI extends TelaEdicaoGUI<TanqueRede> {
	private Text tNome;
	private Text tTamanho;

	private Label lblTanque;
	private Combo combo;
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

	public TanqueRedeGUI(Composite parent, int style) {
		super(parent, style);
		entidade = new TanqueRede();
	}

	@Override
	public void carregar() {
		tvTanqueRede.setInput(tanqueRedeService.buscarTodosTanqueRedeAtivo());
		tvTanqueRede.refresh();
	}

	@Override
	public void excluir() {
		mensagemError("nao implementado");
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvTanqueRede.refresh();
	}

	@Override
	public void salvar() {
		if (tNome.getText() == null || tTamanho.getText() == null
				|| tNome.getText().equalsIgnoreCase("")
				|| tTamanho.getText().equalsIgnoreCase("")) {

			try {
				entidade.setNome(tNome.getText());
				entidade.setTamanho(new BigDecimal(tTamanho.getText()));
				entidade.setStatus(true);
				tanqueRedeService.salvar(entidade);
				HibernateConnection.commit();
				mensagemInfo("Cadastro Realizado!");
				carregar();
			} catch (Exception e) {
				mensagemWarning("Erro de cadastro");
			}
		} else
			mensagemWarning("Informe todos os dados pra o cadastro!");
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new TanqueRedeFiltro();

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

		combo = new Combo(grpTanqueRede, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		combo.setSize(196, 23);

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
		table = tvTanqueRede.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setSize(272, 77);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tvTanqueRede.addFilter(filtro);
		tvTanqueRede.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvTanqueRede, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((TanqueRede)element).getId().toString();
			}
		});
		tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(100);
		tblclmnId.setText("Id");

		tvcNome = new TableViewerColumn(tvTanqueRede, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((TanqueRede) element).getNome();
			}
		});
		tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(121);
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
		tblclmnTamanho.setWidth(227);
		tblclmnTamanho.setText("Tamanho m\u00B3");

	}

	@Override
	public void limparDados() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carregarComponentes() {
		// TODO Auto-generated method stub
		
	}

}
