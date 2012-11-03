package GUI;

import filtro.SafraFiltro;
import helper.CalendarioHelper;
import helper.FormatoHelper;
import modelo.Safra;

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

import service.SafraService;

public class SafraGUI extends TelaEdicaoGUI<Safra> {
	private Text tInicioSafra;
	private Text tFimSafra;
	private Text tDescricao;
	private Text tFiltro;
	private Table tableSafra;
	private TableViewer tvSafra;
	private TableViewerColumn tvcId, tvcInicioSafra, tvcFimSafra, tvcDescricao;
	private SafraService safraService;
	private SafraFiltro filtro;

	public SafraGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() throws Exception {
		entidade.setStatus(false);

	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvSafra.refresh();

	}

	@Override
	public void salvar() throws Exception {
		if (entidade == null)
			entidade = new Safra();

		entidade.setDataInicio(FormatoHelper.dataFormat.parse(tInicioSafra
				.getText()));
		entidade.setDataFim(FormatoHelper.dataFormat.parse(tFimSafra.getText()));
		entidade.setDescricao(tDescricao.getText());
		entidade.setStatus(true);

		safraService.salvar(entidade);
	}

	@Override
	public void validar() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void carregar() {
		tvSafra.setInput(safraService.buscarTodosSafraAtivo());
		tvSafra.refresh();
		tInicioSafra.setText(FormatoHelper.dataFormat.format(CalendarioHelper
				.retornaData()));

	}

	@Override
	public void limparDados() {
		tInicioSafra.setText("");
		tFimSafra.setText("");
		tDescricao.setText("");
		CalendarioHelper.limparData();
		tInicioSafra.setText(FormatoHelper.dataFormat.format(CalendarioHelper
				.retornaData()));
		tFiltro.setText("");
		entidade = null;
	}

	@Override
	public void carregarComponentes() {
		tInicioSafra.setText(FormatoHelper.dataFormat.format(entidade
				.getDataInicio()));
		tFimSafra
				.setText(FormatoHelper.dataFormat.format(entidade.getDataFim()));
		tDescricao.setText(entidade.getDescricao());

	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		safraService = new SafraService();
		filtro = new SafraFiltro();

		composite.setLayout(new GridLayout(1, false));

		Group grpSafra = new Group(composite, SWT.NONE);
		grpSafra.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		grpSafra.setText("Safra");
		grpSafra.setLayout(new GridLayout(2, false));

		Label lblIncioDaSafra = new Label(grpSafra, SWT.NONE);
		lblIncioDaSafra.setText("In\u00EDcio da Safra:");

		tInicioSafra = new Text(grpSafra, SWT.BORDER);
		tInicioSafra.setEditable(false);
		tInicioSafra.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));

		Label lblFinalDaSafra = new Label(grpSafra, SWT.NONE);
		lblFinalDaSafra.setText("Final da Safra:");

		tFimSafra = new Text(grpSafra, SWT.BORDER);
		tFimSafra.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));

		Label lblDescricao = new Label(grpSafra, SWT.NONE);
		lblDescricao.setText("Descri\u00E7\u00E3o:");

		tDescricao = new Text(grpSafra, SWT.BORDER);
		tDescricao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblFiltro = new Label(grpSafra, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpSafra, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		tvSafra = new TableViewer(grpSafra, SWT.BORDER | SWT.FULL_SELECTION);
		tvSafra.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvSafra
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (Safra) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		tableSafra = tvSafra.getTable();
		tableSafra.setLinesVisible(true);
		tableSafra.setHeaderVisible(true);
		tableSafra.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1));
		tvSafra.addFilter(filtro);
		tvSafra.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvSafra, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Safra) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(39);
		tblclmnId.setText("Id");

		tvcInicioSafra = new TableViewerColumn(tvSafra, SWT.NONE);
		tvcInicioSafra.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Safra) element)
						.getDataInicio());
			}
		});
		TableColumn tblclmnInicioDaSafra = tvcInicioSafra.getColumn();
		tblclmnInicioDaSafra.setWidth(108);
		tblclmnInicioDaSafra.setText("Inicio da Safra");

		tvcFimSafra = new TableViewerColumn(tvSafra, SWT.NONE);
		tvcFimSafra.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Safra) element)
						.getDataFim());
			}
		});
		TableColumn tblclmnFinalDaSafra = tvcFimSafra.getColumn();
		tblclmnFinalDaSafra.setWidth(122);
		tblclmnFinalDaSafra.setText("Final da Safra");

		tvcDescricao = new TableViewerColumn(tvSafra, SWT.NONE);
		tvcDescricao.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Safra) element).getDescricao();
			}
		});
		TableColumn tblclmnDescrio = tvcDescricao.getColumn();
		tblclmnDescrio.setWidth(170);
		tblclmnDescrio.setText("Descri\u00E7\u00E3o");

	}
}
