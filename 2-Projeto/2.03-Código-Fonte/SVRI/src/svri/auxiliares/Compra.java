package svri.auxiliares;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import svri.entidades.Ingresso;
import svri.entidades.RegistroCompra;
import svri.interfaces.dao.InterfaceRegistroCompraDao;


public class Compra {
	
	@Autowired
	@Qualifier("RegistroCompra")
	private InterfaceRegistroCompraDao registroCompraDao;

	
	public void calcularTotal(ArrayList<Ingresso> ingressos, RegistroCompra novoRegistroCompra){
		double valor = 0;
		
		for (Ingresso umIngresso : ingressos) {
			valor += umIngresso.getUmTipoIngresso().getPreco();
		}
		novoRegistroCompra.setValor(valor);
		atualizarRegistroCompra(novoRegistroCompra);
	}
	
	private void atualizarRegistroCompra(RegistroCompra novoRegistroCompra){
		registroCompraDao.alterarRegistroCompra(novoRegistroCompra);
	}
	
	public void cancelarCompra(){
		
		
	}
	
	public void efetuarPagamento(){}
	public void gerarComprovante(){}
	public void gerarIngresso(){}
	
}
