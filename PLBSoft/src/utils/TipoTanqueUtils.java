package utils;

import java.util.List;

import modelo.TipoTanque;

public interface TipoTanqueUtils extends PadraoUtils<TipoTanque> {
	List<TipoTanque> buscarTodosPorStatus(Boolean b);
}
