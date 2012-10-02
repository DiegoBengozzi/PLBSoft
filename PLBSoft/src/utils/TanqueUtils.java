package utils;

import java.util.List;

import modelo.Tanque;

public interface TanqueUtils extends PadraoUtils<Tanque>{
	List<Tanque> buscarTodosPorStatus(Boolean b);
}
