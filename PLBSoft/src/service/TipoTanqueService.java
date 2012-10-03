package service;

import java.util.List;

import modelo.TipoTanque;
import DAO.TipoTanqueDAO;

public class TipoTanqueService {

	private TipoTanqueDAO dao = new TipoTanqueDAO();
	
	public void salvar(TipoTanque entidade) {
		dao.salvar(entidade);

	}

	public TipoTanque buscar(Long id) {
		return dao.buscar(id);
	}

	public List<TipoTanque> buscarTodos() {
		return dao.buscarTodos();
	}
	
	public List<TipoTanque> buscarTodosTipoTanqueAtivo(){
		return dao.buscarTodosPorStatus(true);
	}
}
