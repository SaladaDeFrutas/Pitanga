package svri.auxiliares;

import java.util.ArrayList;

import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.checkout.Checkout;
import svri.entidades.Ingresso;
import svri.entidades.RegistroCompra;

public class Compra {
	
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
	
	public void efetuarPagamento(ArrayList<Ingresso> ingressos, RegistroCompra novoRegistroCompra){
		Checkout checkout = new Checkout();
		
		//adicionar aqui cada ingresso usando o while
		//checkout.addItem();
	}
	public void gerarComprovante(){}
	public void gerarIngresso(){}
	
}
