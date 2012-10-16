package DAO;

import java.util.List;
import modelo.Especie;
import org.hibernate.Query;
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
		Query q = getSession().createQuery(
				"select t from Especie t where t.id=:id");
		q.setParameter("id", id);
		return (Especie) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Especie> buscarTodos() {
		Query q = getSession().createQuery("select t from Especie t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Especie> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Especie t where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}
}
