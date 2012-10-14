package filtro;

import helper.FormatoHelper;
import modelo.Adubacao;

import org.eclipse.jface.viewers.Viewer;

public class AdubacaoFiltro extends TabelaFiltro{

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Adubacao adubacao = (Adubacao) element;

		if (adubacao.getId().toString().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (FormatoHelper.getDecimalFormato().format(adubacao.getTanqueId())
				.matches(filtro.toLowerCase()))
			return true;

		if (adubacao.getDescricao().toLowerCase().matches(filtro.toLowerCase()))
			return true;

//		if (adubacao.getData().toLowerCase().matches(filtro.toLowerCase()))
//			return true;
		
		return false;
	}

}
