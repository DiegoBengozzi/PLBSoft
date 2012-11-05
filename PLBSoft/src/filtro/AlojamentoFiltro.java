package filtro;

import modelo.Alojamento;

import org.eclipse.jface.viewers.Viewer;

public class AlojamentoFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;
		Alojamento alojamento = (Alojamento) element;
		
		if (alojamento.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (alojamento.getLoteA().getNome().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (alojamento.getHapaA().getNome().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (alojamento.getTanqueRedeA().getNome().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (alojamento.getTanqueA().getNome().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		return false;
	}
}
