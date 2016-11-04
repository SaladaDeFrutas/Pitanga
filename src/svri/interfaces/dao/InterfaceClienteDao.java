package svri.interfaces.dao;

import java.util.List;

import svri.entidades.Cliente;

public interface InterfaceClienteDao {

	public void adicionarCliente(Cliente umCliente);
	public void removerCliente(Cliente umCliente);
	public List<Cliente> listarCliente();
	public void alterarCliente(Cliente umCliente);
	public Cliente buscarPorId(String email);
	public boolean checarCliente(Cliente cliente);
}
