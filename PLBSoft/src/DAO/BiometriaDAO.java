package DAO;

import java.util.List;

import modelo.Biometria;

import org.hibernate.Query;

import utils.BiometriaUtils;
import conexao.HibernateConnection;

public class BiometriaDAO extends HibernateConnection implements BiometriaUtils {

	@Override
	public void salvar(Biometria entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
	}

	@Override
	public Biometria buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from Biometria t where t.id=:id");
		q.setParameter("id", id);
		return (Biometria) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Biometria> buscarTodos() {
		Query q = getSession().createQuery("select t from Biometria t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Biometria> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Biometria t  where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
