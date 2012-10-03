package filtro;

import modelo.TipoTanque;

import org.eclipse.jface.viewers.Viewer;

public class TipoTanqueFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		TipoTanque tipoTanque = (TipoTanque) element;

		if (tipoTanque.getNome().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (tipoTanque.getRevestimento().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		return false;
	}

}
