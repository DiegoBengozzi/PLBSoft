package filtro;

import org.eclipse.jface.viewers.ViewerFilter;

public abstract class TanqueRedeFiltro extends ViewerFilter{
	
	private String filtro;

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = ".*"+filtro+".*";
	}
	 public Boolean verificar() {
		return filtro == null || filtro.isEmpty();
	}
	
	 

}
