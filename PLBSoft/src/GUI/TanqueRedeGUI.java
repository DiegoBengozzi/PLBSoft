package GUI;

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

public class TanqueRedeGUI extends TelaEdicaoGUI {
	private Text tNome;
	private Text tTamanho;

	private TanqueRedeService tanqueRedeService = new TanqueRedeService();
	private TanqueRede entidade;
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

	public TanqueRedeGUI(Composite parent, int style) {
		super(parent, style);
		entidade = new TanqueRede();
	}
	@Override
	public void carregar(){
		tvTanqueRede.setInput(tanqueRedeService.buscarTodos());
		tvTanqueRede.refresh();
	}

	@Override
	public void excluir() {
		// try {
		// entidade.setStatus(false);
		// tanqueRede.salvar(entidade);
		// tNome.setText("");
		// tTamanho.setText("");
		// StatusHelper.mensagemInfo("Itens excluido");
		// } catch (Exception e) {
		// StatusHelper.mensagemError("Erro ao excluir!");
		// }
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvTanqueRede.refresh();
	}

	@Override
	public void salvar() {
//		if (tNome.getText() == null || tTamanho.getText() == null
//				|| tNome.getText().equalsIgnoreCase("")
//				|| tTamanho.getText().equalsIgnoreCase("")) {
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
//		} else
//			mensagemWarning("Informe todos os dados pra o cadastro!");
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new TanqueRedeFiltro();

		composite.setLayout(new GridLayout(2, false));

		Label lblNome = new Label(composite, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblNome.setText("Nome:");

		tNome = new Text(composite, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		Label lblTamanho = new Label(composite, SWT.NONE);
		lblTamanho.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblTamanho.setText("Tamanha m\u00B3:");

		tTamanho = new Text(composite, SWT.BORDER);
		tTamanho.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		lblTanque = new Label(composite, SWT.NONE);
		lblTanque.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblTanque.setText("Tanque:");

		combo = new Combo(composite, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		lblFiltro = new Label(composite, SWT.NONE);
		lblFiltro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblFiltro.setText("Filtro...");

		tFiltro = new Text(composite, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		tFiltro.setMessage("Filtro de Buscar!!!");

		tvTanqueRede = new TableViewer(composite, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tvTanqueRede.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tvTanqueRede.addFilter(filtro);
		tvTanqueRede.setContentProvider(ArrayContentProvider.getInstance());

		tvcNome = new TableViewerColumn(tvTanqueRede, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((TanqueRede)element).getNome();
			}
		});
		tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(100);
		tblclmnNome.setText("Nome");

		tvcTamanho = new TableViewerColumn(tvTanqueRede, SWT.NONE);
		tvcTamanho.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(((TanqueRede)element).getTamanho());
			}
		});
		tblclmnTamanho = tvcTamanho.getColumn();
		tblclmnTamanho.setWidth(100);
		tblclmnTamanho.setText("Tamanho m\u00B3");

	}
	
}
