package svri.servicos;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import svri.entidades.Cliente;
import svri.entidades.Ingresso;
import svri.entidades.RegistroCompra;

public class PagamentoPagseguro implements InterfacePagamento{

	@Override
	public String realizaPagamento(ArrayList<Ingresso> ingressos,
			RegistroCompra novoRegistroCompra, Cliente cliente) {
		Checkout checkout = new Checkout();
		SimpleDateFormat sDformat = new SimpleDateFormat("dd/MM/yyyy");
		//adicionar aqui cada ingresso usando o while
		for (Ingresso ingresso : ingressos) {
			
			
			
			checkout.addItem(
					Integer.toString(ingresso.getId()), // id
					ingresso.getUmaSessao().getAtracao().getTitulo() + ", " +// descricao
					sDformat.format(ingresso.getUmaSessao().getData().getTime()), // descricao
					Integer.valueOf(1), // quantidade
					
					/* coloca-se o '0' no final, pois a funcao getPreco retorna o valor com o .0
					 * no final e porque o pagseguro requer que o valor do item tenha o formato
					 * 00.00
					 * */
					new BigDecimal(String.valueOf(ingresso.getUmTipoIngresso().getPreco())+"0"), // valor unitario
					null,
					null
			);
		}
		
		// colocando dados do comprador
		/*checkout.setSender(
			cliente.getNome(), // nome
			cliente.getEmail() //email
		);*/
		
		// selecionando moeda
		checkout.setCurrency(Currency.BRL);
		
		// colocando referencia para a transacao
		checkout.setReference(String.valueOf(novoRegistroCompra.getIdCompra()));
		
		// URL para onde o comprador sera redirecionado (GET) apos o fluxo de pagamento 
		checkout.setRedirectURL("http://jbossews-svri.rhcloud.com/SVRI/obrigado"); 
		
		checkout.setNotificationURL("http://jbossews-svri.rhcloud.com/SVRI/notificacoes");
		
		try {  
			  
			  boolean onlyCheckoutCode = false;  
			  String response = checkout.register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);  
			  
			  System.out.println(response);    
			  
			  return response;
			  
			} catch (PagSeguroServiceException e) {  
			  
			    System.err.println(e.getMessage());  
			}
			
			return "https://www.google.com.br/";
				
	}

}
