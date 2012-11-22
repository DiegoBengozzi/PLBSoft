package service;

import java.util.List;

import modelo.Lote;
import modelo.Tanque;
import DAO.TanqueDAO;

public class TanqueService {

	private TanqueDAO dao = new TanqueDAO();
	private LoteService loteService = new LoteService();

	public void salvar(Tanque entidade) {
		dao.salvar(entidade);
	}

	public Tanque buscar(Long id) {
		return dao.buscar(id);
	}

	public List<Tanque> buscarTodos() {
		return dao.buscarTodos();
	}

	public List<Tanque> buscarTodosTanqueAtivo() {
		return dao.buscarTodosPorStatus(true);
	}

	public List<Tanque> buscarTodosTanqueLivre() {
		List<Tanque> t = buscarTodosTanqueAtivo();
		List<Lote> l = loteService.buscarTodosLoteAtivo();
		for (Lote lote : l) {
			t.remove(lote.getTanqueId());
		}
		return t;
	}
}
