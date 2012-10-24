package GUI;

import modelo.Tanque;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class ClassificacaoLote extends TelaEdicaoGUI<Tanque>{

	public ClassificacaoLote(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void excluir() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void salvar() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validar() throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void carregar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limparDados() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carregarComponentes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEntidadeNula() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		composite.setLayout(new GridLayout(1, false));
		
		Group grpClassificaoDeLotes = new Group(composite, SWT.NONE);
		grpClassificaoDeLotes.setLayout(new GridLayout(1, false));
		grpClassificaoDeLotes.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpClassificaoDeLotes.setText("Classifica\u00E7\u00E3o de Lotes");
		
		CTabFolder tabFolder = new CTabFolder(grpClassificaoDeLotes, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmJuntarLotes = new CTabItem(tabFolder, SWT.NONE);
		tbtmJuntarLotes.setText("Juntar lotes");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmJuntarLotes.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		Label lblTeste = new Label(composite_1, SWT.NONE);
		lblTeste.setText("teste");
		
		CTabItem tbtmDividirLotes = new CTabItem(tabFolder, SWT.NONE);
		tbtmDividirLotes.setText("Dividir lotes");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmDividirLotes.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
		Label lblTesteAsdfsdagag = new Label(composite_2, SWT.NONE);
		lblTesteAsdfsdagag.setText("teste asdfsdagag");
		// TODO Auto-generated method stub
		
	}
}
