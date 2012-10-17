package GUI;

import static helper.StatusHelper.mensagemError;
import modelo.SistemaProducao;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Group;

public class SistemaProducaoGUI extends TelaEdicaoGUI<SistemaProducao>{
	private Text tSistemaCultivo;
	private Text tBiomassaEconomica;
	private Text tBiomassaCritica;
	private Text tCapacidadeSuporte;
	private Text tFiltro;
	private Table table;

	public SistemaProducaoGUI(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void excluir() {
		mensagemError("puta ki pariu de rodinhaaa!!");
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
		composite.setLayout(new GridLayout(2, false));
		
		Group grpSistemaDeProduo = new Group(composite, SWT.NONE);
		grpSistemaDeProduo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		grpSistemaDeProduo.setText("Sistema de Produ\u00E7\u00E3o");
		grpSistemaDeProduo.setLayout(new GridLayout(2, false));
		
		Label lblSistemaDeProduo = new Label(grpSistemaDeProduo, SWT.NONE);
		lblSistemaDeProduo.setText("Sistema de Cultivo:");
		
		tSistemaCultivo = new Text(grpSistemaDeProduo, SWT.BORDER);
		tSistemaCultivo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblBiomassaEconomica = new Label(grpSistemaDeProduo, SWT.NONE);
		lblBiomassaEconomica.setText("Biomassa Economica:");
		
		tBiomassaEconomica = new Text(grpSistemaDeProduo, SWT.BORDER);
		tBiomassaEconomica.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblBiomassaCritica = new Label(grpSistemaDeProduo, SWT.NONE);
		lblBiomassaCritica.setText("Biomassa Critica:");
		
		tBiomassaCritica = new Text(grpSistemaDeProduo, SWT.BORDER);
		tBiomassaCritica.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblCapacidadeDeSuporte = new Label(grpSistemaDeProduo, SWT.NONE);
		lblCapacidadeDeSuporte.setText("Capacidade de Suporte:");
		
		tCapacidadeSuporte = new Text(grpSistemaDeProduo, SWT.BORDER);
		tCapacidadeSuporte.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblFiltro = new Label(grpSistemaDeProduo, SWT.NONE);
		lblFiltro.setText("Filtro:");
		
		tFiltro = new Text(grpSistemaDeProduo, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		tFiltro.setMessage("Filtro de Busca!!");
		
		TableViewer tvSistemaProducao = new TableViewer(grpSistemaDeProduo, SWT.BORDER | SWT.FULL_SELECTION);
		table = tvSistemaProducao.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableViewerColumn tvcId = new TableViewerColumn(tvSistemaProducao, SWT.NONE);
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(41);
		tblclmnId.setText("Id");
		
		TableViewerColumn tvcSistemaCultivo = new TableViewerColumn(tvSistemaProducao, SWT.NONE);
		TableColumn tblclmnSistemaDeCultivo = tvcSistemaCultivo.getColumn();
		tblclmnSistemaDeCultivo.setWidth(116);
		tblclmnSistemaDeCultivo.setText("Sistema de Cultivo");
		
		TableViewerColumn tvcBiomassaEconomica = new TableViewerColumn(tvSistemaProducao, SWT.NONE);
		TableColumn tblclmnBiomassaEconomica = tvcBiomassaEconomica.getColumn();
		tblclmnBiomassaEconomica.setWidth(126);
		tblclmnBiomassaEconomica.setText("Biomassa Economica");
		
		TableViewerColumn tvcBiomassaCritica = new TableViewerColumn(tvSistemaProducao, SWT.NONE);
		TableColumn tblclmnBiomassaCritica = tvcBiomassaCritica.getColumn();
		tblclmnBiomassaCritica.setWidth(100);
		tblclmnBiomassaCritica.setText("Biomassa Critica");
		
		TableViewerColumn tvcCapacidadeSuporte = new TableViewerColumn(tvSistemaProducao, SWT.NONE);
		TableColumn tblclmnCapacidadeDeSuporte = tvcCapacidadeSuporte.getColumn();
		tblclmnCapacidadeDeSuporte.setWidth(138);
		tblclmnCapacidadeDeSuporte.setText("Capacidade de Suporte");
		// TODO Auto-generated method stub
		
	}

}
