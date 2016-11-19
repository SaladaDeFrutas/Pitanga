package br.ufg.inf.servicos;

import java.util.ArrayList;

import br.ufg.inf.entidades.Cliente;
import br.ufg.inf.entidades.Ingresso;
import br.ufg.inf.entidades.RegistroCompra;

public class Compra {
	InterfacePagamento tipoPagamento;
	
	public RegistroCompra calcularTotal(ArrayList<Ingresso> ingressos, RegistroCompra novoRegistroCompra){
		double valor = 0;
		
		for (Ingresso umIngresso : ingressos) {
			valor += umIngresso.getUmTipoIngresso().getPreco();
		}
		novoRegistroCompra.setValor(valor);
		return novoRegistroCompra;
	}
		
	public String efetuarPagamento(ArrayList<Ingresso> ingressos, RegistroCompra novoRegistroCompra, Cliente cliente){
		tipoPagamento = new PagamentoPagseguro();
		return tipoPagamento.realizaPagamento(ingressos, novoRegistroCompra, cliente);
		
	}
	public void gerarComprovante(){}
	public void gerarIngresso(){}
	
}
