package GUI;

import helper.FormatoHelper;

import java.math.BigDecimal;

import modelo.SistemaProducao;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.SistemaProducaoService;
import filtro.SistemaProducaoFiltro;

public class SistemaProducaoGUI extends TelaEdicaoGUI<SistemaProducao> {
	private Text tSistemaCultivo;
	private Text tBiomassaEconomica;
	private Text tBiomassaCritica;
	private Text tCapacidadeSuporte;
	private Text tFiltro;
	private Table table;
	private SistemaProducaoFiltro filtro;
	private TableViewer tvSistemaProducao;
	private SistemaProducaoService sistemaProducaoService = new SistemaProducaoService();
	private TableViewerColumn tvcId, tvcSistemaCultivo, tvcBiomassaEconomica,
			tvcBiomassaCritica, tvcCapacidadeSuporte;

	public SistemaProducaoGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() {
		entidade.setStatus(false);
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText().trim());
		tvSistemaProducao.refresh();
	}

	@Override
	public void salvar() {
		if (entidade == null)
			entidade = new SistemaProducao();
		entidade.setSistemaProducao(tSistemaCultivo.getText().trim());
		entidade.setBiomasaCritica(new BigDecimal(tBiomassaCritica.getText().trim().replaceAll(",", ".")));
		entidade.setBiomassaEconomica(new BigDecimal(tBiomassaEconomica.getText().trim().replaceAll(",", ".")));
		entidade.setCapacidadeSuporte(new BigDecimal(tCapacidadeSuporte.getText().trim().replaceAll(",", ".")));
		entidade.setStatus(true);
		sistemaProducaoService.salvar(entidade);
	}

	@Override
	public void validar() throws Exception {

	}

	@Override
	public void carregar() {
		tvSistemaProducao.setInput(sistemaProducaoService.buscarTodosSistemaProducaoAtivo());
		tvSistemaProducao.refresh();

	}

	@Override
	public void limparDados() {
		tSistemaCultivo.setText("");
		tCapacidadeSuporte.setText("");
		tBiomassaCritica.setText("");
		tBiomassaEconomica.setText("");
		tFiltro.setText("");
		entidade=null;
		

	}

	@Override
	public void carregarComponentes() {
		tSistemaCultivo.setText(entidade.getSistemaProducao());
		tBiomassaCritica.setText(FormatoHelper.getDecimalFormato().format(entidade.getBiomasaCritica()));
		tBiomassaEconomica.setText(FormatoHelper.getDecimalFormato().format(entidade.getBiomassaEconomica()));
		tCapacidadeSuporte.setText(FormatoHelper.getDecimalFormato().format(entidade.getCapacidadeSuporte()));
	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new SistemaProducaoFiltro();
		composite.setLayout(new GridLayout(2, false));

		Group grpSistemaDeProduo = new Group(composite, SWT.NONE);
		grpSistemaDeProduo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1));
		grpSistemaDeProduo.setText("Sistema de Produ\u00E7\u00E3o");
		grpSistemaDeProduo.setLayout(new GridLayout(2, false));

		Label lblSistemaDeProduo = new Label(grpSistemaDeProduo, SWT.NONE);
		lblSistemaDeProduo.setText("Sistema de Cultivo:");

		tSistemaCultivo = new Text(grpSistemaDeProduo, SWT.BORDER);
		tSistemaCultivo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));

		Label lblBiomassaEconomica = new Label(grpSistemaDeProduo, SWT.NONE);
		lblBiomassaEconomica.setText("Biomassa Economica:");

		tBiomassaEconomica = new Text(grpSistemaDeProduo, SWT.BORDER);
		tBiomassaEconomica.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));

		Label lblBiomassaCritica = new Label(grpSistemaDeProduo, SWT.NONE);
		lblBiomassaCritica.setText("Biomassa Critica:");

		tBiomassaCritica = new Text(grpSistemaDeProduo, SWT.BORDER);
		tBiomassaCritica.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));

		Label lblCapacidadeDeSuporte = new Label(grpSistemaDeProduo, SWT.NONE);
		lblCapacidadeDeSuporte.setText("Capacidade de Suporte:");

		tCapacidadeSuporte = new Text(grpSistemaDeProduo, SWT.BORDER);
		tCapacidadeSuporte.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));

		Label lblFiltro = new Label(grpSistemaDeProduo, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpSistemaDeProduo, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		tFiltro.setMessage("Filtro de Busca!!");

		tvSistemaProducao = new TableViewer(grpSistemaDeProduo, SWT.BORDER
				| SWT.FULL_SELECTION);
		tvSistemaProducao.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvSistemaProducao.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (SistemaProducao) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		table = tvSistemaProducao.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tvSistemaProducao.addFilter(filtro);
		tvSistemaProducao.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvSistemaProducao, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((SistemaProducao) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(41);
		tblclmnId.setText("Id");

		tvcSistemaCultivo = new TableViewerColumn(tvSistemaProducao, SWT.NONE);
		tvcSistemaCultivo.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((SistemaProducao) element).getSistemaProducao();
			}
		});
		TableColumn tblclmnSistemaDeCultivo = tvcSistemaCultivo.getColumn();
		tblclmnSistemaDeCultivo.setWidth(116);
		tblclmnSistemaDeCultivo.setText("Sistema de Cultivo");

		tvcBiomassaEconomica = new TableViewerColumn(tvSistemaProducao,
				SWT.NONE);
		tvcBiomassaEconomica.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((SistemaProducao) element).getBiomassaEconomica());
			}
		});
		TableColumn tblclmnBiomassaEconomica = tvcBiomassaEconomica.getColumn();
		tblclmnBiomassaEconomica.setWidth(126);
		tblclmnBiomassaEconomica.setText("Biomassa Economica");

		tvcBiomassaCritica = new TableViewerColumn(tvSistemaProducao, SWT.NONE);
		tvcBiomassaCritica.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(((SistemaProducao)element).getBiomasaCritica());
			}
		});
		TableColumn tblclmnBiomassaCritica = tvcBiomassaCritica.getColumn();
		tblclmnBiomassaCritica.setWidth(100);
		tblclmnBiomassaCritica.setText("Biomassa Critica");

		tvcCapacidadeSuporte = new TableViewerColumn(tvSistemaProducao,
				SWT.NONE);
		tvcCapacidadeSuporte.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(((SistemaProducao)element).getCapacidadeSuporte());
			}
		});
		TableColumn tblclmnCapacidadeDeSuporte = tvcCapacidadeSuporte
				.getColumn();
		tblclmnCapacidadeDeSuporte.setWidth(138);
		tblclmnCapacidadeDeSuporte.setText("Capacidade de Suporte");

	}

}
