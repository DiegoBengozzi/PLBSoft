package DAO;

import java.util.List;
import modelo.TipoTanque;
import org.hibernate.Query;
import utils.TipoTanqueUtils;
import conexao.HibernateConnection;

public class TipoTanqueDAO extends HibernateConnection implements TipoTanqueUtils {

	@Override
	public void salvar(TipoTanque entidade) {
		if (entidade.getId() != null)
			merge(entidade);
		else
			persist(entidade);		
	}

	@Override
	public TipoTanque buscar(String nome) {
		Query q = getSession().createQuery(
				"select t from TipoTanque t where t.nome=:nome");
		q.setParameter("nome", nome);		
		return (TipoTanque) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoTanque> buscarTodos() {
		Query q = getSession().createQuery("select t from TipoTanque t");
		return q.list();
	}

}