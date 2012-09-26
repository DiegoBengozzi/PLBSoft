package service;

import java.util.List;

import modelo.TanqueRede;
import DAO.TanqueRedeDAO;

public class TanqueRedeService {

	private TanqueRedeDAO dao = new TanqueRedeDAO();

	public void salvar(TanqueRede entidade) {
		dao.salvar(entidade);

	}

	public TanqueRede buscar(String nome) {
		return dao.buscar(nome);
	}

	public List<TanqueRede> buscarTodos() {
		return dao.buscarTodos();
	}

}
