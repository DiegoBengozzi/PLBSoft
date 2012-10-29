package service;

import java.util.List;

import modelo.Lote;
import DAO.LoteDAO;

public class LoteService {

	private LoteDAO dao = new LoteDAO();

	public void salvar(Lote entidade) {
		dao.salvar(entidade);
	}

	public Lote buscar(Long id) {
		return dao.buscar(id);
	}

	public List<Lote> buscarTodos() {
		return dao.buscarTodos();
	}

	public List<Lote> buscarTodosLoteAtivo() {
		return dao.buscarTodosPorStatus(true);
	}

}
