package DAO;

import java.util.List;

import modelo.Especie;
import utils.EspecieUtils;
import conexao.HibernateConnection;

public class EspecieDAO extends HibernateConnection implements EspecieUtils{

	@Override
	public void salvar(Especie entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
	}

	@Override
	public Especie buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Especie> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
