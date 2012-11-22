package helper;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Shell;

public class LayoutHelper {	
	private static Shell shell;
	private static ScrolledComposite scrolledComposite;

	public static Shell getShellAtivo() {
		if (shell == null) {
			shell = new Shell();
		}
		return shell;
	}
	
	public static ScrolledComposite getActiveScroll(){
		if(scrolledComposite == null)
			scrolledComposite = new ScrolledComposite(shellPlbsoft, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		return scrolledComposite;
	}

}
