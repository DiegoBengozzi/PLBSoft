package filtro;

import modelo.Doenca;

import org.eclipse.jface.viewers.Viewer;

public class DoencaFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Doenca doenca = (Doenca) element;

		if (doenca.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (doenca.getNome().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (doenca.getCausa().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (doenca.getSintoma().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (doenca.getTratamento().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		return false;
	}
}
