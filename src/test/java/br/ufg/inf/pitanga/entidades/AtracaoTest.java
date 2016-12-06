package br.ufg.inf.pitanga.entidades;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class AtracaoTest {

    private Atracao atracao = new Filme();

    @Test
    public void getIdAtracaoTest() {
        Long idAtracao = 178L;
        atracao.setId(idAtracao);
        assertEquals(idAtracao, atracao.getId());
    }

    @Test
    public void setIdAtracaoTest() {
        Long idAtracao = 178L;
        atracao.setId(idAtracao);
        assertEquals(idAtracao, atracao.getId());
    }

    @Test
    public void getTituloTest() {
        String titulo = "Esse titulo é bom";
        atracao.setTitulo(titulo);
        assertEquals(titulo, atracao.getTitulo());
    }

    @Test
    public void setTituloTest() {
        String titulo = "Esse titulo é bom";
        atracao.setTitulo(titulo);
        assertEquals(titulo, atracao.getTitulo());
    }

    @Test
    public void getIdiomaTest() {
        String idioma = "Portugues";
        atracao.setIdioma(idioma);
        assertEquals(idioma, atracao.getIdioma());
    }

    @Test
    public void setIdiomaTest() {
        String idioma = "Portugues";
        atracao.setIdioma(idioma);
        assertEquals(idioma, atracao.getIdioma());
    }

    @Test
    public void getDuracaoTest() {
        int duracao = 178;
        atracao.setDuracao(duracao);
        assertEquals(duracao, atracao.getDuracao());
    }

    @Test
    public void setDuracaoTest() {
        int duracao = 178;
        atracao.setDuracao(duracao);
        assertEquals(duracao, atracao.getDuracao());
    }

    @Test
    public void getSinopseTest() {
        String sinopse = "Filme muito bom";
        atracao.setSinopse(sinopse);
        assertEquals(sinopse, atracao.getSinopse());
    }

    @Test
    public void setSinopseTest() {
        String sinopse = "Filme muito bom";
        atracao.setSinopse(sinopse);
        assertEquals(sinopse, atracao.getSinopse());
    }

    @Test
    public void getGeneroTest() {
        String genero = "Terror";
        atracao.setGenero(genero);
        assertEquals(genero, atracao.getGenero());
    }

    @Test
    public void setGeneroTest() {
        String genero = "Terror";
        atracao.setGenero(genero);
        assertEquals(genero, atracao.getGenero());
    }

    @Test
    public void getDataEstreiaTest() {
        Calendar dataEstreia = Calendar.getInstance();
        dataEstreia.getTime();
        atracao.setDataEstreia(dataEstreia);
        assertEquals(dataEstreia, atracao.getDataEstreia());
    }

    @Test
    public void setDataEstreiaTest() {
        Calendar dataEstreia = Calendar.getInstance();
        dataEstreia.getTime();
        atracao.setDataEstreia(dataEstreia);
        assertEquals(dataEstreia, atracao.getDataEstreia());
    }

    @Test
    public void getClassificacaoIndicativaTest() {
        int classificacaoIndicativa = 1;
        atracao.setClassificacaoIndicativa(classificacaoIndicativa);
        assertEquals(classificacaoIndicativa, atracao.getClassificacaoIndicativa());
    }

    @Test
    public void setClassificacaoIndicativaTest() {
        int classificacaoIndicativa = 1;
        atracao.setClassificacaoIndicativa(classificacaoIndicativa);
        assertEquals(classificacaoIndicativa, atracao.getClassificacaoIndicativa());
    }
}
