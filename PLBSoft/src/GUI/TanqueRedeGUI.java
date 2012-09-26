package GUI;

import helper.StatusHelper;

import java.math.BigDecimal;

import modelo.TanqueRede;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.TanqueRedeService;
import org.eclipse.swt.widgets.Combo;

public class TanqueRedeGUI extends TelaEdicaoGUI {
	private Text tNome;
	private Text tTamanho;

	TanqueRedeService tanqueRede = new TanqueRedeService();
	private TanqueRede entidade;
	private Table table;
	private TableColumn tblclmnNome;
	private TableColumn tblclmnTamanhoM;
	private Label lblTanque;
	private Combo combo;

	public TanqueRedeGUI(Composite parent, int style) {
		super(parent, style);
		entidade = new TanqueRede();
	}

	@Override
	public void excluir() {
		try {
			entidade.setStatus(false);
			tanqueRede.salvar(entidade);
			tNome.setText("");
			tTamanho.setText("");
			StatusHelper.mensagemInfo("Itens excluido");
		} catch (Exception e) {
			StatusHelper.mensagemError("Erro ao excluir!");
		}

	}

	@Override
	public void buscar() {
		if (tNome.getText() == null && tTamanho.getText() == null) {
			tanqueRede.buscarTodos();
			StatusHelper.mensagemInfo("Listar todos os cadastrados!");
		} else if (tNome != null) {
			try {
				entidade.setNome(tNome.getText());
				entidade = tanqueRede.buscar(entidade.getNome());
				tNome.setText(entidade.getNome());
				tTamanho.setText(entidade.getTamanho().toString());
				StatusHelper.mensagemInfo("Busca Realizada");
			} catch (Exception e) {
				StatusHelper.mensagemWarning("Busca nao realizada!");
			}
		} else
			StatusHelper.mensagemError("Erro na busa de dados");
	}

	@Override
	public void salvar() {
		try {
			entidade.setNome(tNome.getText());
			entidade.setTamanho(new BigDecimal(tTamanho.getText()));
			entidade.setStatus(true);
			tanqueRede.salvar(entidade);
			StatusHelper.mensagemInfo("Cadastro Realizado!");
		} catch (Exception e) {
			StatusHelper.mensagemWarning("Erro de cadastro");
		}

	}

	@Override
	public void adicionarComponentes(Composite composite) {
		composite.setLayout(new GridLayout(2, false));

		Label lblNome = new Label(composite, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblNome.setText("Nome:");

		tNome = new Text(composite, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		Label lblTamanhaM = new Label(composite, SWT.NONE);
		lblTamanhaM.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblTamanhaM.setText("Tamanha m\u00B3:");

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

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tblclmnNome = new TableColumn(table, SWT.NONE);
		tblclmnNome.setWidth(100);
		tblclmnNome.setText("Nome");

		tblclmnTamanhoM = new TableColumn(table, SWT.NONE);
		tblclmnTamanhoM.setWidth(104);
		tblclmnTamanhoM.setText("Tamanho m\u00B3");
	}

}
