package br.ufg.inf.interfaces.dao;

import br.ufg.inf.entidades.Cliente;
import br.ufg.inf.entidades.RegistroCompra;

import java.util.List;


public interface InterfaceRegistroCompraDao {

    public void adicionarRegistroCompra(RegistroCompra umRegistroCompra);

    public void removerRegistroCompra(RegistroCompra umRegistroCompra);

    public List<RegistroCompra> listarRegistroCompra();

    public void alterarRegistroCompra(RegistroCompra umRegistroCompra);

    public RegistroCompra buscarPorId(int Id);

    public List<RegistroCompra> buscaPorCliente(Cliente umCliente);
}
