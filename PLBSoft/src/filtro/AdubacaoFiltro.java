package filtro;

import helper.FormatoHelper;
import modelo.Adubacao;

import org.eclipse.jface.viewers.Viewer;

public class AdubacaoFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Adubacao adubacao = (Adubacao) element;

		if (adubacao.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		if (adubacao.getTanqueId().getNome().matches(filtro.toLowerCase()))
			return true;

		if (adubacao.getDescricao().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.dataFormat.format(adubacao.getData()).toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		return false;
	}

}
