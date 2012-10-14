package DAO;

import java.util.List;

import org.hibernate.Query;

import modelo.Adubacao;
import utils.AdubacaoUtils;
import conexao.HibernateConnection;

public class AdubacaoDAO extends HibernateConnection implements AdubacaoUtils {

	@Override
	public void salvar(Adubacao entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
	}

	@Override
	public Adubacao buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from Adubacao t where t.id=:id");
		q.setParameter("id", id);
		return (Adubacao) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Adubacao> buscarTodos() {
		Query q = getSession().createQuery("select t from Adubacao t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Adubacao> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Adubacao t  where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
