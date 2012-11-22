package service;

import java.util.List;

import modelo.Doenca;
import DAO.DoencaDAO;

public class DoencaService {
	private DoencaDAO dao = new DoencaDAO();

	public void salvar(Doenca entidade) {
		dao.salvar(entidade);
	}

	public Doenca buscar(Long id) {
		return dao.buscar(id);
	}

	public List<Doenca> buscarTodos() {
		return dao.buscarTodos();
	}

	public List<Doenca> buscarTodosDoencaAtivo() {
		return dao.buscarTodosPorStatus(true);
	}

}
