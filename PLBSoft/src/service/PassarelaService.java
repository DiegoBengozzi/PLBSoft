package service;

import java.util.List;

import modelo.Passarela;
import modelo.Tanque;
import DAO.PassarelaDAO;
import DAO.TanqueDAO;

public class PassarelaService {

	private PassarelaDAO dao = new PassarelaDAO();
	private TanqueDAO tanqueDAO = new TanqueDAO();

	public void salvar(Passarela entidade) {
		dao.salvar(entidade);
	}
	
	public Passarela buscar(Long id){
		return dao.buscar(id);
	}
	
	public List<Passarela> buscarTodos(){
		return dao.buscarTodos();
	}
	
	public List<Passarela> buscarTodosPassarelaAtivo(){
		return dao.buscarTodosPorStatus(true);
	}
	
	@SuppressWarnings("null")
	public List<Passarela> buscarPassarelaTanque(Tanque id){
		List<Passarela> p = buscarTodosPassarelaAtivo();
		List <Passarela> x = null;
		for (Passarela passarela : p) {
			if (passarela.getTanqueId() == tanqueDAO.buscar(id.getId())) {
				x.add(passarela);
			}
		}
		return x;
	}
	
}
