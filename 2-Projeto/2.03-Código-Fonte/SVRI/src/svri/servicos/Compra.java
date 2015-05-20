package svri.servicos;

import java.util.ArrayList;

import svri.entidades.Cliente;
import svri.entidades.Ingresso;
import svri.entidades.RegistroCompra;

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
	
	public void cancelarCompra(){
		
		
	}
	
	public String efetuarPagamento(ArrayList<Ingresso> ingressos, RegistroCompra novoRegistroCompra, Cliente cliente){
		tipoPagamento = new PagamentoPagseguro();
		return tipoPagamento.realizaPagamento(ingressos, novoRegistroCompra, cliente);
		
	}
	public void gerarComprovante(){}
	public void gerarIngresso(){}
	
}
