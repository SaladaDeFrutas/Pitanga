package br.ufg.inf.pitanga.interfaces.dao;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.entidades.Ingresso;

import java.util.List;


public interface InterfaceIngressoDao {

    public void adicionarIngresso(Ingresso umIngresso);

    public void removerIngresso(Ingresso umIngresso);

    public List<Ingresso> listarIngressos();

    public void alterarIngresso(Ingresso umIngresso);

    public Ingresso buscarPorId(int Id);

    public List<Ingresso> buscaPorCliente(Cliente umCliente);

    public List<Ingresso> buscaPorRegistroCompra(Compra compra);
}
