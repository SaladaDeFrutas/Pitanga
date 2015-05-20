package svri.auxiliares;

import java.util.ArrayList;

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
	
	public void efetuarPagamento(){}
	public void gerarComprovante(){}
	public void gerarIngresso(){}
	
}
