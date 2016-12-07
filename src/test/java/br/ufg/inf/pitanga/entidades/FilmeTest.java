package br.ufg.inf.pitanga.entidades;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FilmeTest extends AtracaoTest {

    private Filme filme = new Filme();

    @Override
    protected Atracao obtenhaAtracao() {
        return filme;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLegendadoComValorNulo() {
        filme.setLegendado(null);
    }

    @Test
    public void testSetLegendadoComSucesso() {
        filme.setLegendado(true);
        assertEquals(true, filme.getLegendado());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetModoDeExibicaoComValorNulo() {
        filme.setModoDeExibicao(null);
    }

    @Test
    public void testSetModoDeExibicaoComSucesso() {
        String modoDeExibicao = "3D";
        filme.setModoDeExibicao(modoDeExibicao);
        assertEquals(modoDeExibicao, filme.getModoDeExibicao());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetProdutoraComValorNulo() {
        filme.setProdutora(null);
    }

    @Test
    public void testSetProdutoraComSucesso() {
        String nomeProdutora = "Universal";
        filme.setProdutora(nomeProdutora);
        assertEquals(nomeProdutora, filme.getProdutora());
    }

}
