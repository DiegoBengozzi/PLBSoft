package filtro;

import org.eclipse.jface.viewers.ViewerFilter;

public abstract class TabelaFiltro extends ViewerFilter{
	
	protected String filtro;

	public void setFiltro(String filtro) {
		this.filtro = ".*"+filtro+".*";
	}
	 public Boolean verificar() {
		return filtro == null || filtro.isEmpty();
	}
}