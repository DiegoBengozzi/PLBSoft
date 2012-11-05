package filtro;

import modelo.Biometria;

import org.eclipse.jface.viewers.Viewer;

public class BiometriaFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Biometria biometria = (Biometria) element;

		if (biometria.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		return false;
	}

}
