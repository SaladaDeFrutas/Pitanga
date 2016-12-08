package br.ufg.inf.pitanga.entidades;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class SessaoTest {

    private Sessao sessao = new Sessao();

    @Test
    public void getIdSessaoTest() {
        long idSessao = 898098;
        sessao.setIdSessao(idSessao);
        assertEquals(idSessao, sessao.getIdSessao());
    }

    @Test
    public void setIdSessaoTest() {
        long idSessao = 894378;
        sessao.setIdSessao(idSessao);
        assertEquals(idSessao, sessao.getIdSessao());
    }

    @Test
    public void getDataTest() {
        Calendar data = Calendar.getInstance();
        data.getTime();
        sessao.setData(data);
        assertEquals(data, sessao.getData());
    }

    @Test
    public void setDataTest() {
        Calendar data = Calendar.getInstance();
        data.getTime();
        sessao.setData(data);
        assertEquals(data, sessao.getData());
    }

    @Test
    public void getAtracaoTest() {
        Filme atracao = new Filme();
        sessao.setAtracao(atracao);
        assertEquals(atracao, sessao.getAtracao());
    }

    @Test
    public void setAtracaoTest() {
        Filme atracao = new Filme();
        sessao.setAtracao(atracao);
        assertEquals(atracao, sessao.getAtracao());
    }

    @Test
    public void getSalaTest() {
        Sala sala = new Sala();
        sessao.setSala(sala);
        assertEquals(sala, sessao.getSala());
    }

    @Test
    public void setSalaTest() {
        Sala sala = new Sala();
        sessao.setSala(sala);
        assertEquals(sala, sessao.getSala());
    }

    @Test
    public void getAssentosOcupadosTest() {
        String assentosOcupados = "muitos";
        sessao.setAssentosOcupados(assentosOcupados);
        assertEquals(assentosOcupados, sessao.getAssentosOcupados());
    }

    @Test
    public void setAssentosOcupadosTest() {
        String assentosOcupados = "poucos";
        sessao.setAssentosOcupados(assentosOcupados);
        assertEquals(assentosOcupados, sessao.getAssentosOcupados());
    }
}
