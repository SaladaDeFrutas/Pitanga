package br.ufg.inf.pitanga.servicos;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.enums.TransactionStatus;
import br.ufg.inf.pitanga.CalendarHelper;
import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.entidades.enums.TipoAssento;
import br.ufg.inf.pitanga.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompraServiceTest {

    @Autowired
    private CompraServico compraServico;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    @Spy
    private PagamentoPagseguroServico pagamentoPagseguroServico;

    @Test
    public void testaObtenhaComprasDeUmClienteComSucesso() {

        String dataCompra = "04/12/2016";
        String emailCliente = "teste@email.com";
        String valor = "15.18";
        Compra compra = adicionarCompraParaCliente(valor, dataCompra, emailCliente);
        CompraDTO compraDTO = compraServico.obtenhaComprasDoCliente(emailCliente).get(0);

        assertEquals(compraDTO.getDataCompra(), dataCompra);
        assertEquals(compraDTO.getNomeCliente(), compra.getCliente().getNome());
        assertEquals(compraDTO.getIdCompra(), compra.getId());
        assertEquals(compraDTO.getValorCompra(), compra.getValorTotal());
    }

    @Test
    public void testaObtenhaCompraPeloId() {
        String dataCompra = "01/01/2001";
        String emailCliente = "teste@teste.com";
        String valor = "35.99";
        Compra compra = adicionarCompraParaCliente(valor, dataCompra, emailCliente);
        Compra compraSalva = compraRepository.save(compra);
        Long idCompra = compraSalva.getId();

        Compra compraObtida = compraServico.buscarPorId(idCompra);
        assertEquals(valor, compraObtida.getValorTotal().toString());
    }

    @Test
    public void testarRegistrarCodigoTransacao() {
        String dataCompra = "02/02/2012";
        String emailCliente = "teste@teste.com";
        String valor = "159.35";
        String codigoTransacao = "12345";
        String codigoNotificacao = "999";

        Compra compra = adicionarCompraParaCliente(valor, dataCompra, emailCliente);
        Long idCompra = compra.getId();
        mockPagamentoPagseguroServico(codigoTransacao, idCompra.toString(), codigoNotificacao);
        compraServico.registrarCodigoTransacao(codigoTransacao);
        Compra compraObtida = compraServico.obtenhaCompraPeloId(idCompra);

        assertEquals(codigoTransacao, compraObtida.getCodigoTransacao());
    }

    @Test
    public void testarRegistrarPagamento() {
        String dataCompra = "02/12/1995";
        String emailCliente = "teste@teste.com";
        String valor = "159.35";
        String codigoTransacao = "12345";
        String codigoNotificacao = "999";

        Compra compra = adicionarCompraParaCliente(valor, dataCompra, emailCliente);
        Long idCompra = compra.getId();
        mockPagamentoPagseguroServico(codigoTransacao, idCompra.toString(), codigoNotificacao);
        compraServico.registrarPagamento(codigoNotificacao);
        Compra compraObtida = compraServico.obtenhaCompraPeloId(idCompra);

        assertEquals(codigoTransacao, compraObtida.getCodigoTransacao());
    }

    @Test
    public void testarFinalizarCompra() {
        String nomeAssento = "B19";
        Sala sala = criaSalaComAssento(nomeAssento);
        Sessao sessao = criaSessao(sala);
        String emailCliente = "cliente@finalizarcompra.com";
        String nomeCliente = "Nome do cliente";
        criarCliente(emailCliente, nomeCliente);

        String nomeTipoIngresso = "Meia";
        BigDecimal valorTipoIngresso = new BigDecimal("15.35");
        criaTipoIngresso(nomeTipoIngresso, valorTipoIngresso);

        Map<String, String> assentoPorTipoIngresso = obtenhaMapaNomeAssentoPorTipoIngresso(nomeAssento, nomeTipoIngresso);

        compraServico.finalizarCompra(sessao.getIdSessao(), emailCliente, assentoPorTipoIngresso);
        CompraDTO compraCriada = compraServico.obtenhaComprasDoCliente(emailCliente).get(0);

        assertEquals(nomeCliente, compraCriada.getNomeCliente());
        assertEquals(valorTipoIngresso, compraCriada.getValorCompra());
    }

    private Cliente criarCliente(String emailCliente, String nomeCliente) {
        Cliente cliente = new Cliente();
        cliente.setEmail(emailCliente);
        cliente.setNome(nomeCliente);
        return clienteRepository.save(cliente);
    }

    private Compra adicionarCompraParaCliente(String valorTotal, String dataCompra, String emailCliente) {
        Cliente cliente = criarCliente(emailCliente, "nome");
        BigDecimal valor = new BigDecimal(valorTotal);
        Calendar data = CalendarHelper.converteStringParaCalendar(dataCompra, "dd/MM/yyyy");

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setValorTotal(valor);
        compra.setPagamentoAprovado(false);
        compra.setDataCompra(data);
        List<Ingresso> ingressos = criaListaDeIngressos(compra);
        ingressoRepository.save(ingressos);
        compra.setIngressos(ingressos);

        return compraRepository.save(compra);
    }

    private List<Ingresso> criaListaDeIngressos(Compra compra) {
        Ingresso ingresso = new Ingresso();
        Assento assento = criaAssentoEmUmaSala();
        ingresso.setUmAssento(assento);
        return new ArrayList<Ingresso>() {{
            add(ingresso);
        }};
    }

    private Assento criaAssentoEmUmaSala() {
        Sala sala = new Sala();
        Assento assento = new Assento(sala);
        sala.setAssentos(new ArrayList<Assento>() {{
            add(assento);
        }});
        salaRepository.save(sala);
        return assento;
    }

    private void mockPagamentoPagseguroServico(String codigoTransacao, String idCompra, String codigoNotificacao) {
        Transaction transaction = new Transaction();
        transaction.setReference(idCompra);
        transaction.setCode(codigoTransacao);
        transaction.setStatus(TransactionStatus.PAID);
        Integer codigo = TransactionStatus.PAID.getValue();
        Mockito.when(pagamentoPagseguroServico.consultarTransacao(codigoTransacao)).thenReturn(transaction);
        Mockito.when(pagamentoPagseguroServico.receberNotificacaoCheckout(codigoNotificacao)).thenReturn(transaction);
        Mockito.when(pagamentoPagseguroServico.traduzirStatusTransacaoPagseguro(codigo)).thenReturn(true);
        ReflectionTestUtils.setField(compraServico, "pagamentoPagseguroServico", pagamentoPagseguroServico);
    }

    private TipoIngresso criaTipoIngresso(String nomeTipoIngresso, BigDecimal valor) {
        TipoIngresso tipoIngresso = new TipoIngresso();
        tipoIngresso.setNome(nomeTipoIngresso);
        tipoIngresso.setPreco(valor);
        return tipoIngressoRepository.save(tipoIngresso);
    }

    private Sessao criaSessao(Sala sala) {
        Sessao sessao = new Sessao();
        sessao.setData(Calendar.getInstance());
        sessao.setAtracao(criaFilme());
        sessao.setSala(sala);
        return sessaoRepository.save(sessao);
    }

    private Filme criaFilme() {
        Filme filme = new Filme();
        filme.setModoDeExibicao("3d");
        filme.setProdutora("produtora");
        filme.setTitulo("titulo");
        filme.setIdioma("idioma");
        filme.setDuracao(1);
        filme.setSinopse("sinopse");
        filme.setGenero("genero");
        filme.setClassificacaoIndicativa(10);
        return filme;
    }

    private Sala criaSalaComAssento(String nomeAssento) {
        Assento assento = new Assento();
        assento.setNome(nomeAssento);
        assento.setTipoAssento(TipoAssento.NORMAL);

        Sala sala = new Sala();
        sala.setAssentos(new ArrayList<Assento>() {{
            add(assento);
        }});

        return salaRepository.save(sala);
    }

    private Map<String, String> obtenhaMapaNomeAssentoPorTipoIngresso(String nomeAssento, String nomeTipoIngresso) {
        Map<String, String> assentoPorTipoIngresso = new HashMap<>();
        assentoPorTipoIngresso.put(nomeAssento, nomeTipoIngresso);
        return assentoPorTipoIngresso;
    }


}
