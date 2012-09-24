package GUI;

import org.eclipse.swt.widgets.Composite;

public abstract class TelaEdicaoAbstract{
	public void telaEdicao(){
		new TelaEdicaoGUI(null, 0);
	}
	
	public abstract void novo();
	public abstract void buscar();
	public abstract void salvar();
	public abstract void voltar();
	
	public abstract void adicionarComponentes(Composite composite);	
	
}


