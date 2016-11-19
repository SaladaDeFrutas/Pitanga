package br.ufg.inf.interfaces.dao;

import br.ufg.inf.entidades.Cliente;

import java.util.List;

public interface InterfaceClienteDao {

	public void adicionarCliente(Cliente umCliente);
	public void removerCliente(Cliente umCliente);
	public List<Cliente> listarCliente();
	public void alterarCliente(Cliente umCliente);
	public Cliente buscarPorId(String email);
	public boolean checarCliente(Cliente cliente);
}
