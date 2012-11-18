package service;

import java.util.List;

import modelo.Hapa;
import modelo.Lote;
import DAO.HapaDAO;

public class HapaService {

	private HapaDAO dao = new HapaDAO();
	private LoteService loteService = new LoteService();

	public void salvar(Hapa entidade) {
		dao.salvar(entidade);
	}
	
	public Hapa buscar(Long id){
		return dao.buscar(id);
	}
	
	public List<Hapa> buscarTodos(){
		return dao.buscarTodos();
	}
	
	public List<Hapa> buscarTodosHapaAtivo(){
		return dao.buscarTodosPorStatus(true);
	}
	
	public List<Hapa> buscarTodasHapaLivre(){
		List<Hapa> t = buscarTodosHapaAtivo();
		List<Lote> l = loteService.buscarTodosLoteAtivo();
		for (Lote lote : l) {
			t.remove(lote.getTanqueId());
		}
		return t;
	}
}
