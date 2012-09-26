package GUI;

import java.math.BigDecimal;

import modelo.TanqueRede;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import service.TanqueRedeService;

public class TanqueRedeGUI extends TelaEdicaoGUI {
	private Text tNome;
	private Text tTamanho;

	TanqueRedeService tanqueRede = new TanqueRedeService();
	private TanqueRede entidade;
	
	public TanqueRedeGUI(Composite parent, int style) {
		super(parent, style);
		entidade = new TanqueRede();
	}

	@Override
	public void excluir() {

	}

	@Override
	public void buscar() {

	}

	@Override
	public void salvar() {
		entidade.setNome(tNome.getText());
		entidade.setTamanho(new BigDecimal(tTamanho.getText()));
		tanqueRede.salvar(entidade);
		
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
		lblTamanhaM.setText("Tamanha m2:");

		tTamanho = new Text(composite, SWT.BORDER);
		tTamanho.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

	}

}
