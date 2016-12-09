package br.ufg.inf.pitanga.servicos;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.NotificationService;
import br.com.uol.pagseguro.service.TransactionSearchService;
import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.entidades.Ingresso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagamentoPagseguroServico implements InterfacePagamento {

    private static final String PAGINA_REDIRECT_URL = "obrigado";
    private static final String PAGINA_NOTIFICACAO_URL = "http://svrideploy-svri.rhcloud.com/SVRI/notificacoes";
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void realizaPagamento(Compra novaCompra, Cliente cliente) {
        Checkout checkout = new Checkout();
        adicionaIngressoNoCheckout(checkout, novaCompra.getIngressos());
        checkout.setCurrency(Currency.BRL);
        checkout.setReference(String.valueOf(novaCompra.getId()));
        checkout.setRedirectURL(PAGINA_REDIRECT_URL);
        checkout.setNotificationURL(PAGINA_NOTIFICACAO_URL);
        try {
            boolean onlyCheckoutCode = false;
            checkout.register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);
        } catch (PagSeguroServiceException e) {
            log.error("Erro ao realizar pagamento.", e);
        }
    }

    private void adicionaIngressoNoCheckout(Checkout checkout, List<Ingresso> ingressos) {

        for (Ingresso ingresso : ingressos) {
            String idIngresso = String.valueOf(ingresso.getId());
            String descricao = ingresso.getUmaSessao().obtenhaDescricao();
            BigDecimal valor = ingresso.getValor();
            checkout.addItem(idIngresso, descricao, Integer.valueOf(1), valor, null, null);
        }
    }

    public Transaction consultarTransacao(String codigoTransacao) {
        Transaction transaction = null;
        try {
            transaction = TransactionSearchService.searchByCode(PagSeguroConfig.getAccountCredentials(),
                codigoTransacao);
        } catch (PagSeguroServiceException e) {
            log.error("Erro ao consultar transação.", e);
        }

        return transaction;
    }

    /**
     * Código	Significado
     * 1	Aguardando pagamento: o comprador iniciou a transação, mas até o momento o PagSeguro não recebeu nenhuma informação sobre o pagamento.
     * 2	Em análise: o comprador optou por pagar com um cartão de crédito e o PagSeguro está analisando o risco da transação.
     * 3	Paga: a transação foi paga pelo comprador e o PagSeguro já recebeu uma confirmação da instituição financeira responsável pelo processamento.
     * 4	Disponível: a transação foi paga e chegou ao final de seu prazo de liberação sem ter sido retornada e sem que haja nenhuma disputa aberta.
     * 5	Em disputa: o comprador, dentro do prazo de liberação da transação, abriu uma disputa.
     * 6	Devolvida: o getValor da transação foi devolvido para o comprador.
     * 7	Cancelada: a transação foi cancelada sem ter sido finalizada.
     * 8	Chargeback debitado: o getValor da transação foi devolvido para o comprador.
     * 9	Em contestação: o comprador abriu uma solicitação
     */
    public boolean traduzirStatusTransacaoPagseguro(int codigoTransacao) {
        if (codigoTransacao >= 3)
            return true;
        else
            return false;
    }

    public Transaction obtenhaTransacao(String codigoTransacao) {
        return consultarTransacao(codigoTransacao);
    }

    public Transaction receberNotificacaoCheckout(String codigoNotificacao) {
        Transaction transaction = null;
        try {
            transaction = NotificationService.checkTransaction(PagSeguroConfig.getAccountCredentials(),
                codigoNotificacao);
        } catch (PagSeguroServiceException e) {
            log.error("Erro ao receber notificação do pag seguro", e);
        }

        return transaction;
    }
}
