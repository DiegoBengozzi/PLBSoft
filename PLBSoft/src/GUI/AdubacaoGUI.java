package GUI;

import helper.CalendarioHelper;
import helper.FormatoHelper;
import modelo.Adubacao;
import modelo.Tanque;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.AdubacaoService;
import service.TanqueService;
import filtro.AdubacaoFiltro;

public class AdubacaoGUI extends TelaEdicaoGUI<Adubacao> {
	private Text tDescricao;
	private Text tFiltro;
	private Table table;
	private AdubacaoFiltro filtro;
	private TableViewer tvAdubacao;
	private AdubacaoService adubacaoService;
	private ComboViewer cvTanque;
	private TanqueService tanqueService;
	private TableViewerColumn tvcId, tvcDescricao, tvcTanque, tvcData;
	private IStructuredSelection valorCombo;
	private Combo comboTanque;
	private Text textData;

	public AdubacaoGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() {
		entidade.setStatus(false);
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvAdubacao.refresh();
	}

	@Override
	public void salvar() {
		if (entidade == null)
			entidade = new Adubacao();

		entidade.setDescricao(tDescricao.getText().trim());
		entidade.setStatus(true);
		valorCombo = (IStructuredSelection) cvTanque.getSelection();
		entidade.setTanqueId((Tanque) valorCombo.getFirstElement());
		// entidade.setData(FormatoHelper.dataFormato().format());
		entidade.setData(CalendarioHelper.retornaData());
		adubacaoService.salvar(entidade);
	}

	@Override
	public void validar() throws Exception {

	}

	@Override
	public void carregar() {
		tvAdubacao.setInput(adubacaoService.buscarTodosAdubacaoAtivo());
		tvAdubacao.refresh();
	}

	@Override
	public void limparDados() {
		CalendarioHelper.limparData();
		tDescricao.setText("");
		comboTanque.deselectAll();
		textData.setText("");
		tFiltro.setText("");
		entidade = null;
	}

	@Override
	public void carregarComponentes() {

		comboTanque.select(tanqueService.buscarTodosTanqueAtivo().indexOf(
				entidade.getTanqueId()));
		tDescricao.setText(entidade.getDescricao());
		textData.setText(FormatoHelper.dataFormat.format(entidade.getData()));
		CalendarioHelper.escreveData(entidade.getData());
	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new AdubacaoFiltro();
		adubacaoService = new AdubacaoService();
		tanqueService = new TanqueService();

		composite.setLayout(new GridLayout(2, false));

		Group grpAdubao = new Group(composite, SWT.NONE);
		grpAdubao.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2,
				1));
		grpAdubao.setText("Aduba\u00E7\u00E3o");
		grpAdubao.setLayout(new GridLayout(2, false));

		Label lblTanque = new Label(grpAdubao, SWT.NONE);
		lblTanque.setText("Tanque:");

		cvTanque = new ComboViewer(grpAdubao, SWT.READ_ONLY);
		comboTanque = cvTanque.getCombo();
		comboTanque.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		cvTanque.setContentProvider(ArrayContentProvider.getInstance());
		cvTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Tanque) element).getNome();
			}
		});
		cvTanque.setInput(tanqueService.buscarTodosTanqueAtivo());

		Label lblDescrioDaAdubao = new Label(grpAdubao, SWT.NONE);
		lblDescrioDaAdubao
				.setText("Descri\u00E7\u00E3o \r\n      da \r\naduba\u00E7\u00E3o:");

		tDescricao = new Text(grpAdubao, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		tDescricao.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));
		/**
		 * calendario!!
		 * 
		 */
		Label lblData = new Label(grpAdubao, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblData.setText("Data:");
		textData = new Text(grpAdubao, SWT.BORDER | SWT.READ_ONLY);
		textData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblFiltro = new Label(grpAdubao, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpAdubao, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1,
				1));
		tFiltro.setMessage("Filtro de Busca!!");

		tvAdubacao = new TableViewer(grpAdubao, SWT.BORDER | SWT.FULL_SELECTION);
		tvAdubacao.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvAdubacao
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (Adubacao) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		table = tvAdubacao.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		tvAdubacao.addFilter(filtro);
		tvAdubacao.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvAdubacao, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Adubacao) element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(40);
		tblclmnId.setText("Id");

		tvcTanque = new TableViewerColumn(tvAdubacao, SWT.NONE);
		tvcTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Adubacao t = (Adubacao) element;

				return t.getTanqueId() == null ? "" : t.getTanqueId().getNome();
			}
		});
		TableColumn tblclmnTanque = tvcTanque.getColumn();
		tblclmnTanque.setWidth(71);
		tblclmnTanque.setText("Tanque");

		tvcDescricao = new TableViewerColumn(tvAdubacao, SWT.NONE);
		tvcDescricao.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Adubacao) element).getDescricao();

			}
		});

		TableColumn tblclmnDescricao = tvcDescricao.getColumn();
		tblclmnDescricao.setWidth(127);
		tblclmnDescricao.setText("Descri\u00E7\u00E3o");

		tvcData = new TableViewerColumn(tvAdubacao, SWT.NONE);
		tvcData.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return FormatoHelper.dataFormat.format(((Adubacao) element)
						.getData());
			}
		});
		TableColumn tblclmnData = tvcData.getColumn();
		tblclmnData.setWidth(81);
		tblclmnData.setText("Data");

	}

}
