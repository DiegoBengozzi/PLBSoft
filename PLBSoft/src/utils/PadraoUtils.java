package utils;

import java.util.List;

public interface PadraoUtils<T> {

	void salvar(T entidade);

	T buscar(Long id);

	List<T> buscarTodos();

}
