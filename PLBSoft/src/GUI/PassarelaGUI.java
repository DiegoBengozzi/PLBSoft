package GUI;

import modelo.Passarela;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;

public class PassarelaGUI extends TelaEdicaoGUI<Passarela>{
	private Text tNome;
	private Text tCapacidade;
	private Table table;
	private Text tFiltro;

	public PassarelaGUI(Composite parent, int style) {
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
	public void validar() throws Exception {

	}

	@Override
	public void adicionarComponentes(Composite composite) {
		composite.setLayout(new GridLayout(1, false));
		
		Group grpPassarela = new Group(composite, SWT.NONE);
		grpPassarela.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpPassarela.setText("Passarela");
		grpPassarela.setLayout(new GridLayout(2, false));
		
		Label lblNome = new Label(grpPassarela, SWT.NONE);
		lblNome.setText("Nome:");
		
		tNome = new Text(grpPassarela, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblCapacidadeDeHapas = new Label(grpPassarela, SWT.NONE);
		lblCapacidadeDeHapas.setText("Capacidade de Hapa:");
		
		tCapacidade = new Text(grpPassarela, SWT.BORDER);
		tCapacidade.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblTanque = new Label(grpPassarela, SWT.NONE);
		lblTanque.setText("Tanque:");
		
		ComboViewer comboViewer = new ComboViewer(grpPassarela, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblFiltro = new Label(grpPassarela, SWT.NONE);
		lblFiltro.setText("Filtro:");
		
		tFiltro = new Text(grpPassarela, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		TableViewer tvPassarela = new TableViewer(grpPassarela, SWT.BORDER | SWT.FULL_SELECTION);
		table = tvPassarela.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tvPassarela, SWT.NONE);
		TableColumn tblclmnId = tableViewerColumn.getColumn();
		tblclmnId.setWidth(41);
		tblclmnId.setText("Id");
		
		TableViewerColumn tvcNome = new TableViewerColumn(tvPassarela, SWT.NONE);
		TableColumn tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(100);
		tblclmnNome.setText("Nome");
		
		TableViewerColumn tvcCapacidadeHapa = new TableViewerColumn(tvPassarela, SWT.NONE);
		TableColumn tblclmnCapacidadeDeHapa = tvcCapacidadeHapa.getColumn();
		tblclmnCapacidadeDeHapa.setWidth(100);
		tblclmnCapacidadeDeHapa.setText("Capacidade de Hapa");
		
		TableViewerColumn tvcTanqueId = new TableViewerColumn(tvPassarela, SWT.NONE);
		TableColumn tblclmnTanque = tvcTanqueId.getColumn();
		tblclmnTanque.setWidth(100);
		tblclmnTanque.setText("Tanque");
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

}
