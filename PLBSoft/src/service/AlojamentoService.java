package service;

import java.util.List;

import modelo.Alojamento;
import DAO.AlojamentoDAO;

public class AlojamentoService {

	private AlojamentoDAO dao = new AlojamentoDAO();

	public void salvar(Alojamento entidade) {
		dao.salvar(entidade);
	}

	public Alojamento buscar(Long id) {
		return dao.buscar(id);
	}

	public List<Alojamento> buscarTodos() {
		return dao.buscarTodos();
	}

	public List<Alojamento> buscarTodosAlojamentoAtivo() {
		return dao.buscarTodosPorStatus(true);
	}
}
