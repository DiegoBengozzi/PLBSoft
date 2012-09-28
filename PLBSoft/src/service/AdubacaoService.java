package service;

import modelo.Adubacao;
import DAO.AdubacaoDAO;

public class AdubacaoService {

	private AdubacaoDAO dao = new AdubacaoDAO();
	
	public void salvar(Adubacao entidade) {
		dao.salvar(entidade);
	}
	
}
