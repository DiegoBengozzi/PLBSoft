package DAO;

import java.util.List;

import modelo.Doenca;

import org.hibernate.Query;

import utils.DoencaUtils;
import conexao.HibernateConnection;

public class DoencaDAO extends HibernateConnection implements DoencaUtils {

	@Override
	public void salvar(Doenca entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
		
	}

	@Override
	public Doenca buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from Doenca t where t.id=:id");
		q.setParameter("id", id);
		return (Doenca) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Doenca> buscarTodos() {
		Query q = getSession().createQuery("select t from Doenca t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Doenca> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Doenca t  where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
