package service;

import java.util.List;

import modelo.RegistroDoenca;
import DAO.RegistroDoencaDAO;

public class RegistroDoencaService {

	private RegistroDoencaDAO dao = new RegistroDoencaDAO();

	public void salvar(RegistroDoenca entidade) {
		dao.salvar(entidade);
	}

	public RegistroDoenca buscar(Long id) {
		return dao.buscar(id);
	}

	public List<RegistroDoenca> buscarTodos() {
		return dao.buscarTodos();
	}

	public List<RegistroDoenca> buscarTodosRegistroDoencaAtivo() {
		return dao.buscarTodosPorStatus(true);
	}

}
