package br.ufg.inf.pitanga.entidades;

import br.ufg.inf.pitanga.entidades.enums.TipoAssento;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        tipoIngresso.setPreco(32.00);
        Assert.assertEquals(32.00, tipoIngresso.getPreco(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaGetESetPrecoComValorNegativoELancaIllegalArgumentException() {
        double preco = -32.1;
        tipoIngresso.setPreco(preco);
    }
}
