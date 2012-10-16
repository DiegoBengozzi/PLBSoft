package filtro;

import helper.FormatoHelper;
import modelo.Especie;

import org.eclipse.jface.viewers.Viewer;

public class EspecieFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Especie especie = (Especie) element;

		if (especie.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		if (especie.getEspecie().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (especie.getLinhegem().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (especie.getHibrido().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (especie.getGenero().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.getDecimalFormato()
				.format(especie.getMaturacaoSexual())
				.matches(filtro.toLowerCase()))
			return true;

		if (especie.getToleranciaSalinidade().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		if (especie.getToleranciaFrio().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		return false;
	}

}
