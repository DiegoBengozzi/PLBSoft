package utils;

import java.util.List;

public interface PeixeUtils<T> {

	void salvar(T entidade);

	T buscar(Long id);

	List<T> buscarTodos();

}
