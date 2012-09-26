package utils;

import java.util.List;

public interface PeixeUtils<T> {

	void salvar(T entidade);

	T buscar(String nome);

	List<T> buscarTodos();

}
