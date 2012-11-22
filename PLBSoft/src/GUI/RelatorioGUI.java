package GUI;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.jasperassistant.designer.viewer.ViewerComposite;

public class RelatorioGUI extends Composite {

	private ViewerComposite view;

	public RelatorioGUI(Composite parent, int style) {
		super(parent, style);
		createComponents(parent);
	}

	public void createComponents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			view = new ViewerComposite(container, SWT.NONE);
		}
	}

	public void setReport(JasperPrint jPrint) {
		view.getReportViewer().setDocument(jPrint);
	}
}
