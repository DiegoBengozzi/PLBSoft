package service;

import helper.LayoutHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.eclipse.swt.SWT;
import org.eclipse.ui.PartInitException;
import org.postgresql.Driver;

import GUI.RelatorioGUI;

public abstract class ReportCommand {

	public ReportCommand() {
	}

	public JasperPrint getReport(String caminhoRelatorio) {

		try {
			String urlBanco = "jdbc:postgresql://localhost:5432/BancoPLBSoft";

			DriverManager.registerDriver(new Driver());

			Connection con = DriverManager.getConnection(urlBanco, "postgres",
					"admin");

			return JasperFillManager
					.fillReport("/relatorio".concat(caminhoRelatorio),
							getParametros(), con);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;

	}

	public JasperPrint getReport(String caminhoRelatorio, List<?> listaObjetos) {

		try {
			JRDataSource jrds = new JRBeanCollectionDataSource(listaObjetos);
			return JasperFillManager.fillReport(
					"/relatorio".concat(caminhoRelatorio), getParametros(),
					jrds);

		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;

	}

	public RelatorioGUI getView() throws PartInitException {

		RelatorioGUI reportView = new RelatorioGUI(
				LayoutHelper.getShellAtivo(), SWT.NONE);
		return reportView;
	}

	public abstract void execute();
	public abstract Map<String, Object> getParametros();

}
