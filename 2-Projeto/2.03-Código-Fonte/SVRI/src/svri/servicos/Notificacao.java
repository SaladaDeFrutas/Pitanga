package svri.servicos;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.NotificationService;

public class Notificacao {
	/**
	 * 
	 * @return status e referencia do pagamento para salvar em RegistroCompra
	 */
	public String[] receberNotificacaoCheckout(String codigoNotificacao) {
		// The notificationCode received by your system
        String notificationCode = codigoNotificacao;

        Transaction transaction = null;

        try {
        	
        	/* Set your account credentials on src/pagseguro-config.properties
			 * You can create an payment using an application credential and set an authorizationCode 
			 * ApplicationCredentials applicationCredentials = PagSeguroConfig.getApplicationCredentials();
             * applicationCredentials.setAuthorizationCode("your_authorizationCode");
			 */
        	
            transaction = NotificationService.checkTransaction(PagSeguroConfig.getAccountCredentials(),
                    notificationCode);

        } catch (PagSeguroServiceException e) {
            System.err.println(e.getMessage());
        }

        if (transaction != null) {
            System.out.println("codigo da transacao: " + transaction.getCode());
            System.out.println("referencia pro registro compra: " + transaction.getReference());
            System.out.println("status: " + transaction.getStatus());
            
            String[] resposta = {String.valueOf(transaction.getStatus()),
            		String.valueOf(transaction.getReference())
            		};
            return resposta;
        }
        else
        	return null;
        
        
	}
	
	/**
Código			Significado
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
}
