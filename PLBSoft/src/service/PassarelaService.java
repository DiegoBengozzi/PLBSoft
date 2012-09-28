package service;

import modelo.Passarela;
import DAO.PassarelaDAO;

public class PassarelaService {

	private PassarelaDAO dao = new PassarelaDAO();

	public void salvar(Passarela entidade) {
		dao.salvar(entidade);
	}
}
