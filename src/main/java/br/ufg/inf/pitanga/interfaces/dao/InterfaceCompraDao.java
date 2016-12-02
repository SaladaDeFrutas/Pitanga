package br.ufg.inf.pitanga.interfaces.dao;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;

import java.util.List;


public interface InterfaceCompraDao {

    public void adicionarCompra(Compra compra);

    public void removerCompra(Compra compra);

    public List<Compra> listarCompras();

    public void alterarCompra(Compra compra);

    public Compra buscarPorId(int Id);

    public List<Compra> buscaPorCliente(Cliente cliente);
}
