package br.ufg.inf.pitanga.entidades;

import br.ufg.inf.pitanga.entidades.Assento;
import br.ufg.inf.pitanga.entidades.Sala;
import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.excecao.ExcecaoAssentoForaDoTamanhoDaSala;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SalaTest {
    Sala sala;

    @Before
    public void criaSala() {
        sala = new Sala();
        sala.setColunas(5);
        sala.setFilas(5);
    }

    @Test
    public void testaGetESetIdComValorValidoComSucesso() {
        long id = 1;
        sala.setId(id);
        Assert.assertEquals(id, sala.getId().longValue());
    }

    @Test
    public void testaGetESetIdComValorNuloComSucesso() {
        Long id = null;
        sala.setId(id);
        Assert.assertEquals(id, sala.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaSetIdComValorELancaIllegalArgumentException() {
        long id = -1;
        sala.setId(id);
    }

    @Test
    public void testaGetESetQuantidadeDeFileirasComValorValidoComSucesso() {
        int tamanho = 6;
        sala.setFilas(tamanho);
        Assert.assertEquals(tamanho, sala.getFilas());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaSetQuantidadeDeFileirasComValorNegativoELancaIllegalArgumentException() {
        int tamanho = -6;
        sala.setFilas(tamanho);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaSetQuantidadeDeFileirasComValorNuloELancaIllegalArgumentException() {
        Integer tamanho = null;
        sala.setFilas(tamanho);
    }

    @Test
    public void testaGetESetQuantidadeDeColunasComValorValidoComSucesso() {
        int tamanho = 6;
        sala.setColunas(tamanho);
        Assert.assertEquals(tamanho, sala.getColunas());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaSetQuantidadeDeColunasComValorInvalidoELancaIllegalArgumentException() {
        int tamanho = -6;
        sala.setColunas(tamanho);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaSetQuantidadeDeColunasComValorNuloELancaIllegalArgumentException() {
        Integer tamanho = null;
        sala.setColunas(tamanho);
    }

    @Test
    public void testaSetAssentoComSucesso() {
        Assento assento1 = new Assento(sala);
        assento1.setColuna(1);
        assento1.setFila(3);

        Assento assento2 = new Assento(sala);
        assento2.setColuna(2);
        assento2.setFila(2);

        List<Assento> listaAssentos = new ArrayList<>();
        listaAssentos.add(assento1);
        listaAssentos.add(assento2);

        sala.setAssentos(listaAssentos);
        Assert.assertEquals(2, sala.getAssentos().size());
    }

    @Test(expected = ExcecaoAssentoForaDoTamanhoDaSala.class)
    public void testaSetAssentoForaDoTamanhoDaSalaELancaExcecaoAssentoForaDoTamanhoDaSala() {
        Assento assento1 = new Assento(sala);
        assento1.setColuna(7);
        assento1.setFila(7);

        List<Assento> listaAssentos = new ArrayList<>();
        listaAssentos.add(assento1);

        sala.setAssentos(listaAssentos);
    }

    @Test
    public void testaGetESetSessoesComSucesso() {
        Sessao sessao1 = new Sessao();
        Sessao sessao2 = new Sessao();
        Sessao sessao3 = new Sessao();

        List<Sessao> listaSessoes = new ArrayList<>();
        listaSessoes.add(sessao1);
        listaSessoes.add(sessao2);
        listaSessoes.add(sessao3);

        sala.setSessoes(listaSessoes);
        Assert.assertEquals(3, sala.getSessoes().size());
    }

}
