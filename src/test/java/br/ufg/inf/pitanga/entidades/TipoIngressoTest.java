package br.ufg.inf.pitanga.entidades;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TipoIngressoTest {
    TipoIngresso tipoIngresso;

    @Before
    public void criaAssento() {
        tipoIngresso = new TipoIngresso();
    }

    @Test
    public void testaGetESetNomeComValorValidoComSucesso() {
        tipoIngresso.setNome("teste");
        Assert.assertEquals("teste", tipoIngresso.getNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaGetESetNomeComValorInvalidoELancaIllegalArgumentException() {
        tipoIngresso.setNome(",-0e*");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testaGetESetNomeComValorVazioELancaIllegalArgumentException() {
        tipoIngresso.setNome("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaGetESetNomeComValorNuloELancaIllegalArgumentException() {
        tipoIngresso.setNome(null);
    }

    @Test
    public void testaGetESetPrecoComValorValidoComSucesso() {
        BigDecimal preco = new BigDecimal(32.00);
        tipoIngresso.setPreco(preco);
        Assert.assertEquals(preco, tipoIngresso.getPreco());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaGetESetPrecoComValorNegativoELancaIllegalArgumentException() {
        BigDecimal preco = new BigDecimal(-32.00);
        tipoIngresso.setPreco(preco);
    }

}
