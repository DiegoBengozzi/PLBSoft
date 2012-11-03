package GUI;

import modelo.Doenca;

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

import service.DoencaService;
import filtro.DoencaFiltro;

public class DoencaGUI extends TelaEdicaoGUI<Doenca> {
	private Text tNome;
	private Text tCausa;
	private Text tSintoma;
	private Text tTratamento;
	private Text tFiltro;
	private Table tableDoenca;
	private TableViewer tvDoenca;
	private TableViewerColumn tvcId, tvcNome, tvcCausa, tvcSintoma,
			tvcTratamento;
	private DoencaFiltro filtro;
	private DoencaService doencaService;

	public DoencaGUI(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void excluir() throws Exception {
		entidade.setStatus(false);
	}

	@Override
	public void buscar() {
		filtro.setFiltro(tFiltro.getText());
		tvDoenca.refresh();
	}

	@Override
	public void salvar() throws Exception {
		if (entidade == null)
			entidade = new Doenca();
		entidade.setNome(tNome.getText().trim());
		entidade.setCausa(tCausa.getText().trim());
		entidade.setSintoma(tSintoma.getText().trim());
		entidade.setTratamento(tTratamento.getText().trim());
		entidade.setStatus(true);
		doencaService.salvar(entidade);
	}

	@Override
	public void validar() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void carregar() {
		tvDoenca.setInput(doencaService.buscarTodosDoencaAtivo());
		tvDoenca.refresh();
	}

	@Override
	public void limparDados() {
		tNome.setText("");
		tCausa.setText("");
		tSintoma.setText("");
		tTratamento.setText("");
		tFiltro.setText("");
		entidade = null;
	}

	@Override
	public void carregarComponentes() {
		tNome.setText(entidade.getNome());
		tCausa.setText(entidade.getCausa());
		tSintoma.setText(entidade.getSintoma());
		tTratamento.setText(entidade.getTratamento());
	}

	@Override
	public boolean isEntidadeNula() {
		return entidade == null;
	}

	@Override
	public void adicionarComponentes(Composite composite) {
		filtro = new DoencaFiltro();
		doencaService = new DoencaService();

		composite.setLayout(new GridLayout(1, false));

		Group grpDoena = new Group(composite, SWT.NONE);
		grpDoena.setLayout(new GridLayout(2, false));
		grpDoena.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		grpDoena.setText("Doen\u00E7a");

		Label lblNome = new Label(grpDoena, SWT.NONE);
		lblNome.setText("Nome:");

		tNome = new Text(grpDoena, SWT.BORDER);
		tNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblCausa = new Label(grpDoena, SWT.NONE);
		lblCausa.setText("Causa:");

		tCausa = new Text(grpDoena, SWT.BORDER);
		tCausa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblSintoma = new Label(grpDoena, SWT.NONE);
		lblSintoma.setText("Sintoma:");

		tSintoma = new Text(grpDoena, SWT.BORDER);
		tSintoma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblTratamento = new Label(grpDoena, SWT.NONE);
		lblTratamento.setText("Tratamento:");

		tTratamento = new Text(grpDoena, SWT.BORDER);
		tTratamento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblFiltro = new Label(grpDoena, SWT.NONE);
		lblFiltro.setText("Filtro:");

		tFiltro = new Text(grpDoena, SWT.BORDER);
		tFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		tFiltro.setMessage("Filtro de Busca!!");

		tvDoenca = new TableViewer(grpDoena, SWT.BORDER | SWT.FULL_SELECTION);
		tvDoenca.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection itemSelecao = (IStructuredSelection) tvDoenca
						.getSelection();
				if (itemSelecao.isEmpty())
					return;
				limparDados();
				entidade = (Doenca) itemSelecao.getFirstElement();
				carregarComponentes();
			}
		});
		tableDoenca = tvDoenca.getTable();
		tableDoenca.setLinesVisible(true);
		tableDoenca.setHeaderVisible(true);
		tableDoenca.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1));

		tvDoenca.addFilter(filtro);
		tvDoenca.setContentProvider(ArrayContentProvider.getInstance());

		tvcId = new TableViewerColumn(tvDoenca, SWT.NONE);
		tvcId.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Doenca)element).getId().toString();
			}
		});
		TableColumn tblclmnId = tvcId.getColumn();
		tblclmnId.setWidth(40);
		tblclmnId.setText("Id");

		tvcNome = new TableViewerColumn(tvDoenca, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Doenca)element).getNome();
			}
		});
		TableColumn tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(80);
		tblclmnNome.setText("Nome");

		tvcCausa = new TableViewerColumn(tvDoenca, SWT.NONE);
		tvcCausa.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Doenca)element).getCausa();
			}
		});
		TableColumn tblclmnCausa = tvcCausa.getColumn();
		tblclmnCausa.setWidth(80);
		tblclmnCausa.setText("Causa");

		tvcSintoma = new TableViewerColumn(tvDoenca, SWT.NONE);
		tvcSintoma.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Doenca)element).getSintoma();
			}
		});
		TableColumn tblclmnSintoma = tvcSintoma.getColumn();
		tblclmnSintoma.setWidth(80);
		tblclmnSintoma.setText("Sintoma");

		tvcTratamento = new TableViewerColumn(tvDoenca, SWT.NONE);
		tvcTratamento.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Doenca)element).getTratamento();
			}
		});
		TableColumn tblclmnTratamento = tvcTratamento.getColumn();
		tblclmnTratamento.setWidth(100);
		tblclmnTratamento.setText("Tratamento");

	}
}
