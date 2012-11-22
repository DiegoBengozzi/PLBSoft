package service;

import java.util.List;

import modelo.Especie;
import DAO.EspecieDAO;

public class EspecieService {

	private EspecieDAO dao = new EspecieDAO();
	
	public void salvar(Especie entidade) {
		dao.salvar(entidade);
	}
	public Especie buscar(Long id) {
		return dao.buscar(id);
	}

	public List<Especie> buscarTodos() {
		return dao.buscarTodos();
	}
	
	public List<Especie> buscarTodosEspecieAtivo(){
		return dao.buscarTodosPorStatus(true);
	}
}
