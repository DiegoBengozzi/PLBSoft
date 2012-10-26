package service;

import java.util.List;

import modelo.Hapa;
import DAO.HapaDAO;

public class HapaService {

	private HapaDAO dao = new HapaDAO();

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
}
