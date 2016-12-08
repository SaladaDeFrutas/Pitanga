package br.ufg.inf.pitanga.servicos;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.NotificationService;

public class NotificacaoPagseguro {
    /**
     * @return transacao para salvar em Compra
     */
    public Transaction receberNotificacaoCheckout(String codigoNotificacao) {
        // The notificationCode received by your system


        Transaction transaction = null;

        try {

        	/* Set your account credentials on src/pagseguro-config.properties
			 * You can create an payment using an application credential and set an authorizationCode 
			 * ApplicationCredentials applicationCredentials = PagSeguroConfig.getApplicationCredentials();
             * applicationCredentials.setAuthorizationCode("your_authorizationCode");
			 */

            transaction = NotificationService.checkTransaction(PagSeguroConfig.getAccountCredentials(),
                codigoNotificacao);

        } catch (PagSeguroServiceException e) {
            System.err.println(e.getMessage());
        }

        if (transaction != null) {
            System.out.println("referencia pro registro compra: (IdCompra)" + transaction.getReference());
            System.out.println("codigo da transacao: " + transaction.getCode());
            System.out.println("status: " + transaction.getStatus());

            return transaction;
        } else
            return null;


    }


}
