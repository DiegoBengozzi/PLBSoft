package filtro;

import helper.FormatoHelper;
import modelo.Lote;

import org.eclipse.jface.viewers.Viewer;

public class LoteFiltro extends TabelaFiltro {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (verificar())
			return true;

		Lote lote = (Lote) element;

		if (lote.getId().toString().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (lote.getNome().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (lote.getDescricao().toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.getDecimalFormato().format(lote.getQuantidadePeixe())
				.matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.dataFormat.format(lote.getDataInicioLote())
				.toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.dataFormat.format(lote.getDataFimLote())
				.toLowerCase().matches(filtro.toLowerCase()))
			return true;

		if (FormatoHelper.dataFormat.format(lote.getSafraId()).toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		if (lote.getEspecieId().getEspecie().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (lote.getTanqueId().getNome().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;

		if (lote.getTanqueRedeId().getNome().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (lote.getHapaId().getNome().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		if (lote.getHapaId().getPassarelaId().getNome().toLowerCase()
				.matches(filtro.toLowerCase()))
			return true;
		
		return false;
	}

}
