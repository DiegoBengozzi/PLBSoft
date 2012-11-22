package filtro;

import helper.FormatoHelper;
import modelo.SistemaProducao;

import org.eclipse.jface.viewers.Viewer;

public class SistemaProducaoFiltro extends TabelaFiltro{

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		
		if (verificar())
			return true;

		SistemaProducao sp = (SistemaProducao) element;

		if (sp.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (sp.getSistemaProducao().toLowerCase().matches(filtro.toLowerCase()))
			return true;
		
		if (FormatoHelper.getDecimalFormato().format(sp.getBiomasaCritica())
				.matches(filtro.toLowerCase()))
			return true;
		
		if (FormatoHelper.getDecimalFormato().format(sp.getBiomassaEconomica())
				.matches(filtro.toLowerCase()))
			return true;
		
		if (FormatoHelper.getDecimalFormato().format(sp.getCapacidadeSuporte())
				.matches(filtro.toLowerCase()))
			return true;
		
		return false;
	}

}
