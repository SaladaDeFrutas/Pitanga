package br.ufg.inf.pitanga.interfaces.dao;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Ingresso;
import br.ufg.inf.pitanga.entidades.RegistroCompra;

import java.util.List;


public interface InterfaceIngressoDao {

    public void adicionarIngresso(Ingresso umIngresso);

    public void removerIngresso(Ingresso umIngresso);

    public List<Ingresso> listarIngressos();

    public void alterarIngresso(Ingresso umIngresso);

    public Ingresso buscarPorId(Long id);

    public List<Ingresso> buscaPorCliente(Cliente umCliente);

    public List<Ingresso> buscaPorRegistroCompra(RegistroCompra umRegistroCompra);
}
