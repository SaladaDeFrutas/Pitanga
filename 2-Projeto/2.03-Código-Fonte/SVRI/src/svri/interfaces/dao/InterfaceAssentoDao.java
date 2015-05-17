package svri.interfaces.dao;

import java.util.List;

import svri.entidades.Assento;



public interface InterfaceAssentoDao {

	public void adicionarAssento(Assento umAssento);
	public void removerAssento(Assento umAssento);
	public List<Assento> listarAssentos();
	public void alterarAssento(Assento umAssento);
	public Assento buscarPorId(int Id);
}
