package filtro;

import helper.FormatoHelper;
import modelo.Tanque;

import org.eclipse.jface.viewers.Viewer;

public class TanqueFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Tanque tanque = (Tanque) element;

		if (tanque.getId().toString().toLowerCase().matches(filtro.toLowerCase()))
			return true;
		
		if (tanque.getNome().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.getDecimalFormato().format(tanque.getLaminaAgua())
				.matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.getDecimalFormato().format(tanque.getProfundidade())
				.matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.getDecimalFormato()
				.format(tanque.getAcessibilidade())
				.matches(filtro.toLowerCase()))
			return true;

		if (tanque.getDescricao().toLowerCase().matches(filtro.toLowerCase())) 
			return true; 
		

		return false;
	}
}
