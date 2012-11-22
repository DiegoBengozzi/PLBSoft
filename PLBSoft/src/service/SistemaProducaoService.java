package service;

import java.util.List;

import modelo.SistemaProducao;
import DAO.SistemaProducaoDAO;

public class SistemaProducaoService {

	private SistemaProducaoDAO dao = new SistemaProducaoDAO();

	public void salvar(SistemaProducao entidade) {
		dao.salvar(entidade);
	}
	public SistemaProducao buscar(Long id) {
		return dao.buscar(id);
	}

	public List<SistemaProducao> buscarTodos() {
		return dao.buscarTodos();
	}
	
	public List<SistemaProducao> buscarTodosSistemaProducaoAtivo(){
		return dao.buscarTodosPorStatus(true);
	}
}
