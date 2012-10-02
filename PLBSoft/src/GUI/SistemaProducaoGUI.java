package GUI;

import static helper.StatusHelper.mensagemError;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SistemaProducaoGUI extends TelaEdicaoGUI{
	private Text tSistemaCultivo;
	private Text tBiomassaEconomica;
	private Text tBiomassaCritica;
	private Text tCapacidadeSuporte;

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
	public void adicionarComponentes(Composite composite) {
		composite.setLayout(new GridLayout(2, false));
		
		Label lblSistemaDeProduo = new Label(composite, SWT.NONE);
		lblSistemaDeProduo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSistemaDeProduo.setText("Sistema de Cultivo:");
		
		tSistemaCultivo = new Text(composite, SWT.BORDER);
		tSistemaCultivo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblBiomassaEconomica = new Label(composite, SWT.NONE);
		lblBiomassaEconomica.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBiomassaEconomica.setText("Biomassa Economica:");
		
		tBiomassaEconomica = new Text(composite, SWT.BORDER);
		tBiomassaEconomica.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblBiomassaCritica = new Label(composite, SWT.NONE);
		lblBiomassaCritica.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBiomassaCritica.setText("Biomassa Critica:");
		
		tBiomassaCritica = new Text(composite, SWT.BORDER);
		tBiomassaCritica.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCapacidadeDeSuporte = new Label(composite, SWT.NONE);
		lblCapacidadeDeSuporte.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCapacidadeDeSuporte.setText("Capacidade de Suporte:");
		
		tCapacidadeSuporte = new Text(composite, SWT.BORDER);
		tCapacidadeSuporte.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carregar() {
		// TODO Auto-generated method stub
		
	}
	

}
