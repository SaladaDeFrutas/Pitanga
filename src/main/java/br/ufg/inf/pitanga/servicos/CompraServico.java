package br.ufg.inf.pitanga.servicos;

<<<<<<< HEAD
import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.entidades.CompraDTO;
import br.ufg.inf.pitanga.entidades.Ingresso;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceCompraDao;
=======
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.enums.TransactionStatus;
import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.repository.AssentoRepository;
import br.ufg.inf.pitanga.repository.ClienteRepository;
>>>>>>> da2c76ed0d4764133217b13b06d2d54cbb0dce17
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
<<<<<<< HEAD
    ClienteService clienteService;
=======
    private ClienteRepository clienteRepository;
>>>>>>> da2c76ed0d4764133217b13b06d2d54cbb0dce17

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

    public Compra obtenhaCompraPeloId(Long idCompra) {
        return compraRepository.findOne(idCompra);
    }

    public List<CompraDTO> obtenhaComprasDoCliente(String email) {
<<<<<<< HEAD
        Cliente cliente = clienteService.recuperarClientePorEmail(email);
=======
        Cliente cliente = clienteRepository.findByEmail(email);
>>>>>>> da2c76ed0d4764133217b13b06d2d54cbb0dce17
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
        Transaction transacaoCompra = pagamentoPagseguroServico.consultarTransacao(codigoTransacao);
        Long idCompra = Long.parseLong(transacaoCompra.getReference());
        Compra compra = compraRepository.findOne(idCompra);
        compra.setCodigoTransacao(codigoTransacao);
        compraRepository.save(compra);
    }

    public void registrarPagamento(String notificationCode) {
        Transaction respostaConsultaNotificacaoCheckout =
            pagamentoPagseguroServico.receberNotificacaoCheckout(notificationCode);

        Compra compra = compraRepository.findOne(Long.parseLong(respostaConsultaNotificacaoCheckout.getReference()));
        compra.setCodigoTransacao(respostaConsultaNotificacaoCheckout.getCode());
        TransactionStatus statusTransacao = respostaConsultaNotificacaoCheckout.getStatus();

        boolean pagamentoAprovado =
            pagamentoPagseguroServico.traduzirStatusTransacaoPagseguro(statusTransacao.getValue().intValue());
        compra.setPagamentoAprovado(pagamentoAprovado);

        compraRepository.save(compra);
    }

    public void finalizarCompra(Long idSessao, String emailCliente,
                                Map<String, String> assentoEscolhidoPorTipoIngresso) {
        Sessao sessao = sessaoServico.buscaSessaoPorId(idSessao);
        Cliente cliente = clienteRepository.findByEmail(emailCliente);
        List<Ingresso> ingressos = obtenhaListaDeIngressos(cliente, sessao, assentoEscolhidoPorTipoIngresso);

        Compra compra = salvaCompraComHoraAtual(cliente, ingressos);
        pagamentoPagseguroServico.realizaPagamento(compra, cliente);
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
