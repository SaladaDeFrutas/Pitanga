package br.ufg.inf.servicos;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.TransactionSearchService;
import br.ufg.inf.entidades.Cliente;
import br.ufg.inf.entidades.Ingresso;
import br.ufg.inf.entidades.RegistroCompra;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
		checkout.setReference(String.valueOf(novoRegistroCompra.getIdRegistroCompra()));
		
		// URL para onde o comprador sera redirecionado (GET) apos o fluxo de pagamento 
		checkout.setRedirectURL("http://svrideploy-svri.rhcloud.com/SVRI/obrigado"); 
		
		checkout.setNotificationURL("http://svrideploy-svri.rhcloud.com/SVRI/notificacoes");
		
		try {  
			  
			  boolean onlyCheckoutCode = false;  
			  String response = checkout.register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);  
			  
			  System.out.println(response);    
			  
			  return response;
			  
			} catch (PagSeguroServiceException e) {  
			  
			    System.err.println(e.getMessage());  
			}
			
			return "notFound";
				
	}
	
	public Transaction consultarTransacao(String codigoTransacao) {
		Transaction transaction = null;
		
		try {  
			  
		    transaction = TransactionSearchService.searchByCode(PagSeguroConfig.getAccountCredentials(),  
		            codigoTransacao);  
		  
		} catch (PagSeguroServiceException e) {  
		    System.err.println(e.getMessage());  
		}  
		  
		if (transaction != null) {  
		  System.out.println("reference: " + transaction.getReference());  
		  System.out.println("status: " + transaction.getStatus());  
		  
		  return transaction;
		}  
		
		return null;
	}

	
	/**
	Código	Significado
	1	Aguardando pagamento: o comprador iniciou a transação, mas até o momento o PagSeguro não recebeu nenhuma informação sobre o pagamento.
	2	Em análise: o comprador optou por pagar com um cartão de crédito e o PagSeguro está analisando o risco da transação.
	3	Paga: a transação foi paga pelo comprador e o PagSeguro já recebeu uma confirmação da instituição financeira responsável pelo processamento.
	4	Disponível: a transação foi paga e chegou ao final de seu prazo de liberação sem ter sido retornada e sem que haja nenhuma disputa aberta.
	5	Em disputa: o comprador, dentro do prazo de liberação da transação, abriu uma disputa.
	6	Devolvida: o valor da transação foi devolvido para o comprador.
	7	Cancelada: a transação foi cancelada sem ter sido finalizada.
	8	Chargeback debitado: o valor da transação foi devolvido para o comprador.
	9	Em contestação: o comprador abriu uma solicitação 
	 */
	public boolean traduzirStatusTransacaoPagseguro(int codigoTransacao){
		if(codigoTransacao >= 3)
			return true;
		else
			return false;
	}
}
