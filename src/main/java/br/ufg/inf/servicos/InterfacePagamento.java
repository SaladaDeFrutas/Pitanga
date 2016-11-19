package br.ufg.inf.servicos;

import br.ufg.inf.entidades.Cliente;
import br.ufg.inf.entidades.Ingresso;
import br.ufg.inf.entidades.RegistroCompra;

import java.util.ArrayList;

public interface InterfacePagamento {
	String realizaPagamento(ArrayList<Ingresso> ingressos,
			RegistroCompra novoRegistroCompra, Cliente cliente);
}
