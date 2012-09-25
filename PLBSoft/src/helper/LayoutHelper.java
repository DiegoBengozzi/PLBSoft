package helper;

import org.eclipse.swt.widgets.Shell;

public class LayoutHelper{
	public static Shell shell;
	
	public static Shell getShellAtivo(){
		if(shell!=null){
			shell = new Shell();
		}
		return shell;
	}
	
	
	
}
