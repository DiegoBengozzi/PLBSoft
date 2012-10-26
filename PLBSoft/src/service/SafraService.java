package service;

import java.util.List;

import modelo.Safra;
import DAO.SafraDAO;

public class SafraService {
	
	private SafraDAO dao = new SafraDAO();

	public void salvar(Safra entidade) {
		dao.salvar(entidade);
	}
	
	public Safra buscar(Long id){
		return dao.buscar(id);
	}
	
	public List<Safra> buscarTodos(){
		return dao.buscarTodos();
	}
	
	public List<Safra> buscarTodosSafraAtivo(){
		return dao.buscarTodosPorStatus(true);
	}

}
