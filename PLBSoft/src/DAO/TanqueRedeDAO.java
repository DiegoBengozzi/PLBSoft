package DAO;

import java.util.List;

import modelo.TanqueRede;

import org.hibernate.Query;

import utils.TanqueRedeUtils;
import conexao.HibernateConnection;

public class TanqueRedeDAO extends HibernateConnection implements TanqueRedeUtils {

	@Override
	public void salvar(TanqueRede entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
	}

	@Override
	public TanqueRede buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from TanqueRede t where t.id =:id");
		q.setParameter("id", id);
		return (TanqueRede) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TanqueRede> buscarTodos() {
		Query q = getSession().createQuery("select t from TanqueRede t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TanqueRede> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery("select t from TanqueRede t  where t.status is :aux");
		q.setParameter("aux", b);
		return  q.list();
	}


}
