package service;

import java.util.List;

import modelo.TipoTanque;
import DAO.TipoTanqueDAO;

public class TipoTanqueService {

	private TipoTanqueDAO dao = new TipoTanqueDAO();
	
	public void salvar(TipoTanque entidade) {
		dao.salvar(entidade);

	}

	public TipoTanque buscar(String nome) {
		return dao.buscar(nome);
	}

	public List<TipoTanque> buscarTodos() {
		return dao.buscarTodos();
	}
}
