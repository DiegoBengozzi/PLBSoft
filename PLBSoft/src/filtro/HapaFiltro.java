package filtro;

import helper.FormatoHelper;
import modelo.Hapa;

import org.eclipse.jface.viewers.Viewer;

public class HapaFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Hapa hapa = (Hapa) element;

		if (hapa.getId().toString().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (hapa.getNome().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.getDecimalFormato().format(hapa.getTamanho())
				.matches(filtro.toLowerCase()))
			return true;

		if (hapa.getPassarelaId().getNome().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		return false;
	}

}
