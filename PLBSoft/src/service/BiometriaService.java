package service;

import java.util.List;

import modelo.Biometria;
import DAO.BiometriaDAO;

public class BiometriaService {

	private BiometriaDAO dao = new BiometriaDAO();

	public void salvar(Biometria entidade) {
		dao.salvar(entidade);
	}

	public Biometria buscar(Long id) {
		return dao.buscar(id);
	}

	public List<Biometria> buscarTodos() {
		return dao.buscarTodos();
	}

	public List<Biometria> buscarTodosBiometriaAtivo() {
		return dao.buscarTodosPorStatus(true);
	}
}
