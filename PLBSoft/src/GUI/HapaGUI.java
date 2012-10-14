package GUI;

import modelo.Hapa;

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

public class HapaGUI extends TelaEdicaoGUI<Hapa>{
	private Text tNome;
	private Text tTamanho;
	private Text tFiltro;
	private Table table;

	public HapaGUI(Composite parent, int style) {
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
		
		Group grpHapa = new Group(composite, SWT.NONE);
		grpHapa.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpHapa.setText("Hapa");
		grpHapa.setLayout(new GridLayout(2, false));
		
		Label lblNome = new Label(grpHapa, SWT.NONE);
		lblNome.setText("Nome:");
		
		tNome = new Text(grpHapa, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblTamanho = new Label(grpHapa, SWT.NONE);
		lblTamanho.setText("Tamanho:");
		
		tTamanho = new Text(grpHapa, SWT.BORDER);
		tTamanho.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblPassarela = new Label(grpHapa, SWT.NONE);
		lblPassarela.setText("Passarela:");
		
		ComboViewer cvPassarelaId = new ComboViewer(grpHapa, SWT.NONE);
		Combo combo = cvPassarelaId.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblFiltro = new Label(grpHapa, SWT.NONE);
		lblFiltro.setText("Filtro:");
		
		tFiltro = new Text(grpHapa, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TableViewer tableViewer = new TableViewer(grpHapa, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		TableViewerColumn tvcId = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(40);
		tblclmnId.setText("Id");
		
		TableViewerColumn tvcNome = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(100);
		tblclmnNome.setText("Nome");
		
		TableViewerColumn tvcTamanho = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnTamanho = tvcTamanho.getColumn();
		tblclmnTamanho.setWidth(100);
		tblclmnTamanho.setText("Tamanho");
		
		TableViewerColumn tvcPassarelaId = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPassarela = tvcPassarelaId.getColumn();
		tblclmnPassarela.setWidth(100);
		tblclmnPassarela.setText("Passarela");
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
