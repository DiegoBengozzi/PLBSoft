package GUI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class TanqueGUI extends TelaEdicaoGUI{

	public TanqueGUI(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void excluir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNome = new Label(composite, SWT.NONE);
		lblNome.setText("Nome:");
		
		Label lblLaminaDegua = new Label(composite, SWT.NONE);
		lblLaminaDegua.setText("Lamina de \u00E1gua:");
		
		Label lblProfundidade = new Label(composite, SWT.NONE);
		lblProfundidade.setText("Profundidade:");
		
		Label lblAcessibilidade = new Label(composite, SWT.NONE);
		lblAcessibilidade.setText("Acessibilidade:");
		
		Label lblDescrio = new Label(composite, SWT.NONE);
		lblDescrio.setText("Descri\u00E7\u00E3o:");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carregar() {
		// TODO Auto-generated method stub
		
	}

}
