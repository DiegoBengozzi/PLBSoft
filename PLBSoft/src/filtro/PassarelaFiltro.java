package filtro;

import modelo.Passarela;

import org.eclipse.jface.viewers.Viewer;

public class PassarelaFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		
		if (verificar())
			return true;
		
		Passarela passarela = (Passarela) element;
		
		if (passarela.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (passarela.getNome().toLowerCase().matches(filtro.toLowerCase()))
			return true;
		
		if (passarela.getCapacidade().toString().matches(filtro.toLowerCase()))
			return true;
		
		if (passarela.getTanqueId().toString().matches(filtro.toLowerCase()))
				return true;
		
		return false;
	}
}
