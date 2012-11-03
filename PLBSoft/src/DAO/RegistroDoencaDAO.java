package DAO;

import java.util.List;

import modelo.RegistroDoenca;

import org.hibernate.Query;

import utils.RegistroDoencaUtils;
import conexao.HibernateConnection;

public class RegistroDoencaDAO extends HibernateConnection implements
		RegistroDoencaUtils {

	@Override
	public void salvar(RegistroDoenca entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);

	}

	@Override
	public RegistroDoenca buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from RegistroDoenca t where t.id=:id");
		q.setParameter("id", id);
		return (RegistroDoenca) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroDoenca> buscarTodos() {
		Query q = getSession().createQuery("select t from RegistroDoenca t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroDoenca> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from RegistroDoenca t  where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
