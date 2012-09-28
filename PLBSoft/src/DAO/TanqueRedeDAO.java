package DAO;

import java.util.List;

import modelo.TanqueRede;

import org.hibernate.Query;

import utils.TanqueRedeUtils;
import conexao.HibernateConnection;

public class TanqueRedeDAO extends HibernateConnection implements
		TanqueRedeUtils {

	@Override
	public void salvar(TanqueRede entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);
	}

	@Override
	public TanqueRede buscar(String nome) {
		Query q = getSession().createQuery(
				"select t from TanqueRede t where t.nome=:nome");
		q.setParameter("nome", nome);
		return (TanqueRede) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TanqueRede> buscarTodos() {
		Query q = getSession().createQuery("select t from TanqueRede t");
		return q.list();
	}


}
