package DAO;

import java.util.List;

import modelo.Hapa;

import org.hibernate.Query;

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
		Query q = getSession().createQuery(
				"select t from Hapa t where t.id=:id");
		q.setParameter("id", id);
		return (Hapa) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hapa> buscarTodos() {
		Query q = getSession().createQuery("select t from Hapa t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hapa> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Hapa t where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
