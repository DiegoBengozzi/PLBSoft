package GUI;

import static helper.StatusHelper.mensagemError;
import static helper.StatusHelper.mensagemInfo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import conexao.HibernateConnection;

public abstract class TelaEdicaoGUI<T> extends Composite {
	protected T entidade;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws Exception
	 */
	public abstract void excluir() throws Exception;

	public abstract void buscar();

	public abstract void salvar() throws Exception;

	public abstract void adicionarComponentes(Composite composite);

	public abstract void carregar();

	public abstract void limparDados();

	public abstract void carregarComponentes();

	public TelaEdicaoGUI(Composite parent, int style) {
		super(parent, style);
		entidade = null;
		setLayout(new GridLayout(2, false));

		Composite compositePrincipal = new Composite(this, SWT.NONE);
		compositePrincipal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		Composite compositeSecundario = new Composite(this, SWT.NONE);
		compositeSecundario.setLayout(new GridLayout(1, false));
		compositeSecundario.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				false, false, 1, 1));

		Button btnSalvar = new Button(compositeSecundario, SWT.NONE);
		btnSalvar.setImage(SWTResourceManager.getImage(TelaEdicaoGUI.class,
				"/Icone/water--plus.png"));
		btnSalvar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					salvar();
					HibernateConnection.commit();
					carregar();
					limparDados();
					mensagemInfo("Cadastro Realizado!");
				} catch (Exception e1) {
					mensagemError("Erro de cadastro!!! ");
					e1.printStackTrace();
				}
			}
		});
		btnSalvar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btnSalvar.setText("Salvar");

		Button btnExcluir = new Button(compositeSecundario, SWT.NONE);
		btnExcluir.setImage(SWTResourceManager.getImage(TelaEdicaoGUI.class,
				"/Icone/water--minus.png"));
		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {

					if (entidade == null)
						return;

					excluir();
					HibernateConnection.commit();
					carregar();
					limparDados();
					mensagemInfo("Exclusao realizada!!");

				} catch (Exception e2) {
					mensagemError("cadastro nao excluido");
					e2.printStackTrace();
				}
			}
		});
		btnExcluir.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btnExcluir.setText("Excluir");

		Button btnBuscar = new Button(compositeSecundario, SWT.NONE);
		btnBuscar.setImage(SWTResourceManager.getImage(TelaEdicaoGUI.class,
				"/Icone/water--arrow.png"));
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buscar();
			}
		});
		btnBuscar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btnBuscar.setText("Buscar");

		Button btnVoltar = new Button(compositeSecundario, SWT.NONE);
		btnVoltar.setImage(SWTResourceManager.getImage(TelaEdicaoGUI.class,
				"/Icone/voltar2.png"));
		btnVoltar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				voltar();
			}
		});
		btnVoltar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		btnVoltar.setText("Voltar");

		adicionarComponentes(compositePrincipal);

	}

	public void voltar() {
		dispose();
	}

	@Override
	protected void checkSubclass() {}
}
