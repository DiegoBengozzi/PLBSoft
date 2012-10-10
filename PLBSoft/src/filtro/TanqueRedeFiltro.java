package filtro;

import helper.FormatoHelper;
import modelo.TanqueRede;
import org.eclipse.jface.viewers.Viewer;

public class TanqueRedeFiltro extends TabelaFiltro {
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (verificar())
			return true;
		TanqueRede tanqueRede = (TanqueRede) element;
		
		if (tanqueRede.getId().toString().toLowerCase().matches(filtro.toLowerCase()))
			return true;
		
		if (tanqueRede.getNome().toLowerCase().matches(filtro.toLowerCase()))
			return true;
		
		if (FormatoHelper.getDecimalFormato().format(tanqueRede.getTamanho()).matches(filtro.toLowerCase()))
			return true;	
			
		return false;
	}
}