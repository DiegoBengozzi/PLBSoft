package service;

import modelo.TanqueRede;
import DAO.TanqueRedeDAO;

public class TanqueRedeService{
	
	private TanqueRedeDAO dao = new TanqueRedeDAO();
	
	public void salvar(TanqueRede entidade){
		dao.salvar(entidade);
		
	}
	
	public TanqueRede buscar(Long id){
		return (TanqueRede) TanqueRede;
	}
	 
}
