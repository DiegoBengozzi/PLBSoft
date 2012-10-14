package GUI;

import modelo.Especie;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;

public class EspecieGUI extends TelaEdicaoGUI<Especie>{
	private Text tEspecie;
	private Text tLinhagem;
	private Text tHibrido;
	private Text tGenero;
	private Text text;
	private Table table;
	private Text text_1;

	public EspecieGUI(Composite parent, int style) {
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
		composite.setLayout(new GridLayout(5, false));
		
		Group grpEspcie = new Group(composite, SWT.NONE);
		grpEspcie.setLayout(new GridLayout(6, false));
		grpEspcie.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		grpEspcie.setText("Esp\u00E9cie");
		
		Label lblEspecie = new Label(grpEspcie, SWT.NONE);
		lblEspecie.setText("Esp\u00E9cie:");
		
		tEspecie = new Text(grpEspcie, SWT.BORDER);
		tEspecie.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 5, 1));
		
		Label lblLinhagem = new Label(grpEspcie, SWT.NONE);
		lblLinhagem.setText("Linhagem:");
		
		tLinhagem = new Text(grpEspcie, SWT.BORDER);
		tLinhagem.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 5, 1));
		
		Label lblHibrdo = new Label(grpEspcie, SWT.NONE);
		lblHibrdo.setText("H\u00EDbrido:");
		
		tHibrido = new Text(grpEspcie, SWT.BORDER);
		tHibrido.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 5, 1));
		
		Label lblGenero = new Label(grpEspcie, SWT.NONE);
		lblGenero.setText("G\u00E9nero:");
		
		tGenero = new Text(grpEspcie, SWT.BORDER);
		tGenero.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 5, 1));
		
		Label lblMaturaoSexual = new Label(grpEspcie, SWT.NONE);
		lblMaturaoSexual.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblMaturaoSexual.setText("Matura\u00E7\u00E3o Sexual:");
		
		text = new Text(grpEspcie, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));
		
		Label lblToleranciaASalinidade = new Label(grpEspcie, SWT.NONE);
		lblToleranciaASalinidade.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblToleranciaASalinidade.setText("Tolerancia a Salinidade:");
		
		Button btnBaixoSalinidade = new Button(grpEspcie, SWT.RADIO);
		btnBaixoSalinidade.setText("Baixo");
		
		Button btnMedioSalinidade = new Button(grpEspcie, SWT.RADIO);
		btnMedioSalinidade.setText("M\u00E9dio");
		
		Button btnAltoSalidade = new Button(grpEspcie, SWT.RADIO);
		btnAltoSalidade.setText("Alto");
		
		Label label = new Label(grpEspcie, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_label = new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1);
		gd_label.widthHint = 74;
		label.setLayoutData(gd_label);
		
		Label lblToleranciaAFrio = new Label(grpEspcie, SWT.NONE);
		lblToleranciaAFrio.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblToleranciaAFrio.setText("Tolerancia a Frio:");
		
		Button btnBaixoFrio = new Button(grpEspcie, SWT.RADIO);
		btnBaixoFrio.setText("Baixo");
		
		Button btnMedioFrio = new Button(grpEspcie, SWT.RADIO);
		btnMedioFrio.setText("M\u00E9dio");
		
		Button btnAltoFrio = new Button(grpEspcie, SWT.RADIO);
		btnAltoFrio.setText("Alto");
		new Label(grpEspcie, SWT.NONE);
		
		Label lblFiltro = new Label(grpEspcie, SWT.NONE);
		lblFiltro.setText("Filtro:");
		
		text_1 = new Text(grpEspcie, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 5, 1));
		
		TableViewer tvEspecie = new TableViewer(grpEspcie, SWT.BORDER | SWT.FULL_SELECTION);
		table = tvEspecie.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableViewerColumn tvcId = new TableViewerColumn(tvEspecie, SWT.NONE);
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(39);
		tblclmnId.setText("Id");
		
		TableViewerColumn tvcEspecie = new TableViewerColumn(tvEspecie, SWT.NONE);
		TableColumn tblclmnEspecie = tvcEspecie.getColumn();
		tblclmnEspecie.setWidth(57);
		tblclmnEspecie.setText("Esp\u00E9cie");
		
		TableViewerColumn tvcLinhagem = new TableViewerColumn(tvEspecie, SWT.NONE);
		TableColumn tblclmnLinhagem = tvcLinhagem.getColumn();
		tblclmnLinhagem.setWidth(73);
		tblclmnLinhagem.setText("Linhagem");
		
		TableViewerColumn tvcHibrido = new TableViewerColumn(tvEspecie, SWT.NONE);
		TableColumn tblclmnHibrido = tvcHibrido.getColumn();
		tblclmnHibrido.setWidth(52);
		tblclmnHibrido.setText("H\u00EDbrido");
		
		TableViewerColumn tvcGenero = new TableViewerColumn(tvEspecie, SWT.NONE);
		TableColumn tblclmnGenero = tvcGenero.getColumn();
		tblclmnGenero.setWidth(54);
		tblclmnGenero.setText("G\u00E9nero");
		
		TableViewerColumn tvcMaturacaoSexual = new TableViewerColumn(tvEspecie, SWT.NONE);
		TableColumn tblclmnMaturacaoSexual = tvcMaturacaoSexual.getColumn();
		tblclmnMaturacaoSexual.setWidth(100);
		tblclmnMaturacaoSexual.setText("Matura\u00E7\u00E3o Sexual");
		
		TableViewerColumn tvcSalinidade = new TableViewerColumn(tvEspecie, SWT.NONE);
		TableColumn tblclmnToleranciaASalinidade = tvcSalinidade.getColumn();
		tblclmnToleranciaASalinidade.setWidth(100);
		tblclmnToleranciaASalinidade.setText("Tolerancia a Salinidade");
		
		TableViewerColumn tvcFrio = new TableViewerColumn(tvEspecie, SWT.NONE);
		TableColumn tblclmnToleranciaAFrio = tvcFrio.getColumn();
		tblclmnToleranciaAFrio.setWidth(100);
		tblclmnToleranciaAFrio.setText("Tolerancia a Frio");
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
