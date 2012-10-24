package DAO;

import java.util.List;

import modelo.Passarela;

import org.hibernate.Query;

import utils.PassarelaUtils;
import conexao.HibernateConnection;

public class PassarelaDAO extends HibernateConnection implements PassarelaUtils{

	@Override
	public void salvar(Passarela entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);		
	}

	@Override
	public Passarela buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from Passarela t where t.id=:id");
		q.setParameter("id", id);
		return (Passarela) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passarela> buscarTodos() {
		Query q = getSession().createQuery("select t from Passarela t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passarela> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Passarela t where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
