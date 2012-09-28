package service;

import modelo.Tanque;
import DAO.TanqueDAO;

public class TanqueService {

	private TanqueDAO dao = new TanqueDAO();

	public void salvar(Tanque entidade) {
		dao.salvar(entidade);
	}
}
