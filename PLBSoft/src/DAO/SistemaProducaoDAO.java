package DAO;

import java.util.List;

import modelo.SistemaProducao;

import org.hibernate.Query;

import utils.SistemaProducaoUtils;
import conexao.HibernateConnection;

public class SistemaProducaoDAO extends HibernateConnection implements SistemaProducaoUtils{

	@Override
	public void salvar(SistemaProducao entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);		
	}

	@Override
	public SistemaProducao buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from SistemaProducao t where t.id=:id");
		q.setParameter("id", id);
		return (SistemaProducao) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SistemaProducao> buscarTodos() {
		Query q = getSession().createQuery("select t from SistemaProducao t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SistemaProducao> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from SistemaProducao t where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
