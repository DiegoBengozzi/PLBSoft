package GUI;

import modelo.Lote;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import service.LoteService;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ManejoLoteGUI extends TelaEdicaoGUI<Lote>{
	private Table tableJuncaoLote;
	private Table tableResultado;
	private ComboViewer cvLote;
	private Combo comboLote;
	private LoteService loteService;
	private TableViewerColumn tcvId, tvcNome, tvcQuantidade, tvcResultadoId, tvcResultadoNome, tvcResultadoQuantidade;
	private TableViewer tvJuncaoLote, tvResultadoLote;
	private IStructuredSelection comboValorLote;

	public ManejoLoteGUI(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
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
		tvJuncaoLote.setInput(comboValorLote);
		tvJuncaoLote.refresh();
		
	}

	@Override
	public boolean isEntidadeNula() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		loteService = new LoteService();
		composite.setLayout(new GridLayout(1, false));
		
		Group grpManejoDeLote = new Group(composite, SWT.NONE);
		grpManejoDeLote.setLayout(new GridLayout(1, false));
		grpManejoDeLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpManejoDeLote.setText("Manejo de Lote");
		
		CTabFolder tabFolder = new CTabFolder(grpManejoDeLote, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmJuncaoDeLote = new CTabItem(tabFolder, SWT.NONE);
		tbtmJuncaoDeLote.setText("Jun\u00E7\u00E3o de Lote");
		
		Composite compositeJuncao = new Composite(tabFolder, SWT.NONE);
		tbtmJuncaoDeLote.setControl(compositeJuncao);
		compositeJuncao.setLayout(new GridLayout(2, false));
		
		Label lblSelecioneOLotes = new Label(compositeJuncao, SWT.NONE);
		lblSelecioneOLotes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblSelecioneOLotes.setText("Selecione o Lotes para a Jun\u00E7\u00E3o");
		new Label(compositeJuncao, SWT.NONE);
		
		cvLote = new ComboViewer(compositeJuncao, SWT.NONE);
		comboLote = cvLote.getCombo();
		comboLote.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cvLote.setContentProvider(ArrayContentProvider.getInstance());
		cvLote.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Lote)element).getNome();
			}
		});
		cvLote.setInput(loteService.buscarTodosLoteAtivo());
		
		Button btnAdicionarLote = new Button(compositeJuncao, SWT.NONE);
		btnAdicionarLote.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboValorLote = (IStructuredSelection) cvLote.getSelection();
				carregarComponentes();
			}
		});
		btnAdicionarLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnAdicionarLote.setText("Adicionar Lote");
		
		tvJuncaoLote = new TableViewer(compositeJuncao, SWT.BORDER | SWT.FULL_SELECTION);
		tableJuncaoLote = tvJuncaoLote.getTable();
		tableJuncaoLote.setLinesVisible(true);
		tableJuncaoLote.setHeaderVisible(true);
		tableJuncaoLote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		tvJuncaoLote.setContentProvider(ArrayContentProvider.getInstance());
		
		tcvId = new TableViewerColumn(tvJuncaoLote, SWT.NONE);
		tcvId.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Lote)element).getId().toString();
			}
		});
		TableColumn tblclmnId = tcvId.getColumn();
		tblclmnId.setWidth(40);
		tblclmnId.setText("Id");
		
		tvcNome = new TableViewerColumn(tvJuncaoLote, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Lote)element).getNome();
			}
		});
		TableColumn tblclmnNewColumn = tvcNome.getColumn();
		tblclmnNewColumn.setWidth(150);
		tblclmnNewColumn.setText("Nome");
		
		tvcQuantidade = new TableViewerColumn(tvJuncaoLote, SWT.NONE);
		tvcQuantidade.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Lote)element).getQuantidadePeixe().toString();
			}
		});
		TableColumn tblclmnQuantidade = tvcQuantidade.getColumn();
		tblclmnQuantidade.setWidth(150);
		tblclmnQuantidade.setText("Quantidade");
		
		tvResultadoLote = new TableViewer(compositeJuncao, SWT.BORDER | SWT.FULL_SELECTION);
		tableResultado = tvResultadoLote.getTable();
		tableResultado.setLinesVisible(true);
		GridData gd_tableResultado = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_tableResultado.heightHint = 23;
		tableResultado.setLayoutData(gd_tableResultado);
		
		tvcResultadoId = new TableViewerColumn(tvResultadoLote, SWT.NONE);
		TableColumn tblclmnTesteresultado = tvcResultadoId.getColumn();
		tblclmnTesteresultado.setWidth(40);
		tblclmnTesteresultado.setText("Id");
		
		tvcResultadoNome = new TableViewerColumn(tvResultadoLote, SWT.NONE);
		TableColumn tblclmnNome = tvcResultadoNome.getColumn();
		tblclmnNome.setWidth(150);
		tblclmnNome.setText("Nome");
		
		tvcResultadoQuantidade = new TableViewerColumn(tvResultadoLote, SWT.NONE);
		TableColumn tblclmnQuantidade_1 = tvcResultadoQuantidade.getColumn();
		tblclmnQuantidade_1.setWidth(150);
		tblclmnQuantidade_1.setText("Quantidade");
		
		CTabItem tbtmDivisaoDeLote = new CTabItem(tabFolder, SWT.NONE);
		tbtmDivisaoDeLote.setText("Divis\u00E3o de Lote");
		
		Composite compositeDivisao = new Composite(tabFolder, SWT.NONE);
		tbtmDivisaoDeLote.setControl(compositeDivisao);
		compositeDivisao.setLayout(new GridLayout(1, false));
		
	}
}
