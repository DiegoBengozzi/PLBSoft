package DAO;

import java.util.List;

import modelo.Alojamento;
import org.hibernate.Query;
import utils.AlojamentoUtils;
import conexao.HibernateConnection;

public class AlojamentoDAO extends HibernateConnection implements
		AlojamentoUtils {

	@Override
	public void salvar(Alojamento entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);

	}

	@Override
	public Alojamento buscar(Long id) {
		Query q = getSession().createQuery(
				"select t from Alojamento t where t.id=:id");
		q.setParameter("id", id);
		return (Alojamento) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Alojamento> buscarTodos() {
		Query q = getSession().createQuery("select t from Alojamento t");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Alojamento> buscarTodosPorStatus(Boolean b) {
		Query q = getSession().createQuery(
				"select t from Alojamento t  where t.status is :aux");
		q.setParameter("aux", b);
		return q.list();
	}

}
