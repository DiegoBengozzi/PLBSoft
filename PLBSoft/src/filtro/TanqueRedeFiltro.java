package filtro;

import modelo.TanqueRede;
import org.eclipse.jface.viewers.Viewer;

public class TanqueRedeFiltro extends TabelaFiltro {
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (verificar())
			return true;
		TanqueRede tanqueRede = (TanqueRede) element;
		if (tanqueRede.getNome().toLowerCase().matches(filtro.toLowerCase()))
			return true;
		
//		if (tanqueRede.getStatus().toLowerCase().matches(filtro.toLowerCase()))
//			return true;
		
//		if (tanqueRede.getTamanho().toBigInteger().matches);
//			return true;	
			
		return false;
	}
}