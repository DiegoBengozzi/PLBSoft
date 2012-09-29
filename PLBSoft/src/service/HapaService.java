package service;

import modelo.Hapa;
import DAO.HapaDAO;

public class HapaService {

	private HapaDAO dao = new HapaDAO();

	public void salvar(Hapa entidade) {
		dao.salvar(entidade);
	}
}
