package filtro;

import helper.FormatoHelper;
import modelo.RegistroDoenca;

import org.eclipse.jface.viewers.Viewer;

public class RegistroDoencaFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		RegistroDoenca registroDoenca = (RegistroDoenca) element;

		if (registroDoenca.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.dataFormat.format(registroDoenca.getData())
				.toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (registroDoenca.getDescricao().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		if (registroDoenca.getLoteId().getNome().matches(filtro.toLowerCase()))
			return true;

		if (registroDoenca.getDoencaId().getNome()
				.matches(filtro.toLowerCase()))
			return true;

		return false;
	}
}
