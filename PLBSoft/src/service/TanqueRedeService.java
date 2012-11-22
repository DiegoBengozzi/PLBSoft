package service;

import java.util.List;

import modelo.Lote;
import modelo.Tanque;
import modelo.TanqueRede;
import DAO.TanqueRedeDAO;

public class TanqueRedeService {

	private TanqueRedeDAO dao = new TanqueRedeDAO();
	private LoteService loteService = new LoteService();
	
	public void salvar(TanqueRede entidade) {
		dao.salvar(entidade);
	}

	public TanqueRede buscar(Long id) {
		return dao.buscar(id);
	}

	public List<TanqueRede> buscarTodos() {
		return dao.buscarTodos();
	}
	
	public List<TanqueRede> buscarTodosTanqueRedeAtivo(){
		return dao.buscarTodosPorStatus(true);
	}
	
	public List<TanqueRede> buscarTodosTanqueRedeLivre(){
		List<TanqueRede> t = buscarTodosTanqueRedeAtivo();
		List<Lote> l = loteService.buscarTodosLoteAtivo();
		for (Lote lote : l) {
			t.remove(lote.getTanqueRedeId());
		}
		return t;
	}
	
	public List<TanqueRede> buscarTanqueRedeTanque(Tanque t){
		return dao.buscarTRT(t);
	}

}
