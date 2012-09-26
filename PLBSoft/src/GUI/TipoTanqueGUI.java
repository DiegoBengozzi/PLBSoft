package GUI;

import helper.StatusHelper;
import modelo.TipoTanque;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import service.TipoTanqueService;

public class TipoTanqueGUI extends TelaEdicaoGUI {
	
	TipoTanqueService tipoTanque = new TipoTanqueService();
	private TipoTanque entidade;
	private Text tNome;
	private Text tRevestimento;

	public TipoTanqueGUI(Composite parent, int style) {
		super(parent, style);
		entidade = new TipoTanque();
	}

	@Override
	public void excluir() {
		StatusHelper.mensagemError("Excuir nao implementado");
	}

	@Override
	public void buscar() {
		StatusHelper.mensagemError("Buscar nao implementado");
	}

	@Override
	public void salvar() {
		try {
			entidade.setNome(tNome.getText());
			entidade.setRevestimento(tRevestimento.getText());
			entidade.setStatus(true);
			tipoTanque.salvar(entidade);
			StatusHelper.mensagemInfo("Cadastro Realizado!");
		} catch (Exception e) {
			StatusHelper.mensagemWarning("Erro de cadastro");
		}		
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		composite.setLayout(new GridLayout(2, false));
		
		Label lblNome = new Label(composite, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome:");
		
		tNome = new Text(composite, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		
		Label lblRevestimento = new Label(composite, SWT.NONE);
		lblRevestimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRevestimento.setText("Revestimento:");
		
		tRevestimento = new Text(composite, SWT.BORDER);
		tRevestimento.setText("");
		tRevestimento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// TODO Auto-generated method stub
		
	}
}
