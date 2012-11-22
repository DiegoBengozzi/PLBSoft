package service;

import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

public class CommandRelatorioLote extends ReportCommand {
	
	public void execute() {
		JasperPrint jaPrint = getReport("relatorioLote.jasper");
		jaPrint.getPages().isEmpty();
	}

	@Override
	public Map<String, Object> getParametros() {
		// TODO Auto-generated method stub
		return null;
	}

}
