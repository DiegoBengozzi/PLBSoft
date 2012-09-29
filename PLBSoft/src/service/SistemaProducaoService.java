package service;

import modelo.SistemaProducao;
import DAO.SistemaProducaoDAO;

public class SistemaProducaoService {

	private SistemaProducaoDAO dao = new SistemaProducaoDAO();

	public void salvar(SistemaProducao entidade) {
		dao.salvar(entidade);
	}
}
