package DAO;

import java.util.List;

import modelo.Adubacao;
import utils.AdubacaoUtils;
import conexao.HibernateConnection;

public class AdubacaoDAO extends HibernateConnection implements AdubacaoUtils{

	@Override
	public void salvar(Adubacao entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
	}

	@Override
	public Adubacao buscar(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adubacao> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
