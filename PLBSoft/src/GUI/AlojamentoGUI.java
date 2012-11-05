package GUI;

import modelo.Alojamento;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;

public class AlojamentoGUI extends TelaEdicaoGUI<Alojamento> {
	private Text tLote;
	private Table table;
	private Table table_1;
	private TableViewerColumn tableViewerColumn;

	public AlojamentoGUI(Composite parent, int style) {
		super(parent, style);
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
		
		Group grpAlojamentoDeLote = new Group(composite, SWT.NONE);
		grpAlojamentoDeLote.setLayout(new GridLayout(3, false));
		grpAlojamentoDeLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpAlojamentoDeLote.setText("Alojamento de Lote");
		
		Label lblLote = new Label(grpAlojamentoDeLote, SWT.NONE);
		lblLote.setText("Lote:");
		
		tLote = new Text(grpAlojamentoDeLote, SWT.BORDER);
		tLote.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Label lblTanque = new Label(grpAlojamentoDeLote, SWT.NONE);
		lblTanque.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblTanque.setText("Tanque:");
		new Label(grpAlojamentoDeLote, SWT.NONE);
		
		Label lblTaqueRede = new Label(grpAlojamentoDeLote, SWT.NONE);
		lblTaqueRede.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblTaqueRede.setText("Taque Rede:");
		new Label(grpAlojamentoDeLote, SWT.NONE);
		
		Label lblHapa = new Label(grpAlojamentoDeLote, SWT.NONE);
		lblHapa.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblHapa.setText("Hapa:");
		new Label(grpAlojamentoDeLote, SWT.NONE);
		
		Group grpLotes = new Group(grpAlojamentoDeLote, SWT.NONE);
		grpLotes.setLayout(new GridLayout(1, false));
		grpLotes.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		grpLotes.setText("Lotes");
		
		TableViewer tableViewer = new TableViewer(grpLotes, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnId = tableViewerColumn.getColumn();
		tblclmnId.setWidth(100);
		tblclmnId.setText("Id");
		
		Group grpAlojamentoDisponivel = new Group(grpAlojamentoDeLote, SWT.NONE);
		grpAlojamentoDisponivel.setLayout(new GridLayout(1, false));
		grpAlojamentoDisponivel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpAlojamentoDisponivel.setText("Alojamento Disponivel");
		
		TableViewer tableViewer_1 = new TableViewer(grpAlojamentoDisponivel, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		// TODO Auto-generated method stub
		
	}
}
