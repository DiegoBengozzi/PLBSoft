package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AdubacaoGUI extends TelaEdicaoGUI {
	private Text tDescricao;
	private DateTime dateTime;

	public AdubacaoGUI(Composite parent, int style) {
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
		composite.setLayout(new GridLayout(2, false));
		
		Label lblDescrioDaAdubao = new Label(composite, SWT.NONE);
		lblDescrioDaAdubao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrioDaAdubao.setText("Descri\u00E7\u00E3o \r\n      da \r\naduba\u00E7\u00E3o:");
		
		tDescricao = new Text(composite, SWT.BORDER);
		tDescricao.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblData = new Label(composite, SWT.NONE);
		lblData.setText("Data:");
		
		setDateTime(new DateTime(composite, SWT.BORDER));
		
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public void carregar() {
		// TODO Auto-generated method stub
		
	}
}
