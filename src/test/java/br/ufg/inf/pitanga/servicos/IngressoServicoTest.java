package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngressoServicoTest {

    @Autowired
    private IngressoServico ingressoServico;

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private AssentoRepository assentoRepository;

    @Autowired
    private AtracaoRepository atracaoRepository;

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testaInserirIngressoInvalido() {

        Cliente cliente = new Cliente();
        cliente.setNome("alguem");
        cliente.setEmail("alguem@alguem.com");
        Assento assento = new Assento();
        Sala sala = new Sala();
        TipoIngresso tipoIngresso = new TipoIngresso();
        tipoIngresso.setNome("ingresso");
        tipoIngresso.setPreco(new BigDecimal(1.0));
        Filme filme = new Filme();
        filme.setLegendado(true);
        filme.setModoDeExibicao("1");
        filme.setProdutora("Salt Software");
        filme.setClassificacaoIndicativa(12);
        filme.setDuracao(120);
        filme.setTitulo("Salt Software's bizarre adventures");
        filme.setModoDeExibicao("cinema");
        filme.setSinopse("Salt Software");
        filme.setIdioma("inglês");
        filme.setGenero("inglês");
        Sessao sessao = new Sessao();
        sessao.setAtracao(filme);
        sessao.setSala(sala);
        sessao.setData(Calendar.getInstance());

        Ingresso ingressoEncontrado = ingressoServico.salvaIngresso(cliente, sessao, assento, tipoIngresso);
        assertNull(ingressoEncontrado);

        Ingresso ingresso = new Ingresso();
        ingresso.setUmaSessao(sessao);
        ingresso.setUmAssento(assento);
        ingresso.setUmCliente(cliente);
        ingresso.setUmTipoIngresso(tipoIngresso);
        assertEquals(ingressoEncontrado.getUmaSessao(), ingresso.getUmaSessao());
        assertEquals(ingressoEncontrado.getUmAssento(), ingresso.getUmAssento());
        assertEquals(ingressoEncontrado.getUmCliente(), ingresso.getUmCliente());
        assertEquals(ingressoEncontrado.getUmTipoIngresso(), ingresso.getUmTipoIngresso());
    }

}
