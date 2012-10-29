package filtro;

import modelo.Lote;

import org.eclipse.jface.viewers.Viewer;

public class LoteFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Lote lote = (Lote) element;
		if (lote.getId().toString().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		return false;
	}

}
