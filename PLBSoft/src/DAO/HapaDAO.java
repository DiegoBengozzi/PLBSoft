package DAO;

import java.util.List;

import modelo.Hapa;
import utils.HapaUtils;
import conexao.HibernateConnection;

public class HapaDAO extends HibernateConnection implements HapaUtils{

	@Override
	public void salvar(Hapa entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
	}

	@Override
	public Hapa buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hapa> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hapa> buscarTodosPorStatus(Boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

}
