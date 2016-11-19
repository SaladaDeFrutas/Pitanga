package br.ufg.inf.interfaces.dao;

import br.ufg.inf.entidades.Cliente;
import br.ufg.inf.entidades.Ingresso;
import br.ufg.inf.entidades.RegistroCompra;

import java.util.List;


public interface InterfaceIngressoDao {

	public void adicionarIngresso(Ingresso umIngresso);
	public void removerIngresso(Ingresso umIngresso);
	public List<Ingresso> listarIngressos();
	public void alterarIngresso(Ingresso umIngresso);
	public Ingresso buscarPorId(int Id);
	public List<Ingresso> buscaPorCliente(Cliente umCliente);
	public List<Ingresso> buscaPorRegistroCompra(RegistroCompra umRegistroCompra);
}
