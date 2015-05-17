package svri.interfaces.dao;

import java.util.List;

import svri.entidades.Cliente;
import svri.entidades.Ingresso;

public interface InterfaceIngressoDao {

	public void adicionarIngresso(Ingresso umIngresso);
	public void removerIngresso(Ingresso umIngresso);
	public List<Ingresso> listarIngressos();
	public void alterarIngresso(Ingresso umIngresso);
	public Ingresso buscarPorId(int Id);
	public List<Cliente> buscaPorCliente(Cliente umCliente);
}
