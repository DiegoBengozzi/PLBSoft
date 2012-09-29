package DAO;

import java.util.List;

import modelo.Passarela;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passarela> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
