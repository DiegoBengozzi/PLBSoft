package GUI;

import static helper.StatusHelper.mensagemError;
import static helper.StatusHelper.mensagemInfo;
import static helper.StatusHelper.mensagemWarning;
import modelo.TipoTanque;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
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

import service.TipoTanqueService;
import conexao.HibernateConnection;
import filtro.TipoTanqueFiltro;

public class TipoTanqueGUI extends TelaEdicaoGUI<TipoTanque> {

	private TipoTanqueService tipoTanqueService = new TipoTanqueService();
	private TipoTanque entidade;
	private Text tNome;
	private Text tRevestimento;
	private Group grpTipoDeTanque;
	private Label lblFiltro;
	private Text tFiltro;
	private Table table;
	private TableViewer tvTipoTanque;
	private TableColumn tblclmnNome;
	private TableViewerColumn tvcNome;
	private TableColumn tblclmnRevestimento;
	private TableViewerColumn tvcRevestimento;
	private TableColumn tblclmnId;
	private TableViewerColumn tvcId;
	private TipoTanqueFiltro filtro;

	public TipoTanqueGUI(Composite parent, int style) {
		super(parent, style);
		entidade = new TipoTanque();
	}

	@Override
	public void excluir() {
		mensagemError("Excuir nao implementado");
	}

	@Override
	public void buscar() {
		mensagemError("Buscar nao implementado");
	}

	@Override
	public void salvar() {
//		if (tNome.getText() == null || tRevestimento.getText() == null
//				|| tNome.getText().equalsIgnoreCase("")
//				|| tRevestimento.getText().equalsIgnoreCase("")) {
			try {
				entidade.setNome(tNome.getText());
				entidade.setRevestimento(tRevestimento.getText());
				entidade.setStatus(true);
				tipoTanqueService.salvar(entidade);
				mensagemInfo("Cadastro Realizado!");
				HibernateConnection.commit();
				carregar();
			} catch (Exception e) {
				mensagemWarning("Erro de cadastro");
			}
//		}else StatusHelper.mensagemWarning("Informe todos os dados pra o cadastro!");
	}

	@Override
	public void carregar() {
		tvTipoTanque.setInput(tipoTanqueService.buscarTodosTipoTanqueAtivo());
		tvTipoTanque.refresh();
		
	}
	
	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new TipoTanqueFiltro();
		
		composite.setLayout(new GridLayout(2, false));
		
		grpTipoDeTanque = new Group(composite, SWT.NONE);
		grpTipoDeTanque.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		grpTipoDeTanque.setText("Tipo de Tanque");
		grpTipoDeTanque.setLayout(new GridLayout(2, false));
		
				Label lblNome = new Label(grpTipoDeTanque, SWT.NONE);
				lblNome.setText("Nome:");
				
						tNome = new Text(grpTipoDeTanque, SWT.BORDER);
						tNome.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
				Label lblRevestimento = new Label(grpTipoDeTanque, SWT.NONE);
				lblRevestimento.setText("Revestimento:");
		
				tRevestimento = new Text(grpTipoDeTanque, SWT.BORDER);
				tRevestimento.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
				tRevestimento.setText("");
				
				lblFiltro = new Label(grpTipoDeTanque, SWT.NONE);
				lblFiltro.setText("Filtro:");
				
				tFiltro = new Text(grpTipoDeTanque, SWT.BORDER);
				tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				
				tvTipoTanque = new TableViewer(grpTipoDeTanque, SWT.BORDER | SWT.FULL_SELECTION);
				table = tvTipoTanque.getTable();
				table.setLinesVisible(true);
				table.setHeaderVisible(true);
				table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				tvTipoTanque.addFilter(filtro);
				tvTipoTanque.setContentProvider(ArrayContentProvider.getInstance());
				
				tvcId = new TableViewerColumn(tvTipoTanque, SWT.NONE);
				tvcId.setLabelProvider(new ColumnLabelProvider(){
					@Override
					public String getText(Object element) {
						return ((TipoTanque)element).getId().toString();
					}
				});
				tblclmnId = tvcId.getColumn();
				tblclmnId.setWidth(100);
				tblclmnId.setText("Id");
				
				tvcNome = new TableViewerColumn(tvTipoTanque, SWT.NONE);
				tvcNome.setLabelProvider(new ColumnLabelProvider(){
					@Override
					public String getText(Object element) {
						return ((TipoTanque)element).getNome();
					}
				});
				tblclmnNome = tvcNome.getColumn();
				tblclmnNome.setWidth(100);
				tblclmnNome.setText("Nome");
				
				tvcRevestimento = new TableViewerColumn(tvTipoTanque, SWT.NONE);
				tvcRevestimento.setLabelProvider(new ColumnLabelProvider(){
					@Override
					public String getText(Object element) {
						return ((TipoTanque)element).getRevestimento();
					}
				});
				tblclmnRevestimento = tvcRevestimento.getColumn();
				tblclmnRevestimento.setWidth(100);
				tblclmnRevestimento.setText("Revestimento");
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

}
