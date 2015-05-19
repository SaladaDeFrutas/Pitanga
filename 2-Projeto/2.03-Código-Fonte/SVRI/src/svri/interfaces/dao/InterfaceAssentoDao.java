package svri.interfaces.dao;

import java.util.List;

import svri.entidades.TipoAssento;



public interface InterfaceAssentoDao {

	public void adicionarAssento(TipoAssento umAssento);
	public void removerAssento(TipoAssento umAssento);
	public List<TipoAssento> listarAssentos();
	public void alterarAssento(TipoAssento umAssento);
	public TipoAssento buscarPorId(int Id);
}
