package br.ufg.inf.pitanga.entidades;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class IngressoTest {

    private Ingresso ingresso = new Ingresso();

    @Test
    public void getIdTest() {
        Long idIngresso = 898098L;
        ingresso.setId(idIngresso);
        assertEquals(idIngresso, ingresso.getId());
    }

    @Test
    public void setIdTest() {
        Long idIngresso = 894378L;
        ingresso.setId(idIngresso);
        assertEquals(idIngresso, ingresso.getId());
    }

    @Test
    public void getUmClienteTest() {
        Cliente cliente = new Cliente();
        ingresso.setUmCliente(cliente);
        assertEquals(cliente, ingresso.getUmCliente());
    }

    @Test
    public void setUmClienteTest() {
        Cliente cliente = new Cliente();
        ingresso.setUmCliente(cliente);
        assertEquals(cliente, ingresso.getUmCliente());
    }

    @Test
    public void getUmaSessaoTest() {
        Sessao sessao = new Sessao();
        ingresso.setUmaSessao(sessao);
        assertEquals(sessao, ingresso.getUmaSessao());
    }

    @Test
    public void setUmaSessaoTest() {
        Sessao sessao = new Sessao();
        ingresso.setUmaSessao(sessao);
        assertEquals(sessao, ingresso.getUmaSessao());
    }

    @Test
    public void getUmAssentoTest() {
        Assento assento = new Assento();
        ingresso.setUmAssento(assento);
        assertEquals(assento, ingresso.getUmAssento());
    }

    @Test
    public void setUmAssentoTest() {
        Assento assento = new Assento();
        ingresso.setUmAssento(assento);
        assertEquals(assento, ingresso.getUmAssento());
    }

    @Test
    public void getUmTipoIngressoTest() {
        TipoIngresso tipoIngresso = new TipoIngresso();
        ingresso.setUmTipoIngresso(tipoIngresso);
        assertEquals(tipoIngresso, ingresso.getUmTipoIngresso());
    }

    @Test
    public void setUmTipoIngressoTest() {
        TipoIngresso tipoIngresso = new TipoIngresso();
        ingresso.setUmTipoIngresso(tipoIngresso);
        assertEquals(tipoIngresso, ingresso.getUmTipoIngresso());
    }

    @Test
    public void getValorIngressoTest() {
        String valor = "9.15";
        Ingresso ingresso = new Ingresso();
        ingresso.setUmTipoIngresso(criaTipoIngressoParaTeste(valor));
        assertEquals(valor, ingresso.getValor().toString());
    }


    @Test
    public void criaIngressoComSucessoTest() {
        String nomeCliente = "Nome do cliente";
        String tituloFilme = "Filme";
        String nomeAssento = "A1";
        String precoTipoIngresso = "15.99";
        Ingresso ingresso = criaIngressoParaTeste(nomeCliente, tituloFilme, nomeAssento, precoTipoIngresso);

        assertEquals(nomeCliente, ingresso.getUmCliente().getNome());
        assertEquals(tituloFilme, ingresso.getUmaSessao().getAtracao().getTitulo());
        assertEquals(nomeAssento, ingresso.getUmAssento().getNome());
        assertEquals(precoTipoIngresso, ingresso.getUmTipoIngresso().getPreco().toString());
    }

    private Ingresso criaIngressoParaTeste(String nomeCliente, String tituloFilme, String nomeAssento,
                                           String nomeTipoIngresso) {
        Cliente cliente = criaClienteParaTeste(nomeCliente);
        Sessao sessao = criaSessaoParaTeste(tituloFilme);
        Assento assento = criaAssentoParaTeste(nomeAssento);
        TipoIngresso tipoIngresso = criaTipoIngressoParaTeste(nomeTipoIngresso);
        return new Ingresso(cliente, sessao, assento, tipoIngresso);
    }

    private Cliente criaClienteParaTeste(String nomeCliente) {
        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente);
        return cliente;
    }

    private Sessao criaSessaoParaTeste(String tituloFilme) {
        Sessao sessao = new Sessao();
        Atracao filme = new Filme();
        filme.setTitulo(tituloFilme);
        sessao.setAtracao(filme);
        return sessao;
    }

    private Assento criaAssentoParaTeste(String nomeAssento) {
        Assento assento = new Assento();
        assento.setNome(nomeAssento);
        return assento;
    }

    private TipoIngresso criaTipoIngressoParaTeste(String valor) {
        TipoIngresso tipoIngresso = new TipoIngresso();
        tipoIngresso.setPreco(new BigDecimal(valor));
        return tipoIngresso;
    }
}
