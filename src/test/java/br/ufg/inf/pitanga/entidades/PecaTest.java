package br.ufg.inf.pitanga.entidades;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PecaTest extends AtracaoTest {

    Peca peca = new Peca();

    @Override
    protected Atracao obtenhaAtracao() {
        return peca;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLegendadoComValorNulo() {
        peca.setDiretor(null);
    }

    @Test
    public void testSetLegendadoComSucesso() {
        String diretor = "Spielberg";
        peca.setDiretor(diretor);
        assertEquals(diretor, peca.getDiretor());
    }
}
