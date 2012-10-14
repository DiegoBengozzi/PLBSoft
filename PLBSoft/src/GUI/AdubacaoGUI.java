package GUI;

import modelo.Adubacao;
import modelo.Tanque;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.AdubacaoService;
import service.TanqueService;
import filtro.AdubacaoFiltro;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.DoubleClickEvent;

public class AdubacaoGUI extends TelaEdicaoGUI<Adubacao> {
	private Text tDescricao;
	private DateTime dateTime;
	private Text tFiltro;
	private Table table;
	private AdubacaoFiltro filtro;
	private TableViewer tvAdubacao;
	private AdubacaoService adubacaoService = new AdubacaoService();
	private ComboViewer cvTanque;
	private TanqueService tanqueService;
	private TableViewerColumn tvcId, tvcDescricao, tvcTanque, tvcData;
	
	
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
		if(entidade == null)
			entidade = new Adubacao();
		
		IStructuredSelection valorCombo = (IStructuredSelection) cvTanque.getSelection();
		entidade.setTanqueId((Adubacao)valorCombo.getFirstElement());
		entidade.setDescricao(tDescricao.getText().trim());
		entidade.setStatus(true);
//		entidade.setData(getDateTime());
		
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
		tDescricao.setText("");
		entidade = null;
	}

	@Override
	public void carregarComponentes() {
		tDescricao.setText(entidade.getDescricao());
//		setDateTime(entidade.getData());
		
	}

	@Override
	public boolean isEntidadeNula() {
		return entidade==null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new AdubacaoFiltro();
		tanqueService = new TanqueService();
		
		composite.setLayout(new GridLayout(2, false));

		Group grpAdubao = new Group(composite, SWT.NONE);
		grpAdubao.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2,
				1));
		grpAdubao.setText("Aduba\u00E7\u00E3o");
		grpAdubao.setLayout(new GridLayout(2, false));

		Label lblTanque = new Label(grpAdubao, SWT.NONE);
		lblTanque.setText("Tanque:");

		cvTanque = new ComboViewer(grpAdubao, SWT.NONE);
		Combo combo = cvTanque.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		cvTanque.setContentProvider(ArrayContentProvider.getInstance());
		cvTanque.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Tanque)element).getNome();
			}
		});
		cvTanque.setInput(tanqueService.buscarTodosTanqueAtivo());

		Label lblDescrioDaAdubao = new Label(grpAdubao, SWT.NONE);
		lblDescrioDaAdubao
				.setText("Descri\u00E7\u00E3o \r\n      da \r\naduba\u00E7\u00E3o:");

		tDescricao = new Text(grpAdubao, SWT.BORDER);
		tDescricao.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));

		Label lblData = new Label(grpAdubao, SWT.NONE);
		lblData.setText("Data:");

		setDateTime(new DateTime(grpAdubao, SWT.BORDER));

		Label lblFiltro = new Label(grpAdubao, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpAdubao, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1,
				1));

		tvAdubacao = new TableViewer(grpAdubao, SWT.BORDER
				| SWT.FULL_SELECTION);
		tvAdubacao.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvAdubacao.getSelection();
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

		tvcTanque = new TableViewerColumn(tvAdubacao,
				SWT.NONE);
		tvcTanque.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Adubacao) element).getTanqueId().toString();
			}
		});
		TableColumn tblclmnTanque = tvcTanque.getColumn();
		tblclmnTanque.setWidth(71);
		tblclmnTanque.setText("Tanque");

		tvcDescricao = new TableViewerColumn(tvAdubacao, SWT.NONE);
		tvcDescricao.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Adubacao)element).getDescricao(); 

			}
		});
		
		TableColumn tblclmnDescricao = tvcDescricao.getColumn();
		tblclmnDescricao.setWidth(127);
		tblclmnDescricao.setText("Descri\u00E7\u00E3o");

		tvcData = new TableViewerColumn(tvAdubacao, SWT.NONE);
		tvcData.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return((Adubacao)element).getData().toString();
			}
		});
		TableColumn tblclmnData = tvcData.getColumn();
		tblclmnData.setWidth(81);
		tblclmnData.setText("Data");

	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
		dateTime.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false,
				1, 1));
	}

}
