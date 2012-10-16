package GUI;

import helper.FormatoHelper;

import java.math.BigDecimal;

import modelo.Especie;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.EspecieService;
import filtro.EspecieFiltro;

public class EspecieGUI extends TelaEdicaoGUI<Especie> {
	private Text tEspecie;
	private Text tLinhagem;
	private Text tHibrido;
	private Text tGenero;
	private Text tMaturacaoSexual;
	private Table table;
	private Text tFiltro;
	private EspecieFiltro filtro;
	private EspecieService especieService;
	private Button rbBaixoSalinidade, rbMedioSalinidade, rbAltoSalidade,
			rbBaixoFrio, rbMedioFrio, rbAltoFrio;
	private TableViewerColumn tvcId, tvcEspecie, tvcLinhagem, tvcHibrido,
			tvcGenero, tvcMaturacaoSexual, tvcSalinidade, tvcFrio;
	private TableViewer tvEspecie;

	public EspecieGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() {
		entidade.setStatus(false);
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvEspecie.refresh();
	}

	@Override
	public void salvar() {
		if (entidade == null)
			entidade = new Especie();
		
		entidade.setEspecie(tEspecie.getText().trim());
		entidade.setGenero(tGenero.getText().trim());
		entidade.setHibrido(tHibrido.getText().trim());
		entidade.setLinhegem(tLinhagem.getText().trim());
		entidade.setMaturacaoSexual(new BigDecimal(tMaturacaoSexual.getText().trim().replaceAll(",", ".")));
		entidade.setStatus(true);
		entidade.setToleranciaFrio(getValorFrio().trim());
		entidade.setToleranciaSalinidade(getValorSalinidade());
		especieService.salvar(entidade);
	}

	@Override
	public void validar() throws Exception {

	}


	@Override
	public void limparDados() {
		tEspecie.setText("");
		tGenero.setText("");
		tHibrido.setText("");
		tLinhagem.setText("");
		tMaturacaoSexual.setText("");
		setValorFrio("");
		setValorSalinidade("");
		entidade = null;
	}
	
	@Override
	public void carregar() {
		tvEspecie.setInput(especieService.buscarTodosEspecieAtivo());
		tvEspecie.refresh();
	}

	@Override
	public void carregarComponentes() {
		tEspecie.setText(entidade.getEspecie());
		tGenero.setText(entidade.getGenero());
		tHibrido.setText(entidade.getHibrido());
		tLinhagem.setText(entidade.getLinhegem());
		tMaturacaoSexual.setText(FormatoHelper.getDecimalFormato().format(entidade.getMaturacaoSexual()));
		setValorFrio(entidade.getToleranciaFrio());
		setValorSalinidade(entidade.getToleranciaSalinidade());
	}

	@Override
	public boolean isEntidadeNula(){
		return entidade==null;
	}
	
	private void setValorFrio(String radio) {
		if (radio.equalsIgnoreCase("ALTO")) {
			rbAltoFrio.setSelection(true);
		} else if (radio.equalsIgnoreCase("MEDIO")) {
			rbMedioFrio.setSelection(true);
		} else if (radio.equalsIgnoreCase("BAIXO")) {
			rbBaixoFrio.setSelection(true);
		} else {
			rbAltoFrio.setSelection(false);
			rbMedioFrio.setSelection(false);
			rbBaixoFrio.setSelection(false);
		}
	}

	private String getValorFrio() {
		if (rbAltoFrio.getSelection()) {
			return "ALTO";
		} else if (rbMedioFrio.getSelection()){
			return "MEDIO";
		} else {
			return "BAIXO";
		}
	}
	
	private void setValorSalinidade(String radio) {
		if (radio.equalsIgnoreCase("ALTO")) {
			rbAltoSalidade.setSelection(true);
		} else if (radio.equalsIgnoreCase("MEDIO")) {
			rbMedioSalinidade.setSelection(true);
		} else if (radio.equalsIgnoreCase("BAIXO")) {
			rbBaixoSalinidade.setSelection(true);
		} else {
			rbAltoSalidade.setSelection(false);
			rbMedioSalinidade.setSelection(false);
			rbBaixoSalinidade.setSelection(false);
		}
	}

	private String getValorSalinidade() {
		if (rbAltoSalidade.getSelection()) {
			return "ALTO";
		} else if (rbMedioSalinidade.getSelection()) {
			return "MEDIO";
		} else {
			return "BAIXO";
		}
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new EspecieFiltro();
		especieService = new EspecieService();

		composite.setLayout(new GridLayout(5, false));

		Group grpEspcie = new Group(composite, SWT.NONE);
		grpEspcie.setLayout(new GridLayout(7, false));
		grpEspcie.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5,
				1));
		grpEspcie.setText("Esp\u00E9cie");

		Label lblEspecie = new Label(grpEspcie, SWT.NONE);
		lblEspecie.setText("Esp\u00E9cie:");

		tEspecie = new Text(grpEspcie, SWT.BORDER);
		tEspecie.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 6,
				1));

		Label lblLinhagem = new Label(grpEspcie, SWT.NONE);
		lblLinhagem.setText("Linhagem:");

		tLinhagem = new Text(grpEspcie, SWT.BORDER);
		tLinhagem.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				6, 1));

		Label lblHibrdo = new Label(grpEspcie, SWT.NONE);
		lblHibrdo.setText("H\u00EDbrido:");

		tHibrido = new Text(grpEspcie, SWT.BORDER);
		tHibrido.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 6,
				1));

		Label lblGenero = new Label(grpEspcie, SWT.NONE);
		lblGenero.setText("G\u00E9nero:");

		tGenero = new Text(grpEspcie, SWT.BORDER);
		tGenero.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 6,
				1));

		Label lblMaturaoSexual = new Label(grpEspcie, SWT.NONE);
		lblMaturaoSexual.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 2, 1));
		lblMaturaoSexual.setText("Matura\u00E7\u00E3o Sexual:");

		tMaturacaoSexual = new Text(grpEspcie, SWT.BORDER);
		tMaturacaoSexual.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 5, 1));

		Group groupSalinidade = new Group(grpEspcie, SWT.NONE);
		groupSalinidade.setText("Tolerancia a Salinidade");
		GridData gd_groupSalinidade = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 6, 1);
		gd_groupSalinidade.heightHint = 30;
		groupSalinidade.setLayoutData(gd_groupSalinidade);
		groupSalinidade.setLayout(new GridLayout(3, false));

		rbBaixoSalinidade = new Button(groupSalinidade, SWT.RADIO);
		rbBaixoSalinidade.setText("Baixo");

		rbMedioSalinidade = new Button(groupSalinidade, SWT.RADIO);
		rbMedioSalinidade.setText("M\u00E9dio");

		rbAltoSalidade = new Button(groupSalinidade, SWT.RADIO);
		rbAltoSalidade.setText("Alto");

		Group groupFrio = new Group(grpEspcie, SWT.NONE);
		groupFrio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));
		groupFrio.setText("Tolerancia a Frio");
		groupFrio.setLayout(new GridLayout(3, false));

		rbBaixoFrio = new Button(groupFrio, SWT.RADIO);
		rbBaixoFrio.setText("Baixo");

		rbMedioFrio = new Button(groupFrio, SWT.RADIO);
		rbMedioFrio.setText("M\u00E9dio");

		rbAltoFrio = new Button(groupFrio, SWT.RADIO);
		rbAltoFrio.setText("Alto");

		Label lblFiltro = new Label(grpEspcie, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpEspcie, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 6, 1));

		tvEspecie = new TableViewer(grpEspcie, SWT.BORDER | SWT.FULL_SELECTION);
		table = tvEspecie.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 7, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tvEspecie.addFilter(filtro);
		tvEspecie.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvEspecie, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Especie) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(39);
		tblclmnId.setText("Id");

		tvcEspecie = new TableViewerColumn(tvEspecie, SWT.NONE);
		tvcEspecie.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Especie) element).getEspecie();
			}
		});
		TableColumn tblclmnEspecie = tvcEspecie.getColumn();
		tblclmnEspecie.setWidth(57);
		tblclmnEspecie.setText("Esp\u00E9cie");

		tvcLinhagem = new TableViewerColumn(tvEspecie, SWT.NONE);
		tvcLinhagem.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Especie) element).getLinhegem();
			}
		});
		TableColumn tblclmnLinhagem = tvcLinhagem.getColumn();
		tblclmnLinhagem.setWidth(73);
		tblclmnLinhagem.setText("Linhagem");

		tvcHibrido = new TableViewerColumn(tvEspecie, SWT.NONE);
		tvcHibrido.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Especie) element).getHibrido();
			}
		});
		TableColumn tblclmnHibrido = tvcHibrido.getColumn();
		tblclmnHibrido.setWidth(52);
		tblclmnHibrido.setText("H\u00EDbrido");

		tvcGenero = new TableViewerColumn(tvEspecie, SWT.NONE);
		tvcGenero.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Especie) element).getGenero();
			}
		});
		TableColumn tblclmnGenero = tvcGenero.getColumn();
		tblclmnGenero.setWidth(54);
		tblclmnGenero.setText("G\u00E9nero");

		tvcMaturacaoSexual = new TableViewerColumn(tvEspecie, SWT.NONE);
		tvcMaturacaoSexual.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.getDecimalFormato().format(
						((Especie) element).getMaturacaoSexual());
			}
		});
		TableColumn tblclmnMaturacaoSexual = tvcMaturacaoSexual.getColumn();
		tblclmnMaturacaoSexual.setWidth(100);
		tblclmnMaturacaoSexual.setText("Matura\u00E7\u00E3o Sexual");

		tvcSalinidade = new TableViewerColumn(tvEspecie, SWT.NONE);
		tvcSalinidade.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Especie)element).getToleranciaSalinidade();
			}
		});
		TableColumn tblclmnToleranciaASalinidade = tvcSalinidade.getColumn();
		tblclmnToleranciaASalinidade.setWidth(100);
		tblclmnToleranciaASalinidade.setText("Tolerancia a Salinidade");

		tvcFrio = new TableViewerColumn(tvEspecie, SWT.NONE);
		tvcFrio.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Especie)element).getToleranciaFrio();
			}
		});
		TableColumn tblclmnToleranciaAFrio = tvcFrio.getColumn();
		tblclmnToleranciaAFrio.setWidth(100);
		tblclmnToleranciaAFrio.setText("Tolerancia a Frio");
		// TODO Auto-generated method stub

	}
}
