package DAO;

import java.util.List;

import modelo.SistemaProducao;
import utils.SistemaProducaoUtils;
import conexao.HibernateConnection;

public class SistemaProducaoDAO extends HibernateConnection implements SistemaProducaoUtils{

	@Override
	public void salvar(SistemaProducao entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);		
	}

	@Override
	public SistemaProducao buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SistemaProducao> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
