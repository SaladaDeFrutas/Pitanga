package br.ufg.inf.pitanga.entidades;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AssentoTest {
    Assento assento;

    @Before
    public void criaAssento() {
        assento = new Assento();
    }

    @Test
    public void testaGetESetIdComValorValidoComSucesso() {
        long id = 1;
        assento.setId(id);
        Assert.assertEquals(id, assento.getId().longValue());
    }

    @Test
    public void testaGetESetIdComValorNuloComSucesso() {
        Long id = null;
        assento.setId(id);
        Assert.assertEquals(id, assento.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaSetIdComValorELancaIllegalArgumentException() {
        long id = -1;
        assento.setId(id);
    }

    @Test
    public void testaGetESetSalaComSucesso() {
        Sala sala = new Sala();
        long id = 1;
        sala.setId(id);
        assento.setSala(sala);
        Assert.assertEquals(sala.getId(), assento.getSala().getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaGetESetSalaNulaELancaIllegalArgumentException() {
        Sala sala = null;
        assento.setSala(sala);
    }

    @Test
    public void testaGetESetQuantidadeDeFileirasComValorValidoComSucesso() {
        int tamanho = 6;
        assento.setFila(tamanho);
        Assert.assertEquals(tamanho, assento.getFila());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaSetQuantidadeDeFileirasComValorNegativoELancaIllegalArgumentException() {
        int tamanho = -6;
        assento.setFila(tamanho);
    }

    @Test
    public void testaGetESetQuantidadeDeColunasComValorValidoComSucesso() {
        int tamanho = 6;
        assento.setColuna(tamanho);
        Assert.assertEquals(tamanho, assento.getColuna());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaSetQuantidadeDeColunasComValorInvalidoELancaIllegalArgumentException() {
        int tamanho = -6;
        assento.setColuna(tamanho);
    }

    @Test
    public void testaGetESetNomeComSucesso() {
        assento.setNome("A1");
        Assert.assertEquals("A1", assento.getNome());
    }


    @Test
    public void testaGetESetTipoAssentoComSucesso() {
        assento.setTipoAssento(TipoAssento.DEFICIENTE_FISICO);
        Assert.assertEquals(TipoAssento.DEFICIENTE_FISICO, assento.getTipoAssento());
    }
}
