package service;

import modelo.Especie;
import DAO.EspecieDAO;

public class EspecieService {

	private EspecieDAO dao = new EspecieDAO();
	
	public void salvar(Especie entidade) {
		dao.salvar(entidade);
	}
}
