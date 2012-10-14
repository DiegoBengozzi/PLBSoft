package service;

import java.util.List;

import modelo.Adubacao;
import DAO.AdubacaoDAO;

public class AdubacaoService {

	private AdubacaoDAO dao = new AdubacaoDAO();
	
	public void salvar(Adubacao entidade) {
		dao.salvar(entidade);
	}
	public Adubacao buscar(Long id) {
		return dao.buscar(id);
	}

	public List<Adubacao> buscarTodos() {
		return dao.buscarTodos();
	}
	
	public List<Adubacao> buscarTodosAdubacaoAtivo(){
		return dao.buscarTodosPorStatus(true);
	}
}
