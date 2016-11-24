package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Ingresso;
import br.ufg.inf.pitanga.entidades.RegistroCompra;

import java.util.ArrayList;

public interface InterfacePagamento {
    String realizaPagamento(ArrayList<Ingresso> ingressos,
                            RegistroCompra novoRegistroCompra, Cliente cliente);
}
