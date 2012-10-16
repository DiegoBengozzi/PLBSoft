package DAO;

import java.util.List;
import modelo.Tanque;
import org.hibernate.Query;
import utils.TanqueUtils;
import conexao.HibernateConnection;

public class TanqueDAO extends HibernateConnection implements TanqueUtils {

	@Override
	public void salvar(Tanque entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
	}

	@Override
	public Tanque buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from Tanque t where t.id=:id");
		q.setParameter("id", id);
		return (Tanque) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tanque> buscarTodos() {
		Query q = getSession().createQuery("select t from Tanque t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tanque> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Tanque t where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
