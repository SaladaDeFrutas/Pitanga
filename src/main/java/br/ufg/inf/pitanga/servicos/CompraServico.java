package br.ufg.inf.pitanga.servicos;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.enums.TransactionStatus;
import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.repository.AssentoRepository;
import br.ufg.inf.pitanga.repository.ClienteRepository;
import br.ufg.inf.pitanga.repository.CompraRepository;
import br.ufg.inf.pitanga.repository.TipoIngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CompraServico {

    @Autowired
    private PagamentoPagseguroServico pagamentoPagseguroServico;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private SessaoServico sessaoServico;

    @Autowired
    private AssentoRepository assentoRepository;

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    @Autowired
    private IngressoServico ingressoServico;

    InterfacePagamento tipoPagamento;

    public Compra obtenhaCompraPeloId(Long idCompra) {
        return compraRepository.findOne(idCompra);
    }

    private void efetuarPagamento(Compra novaCompra, Cliente cliente) {
        tipoPagamento = new PagamentoPagseguroServico();
        tipoPagamento.realizaPagamento(novaCompra, cliente);
    }

    public List<CompraDTO> obtenhaComprasDoCliente(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        String nomeCliente = cliente.getNome();
        List<Compra> compras = compraRepository.findByCliente(cliente);

        List<CompraDTO> compraDTOS = new ArrayList<>();

        for (Compra compra : compras) {
            String dataCompra = formatarDataDaCompra(compra.getDataCompra());
            compraDTOS.add(new CompraDTO(nomeCliente, compra.getId(), dataCompra, compra.getValorTotal()));
        }

        return compraDTOS;
    }

    private String formatarDataDaCompra(Calendar dataCompra) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataCompra.getTime());
    }

    public void registrarCodigoTransacao(String codigoTransacao) {
        Transaction transacaoCompra = new PagamentoPagseguroServico().consultarTransacao(codigoTransacao);
        Compra compra = compraRepository.findOne(Long.parseLong(transacaoCompra.getReference()));
        compra.setCodigoTransacao(codigoTransacao);
        compraRepository.save(compra);
    }

    public void registrarPagamento(String notificationCode) {
        NotificacaoPagseguro novaNotificacao = new NotificacaoPagseguro();
        Transaction respostaConsultaNotificacaoCheckout = novaNotificacao.receberNotificacaoCheckout(notificationCode);

        Compra compra = compraRepository.findOne(Long.parseLong(respostaConsultaNotificacaoCheckout.getReference()));
        compra.setCodigoTransacao(respostaConsultaNotificacaoCheckout.getCode());
        TransactionStatus statusTransacao = respostaConsultaNotificacaoCheckout.getStatus();

        boolean pagamentoAprovado = new PagamentoPagseguroServico()
            .traduzirStatusTransacaoPagseguro(statusTransacao.getValue().intValue());
        compra.setPagamentoAprovado(pagamentoAprovado);

        compraRepository.save(compra);
    }

    public void finalizarCompra(Long idSessao, String emailCliente,
                                HashMap<String, String> assentoEscolhidoPorTipoIngresso) {
        Sessao sessao = sessaoServico.buscaSessaoPorId(idSessao);
        Cliente cliente = clienteRepository.findByEmail(emailCliente);
        List<Ingresso> ingressos = obtenhaListaDeIngressos(cliente, sessao, assentoEscolhidoPorTipoIngresso);

        Compra compra = salvaCompraComHoraAtual(cliente, ingressos);
        efetuarPagamento(compra, cliente);
    }

    private Compra salvaCompraComHoraAtual(Cliente cliente, List<Ingresso> ingressos) {
        Calendar dataCompra = Calendar.getInstance();
        return compraRepository.save(new Compra(cliente, ingressos, dataCompra));
    }

    private List<Ingresso> obtenhaListaDeIngressos(Cliente cliente, Sessao sessao,
                                                   Map<String, String> assentoPorTipoIngressoMap) {
        List<Ingresso> ingressos = new ArrayList<>();

        for (Map.Entry<String, String> nomeAssentoPorTipoIngresso : assentoPorTipoIngressoMap.entrySet()) {
            String nomeAssento = nomeAssentoPorTipoIngresso.getKey();
            String nomeTipoIngresso = nomeAssentoPorTipoIngresso.getValue();

            TipoIngresso tipoIngresso = tipoIngressoRepository.findByNome(nomeTipoIngresso);
            Assento assentoEscolhido = assentoRepository.findByNomeAndSala(nomeAssento, sessao.getSala());
            Ingresso ingresso = ingressoServico.salvaIngresso(cliente, sessao, assentoEscolhido, tipoIngresso);
            ingressos.add(ingresso);
        }
        return ingressos;
    }
}
