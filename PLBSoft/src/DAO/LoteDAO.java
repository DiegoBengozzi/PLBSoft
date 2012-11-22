package DAO;

import java.util.List;

import modelo.Lote;

import org.hibernate.Query;

import utils.LoteUtils;
import conexao.HibernateConnection;

public class LoteDAO extends HibernateConnection implements LoteUtils {

	@Override
	public void salvar(Lote entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);

	}

	@Override
	public Lote buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from Lote t where t.id=:id");
		q.setParameter("id", id);
		return (Lote) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lote> buscarTodos() {
		Query q = getSession().createQuery("select t from Lote t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lote> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Lote t  where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

	// @SuppressWarnings("unchecked")
	// public List<Tanque> buscarTanqueUsados(){
	// Query q = getSession().createQuery(
	// "select t from Lote t where t.");
	// return q.list();
	// }

}
