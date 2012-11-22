package service;

import helper.LayoutHelper;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;

import GUI.RelatorioGUI;

public class CommandRelatorioLote extends ReportCommand {

	@Override
	public void execute() throws Exception {
		JasperPrint jaPrint = getReport("\\relatorioLote.jasper");
		if (!jaPrint.getPages().isEmpty()) {
			RelatorioGUI relatorio = getView();
			relatorio.setReport(jaPrint);
			ScrolledComposite sc = LayoutHelper.getActiveScroll();
			sc.setContent(relatorio);
			sc.setMinSize(relatorio.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
		}
	}

	@Override
	public Map<String, Object> getParametros() {
		return new HashMap<String, Object>();
	}
}
