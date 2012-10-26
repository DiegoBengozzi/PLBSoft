package filtro;

import helper.FormatoHelper;
import modelo.Safra;

import org.eclipse.jface.viewers.Viewer;

public class SafraFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Safra safra = (Safra) element;

		if (safra.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (FormatoHelper.dataFormat.format(safra.getDataInicio()).toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (FormatoHelper.dataFormat.format(safra.getDataInicio()).toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		if (safra.getDescricao().toLowerCase().matches(filtro.toLowerCase()))
			return true;
		
		return false;
	}
}
