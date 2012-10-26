package DAO;

import java.util.List;
import modelo.Safra;
import org.hibernate.Query;
import utils.SafraUtils;
import conexao.HibernateConnection;

public class SafraDAO extends HibernateConnection implements SafraUtils {

	@Override
	public void salvar(Safra entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
	}

	@Override
	public Safra buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from Safra t where t.id=:id");
		q.setParameter("id", id);
		return (Safra) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Safra> buscarTodos() {
		Query q = getSession().createQuery("select t from Safra t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Safra> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Safra t where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
