package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;

@FunctionalInterface
public interface InterfacePagamento {
    void realizaPagamento(Compra novaCompra, Cliente cliente);
}
