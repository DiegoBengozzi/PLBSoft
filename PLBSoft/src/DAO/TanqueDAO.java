package DAO;

import java.util.List;

import org.hibernate.Query;

import modelo.Tanque;

import utils.TanqueUtils;
import conexao.HibernateConnection;

public class TanqueDAO extends HibernateConnection implements TanqueUtils{

	@Override
	public void salvar(Tanque entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);		
	}

	@Override
	public Tanque buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tanque> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Tanque> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery("select t from Tanque t  where t.status is :aux");
		q.setParameter("aux", b);
		return null;
	}

}
