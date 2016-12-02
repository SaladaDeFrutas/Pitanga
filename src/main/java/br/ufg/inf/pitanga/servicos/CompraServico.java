package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.entidades.Ingresso;

import java.util.ArrayList;

public class CompraServico {
    InterfacePagamento tipoPagamento;

    public Compra calcularTotal(ArrayList<Ingresso> ingressos, Compra novaCompra) {
        double valor = 0;

        for (Ingresso umIngresso : ingressos) {
            valor += umIngresso.getUmTipoIngresso().getPreco();
        }
        novaCompra.setValor(valor);
        return novaCompra;
    }

    public String efetuarPagamento(ArrayList<Ingresso> ingressos, Compra novaCompra, Cliente cliente) {
        tipoPagamento = new PagamentoPagseguro();
        return tipoPagamento.realizaPagamento(ingressos, novaCompra, cliente);

    }

    public void gerarComprovante() {
    }

    public void gerarIngresso() {
    }

}
