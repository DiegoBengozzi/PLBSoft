package utils;

import java.util.List;

import modelo.TanqueRede;

public interface TanqueRedeUtils extends PadraoUtils<TanqueRede> {

	List<TanqueRede> buscarTodosPorStatus(Boolean b);
	
}
