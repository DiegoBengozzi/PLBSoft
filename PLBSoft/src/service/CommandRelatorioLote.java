package service;

import helper.LayoutHelper;

import java.util.Map;

import GUI.RelatorioGUI;

import net.sf.jasperreports.engine.JasperPrint;

public class CommandRelatorioLote extends ReportCommand {
	
	@Override
	public void execute() {
		JasperPrint jaPrint = getReport("relatorioLote.jasper");
		
		if(!jaPrint.getPages().isEmpty()){
			RelatorioGUI relatorio = getView();
			relatorio.setReport(jPrint);
			
			ScrolledComposite sc = LayoutHelper.getActiveScroll();
			sc.setContent(relatorio);
			sc.setMinSize(relatorio.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}
			
			
	}

	@Override
	public Map<String, Object> getParametros() {
		// TODO Auto-generated method stub
		return null;
	}

}
