package br.ufg.inf.test;

import br.ufg.inf.pitanga.entidades.Atracao;
import org.testng.annotations.Test;

import java.util.Calendar;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by gleibson on 02/12/16.
 */

public class AtracaoTest {

    public class AtracaoForTest extends Atracao{
        private int idAtracao;

        private String titulo;

        private String idioma;

        private int duracao;

        private String sinopse;

        private String genero;

        private Calendar dataEstreia;

        private int classificacaoIndicativa;

        public int getIdAtracao() {
            return idAtracao;
        }

        public void setIdAtracao(int idAtracao) {
            this.idAtracao = idAtracao;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getIdioma() {
            return idioma;
        }

        public void setIdioma(String idioma) {
            this.idioma = idioma;
        }

        public int getDuracao() {
            return duracao;
        }

        public void setDuracao(int duracao) {
            this.duracao = duracao;
        }

        public String getSinopse() {
            return sinopse;
        }

        public void setSinopse(String sinopse) {
            this.sinopse = sinopse;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public Calendar getDataEstreia() {
            return dataEstreia;
        }

        public void setDataEstreia(Calendar dataEstreia) {
            this.dataEstreia = dataEstreia;
        }

        public int getClassificacaoIndicativa() {
            return classificacaoIndicativa;
        }

        public void setClassificacaoIndicativa(int classificacaoIndicativa) {
            this.classificacaoIndicativa = classificacaoIndicativa;
        }
    }

    private AtracaoForTest atracao = new AtracaoForTest();

    @Test
    public void getIdAtracaoTest(){
        long idAtracao = 178;
        atracao.setIdAtracao(idAtracao);
        assertEquals(idAtracao, atracao.getIdAtracao());
    }

    @Test
    public void setIdAtracaoTest(){
        long idAtracao = 178;
        atracao.setIdAtracao(idAtracao);
        assertEquals(idAtracao, atracao.getIdAtracao());
    }

    @Test
    public void getTituloTest(){
        String titulo = "Esse titulo é bom";
        atracao.setTitulo(titulo);
        assertEquals(titulo, atracao.getTitulo());
    }

    @Test
    public void setTituloTest(){
        String titulo = "Esse titulo é bom";
        atracao.setTitulo(titulo);
        assertEquals(titulo, atracao.getTitulo());
    }

    @Test
    public void getIdiomaTest(){
        String idioma = "Portugues";
        atracao.setIdioma(idioma);
        assertEquals(idioma, atracao.getIdioma());
    }

    @Test
    public void setIdiomaTest(){
        String idioma = "Portugues";
        atracao.setIdioma(idioma);
        assertEquals(idioma, atracao.getIdioma());
    }

    @Test
    public void getDuracaoTest(){
        int duracao = 178;
        atracao.setDuracao(duracao);
        assertEquals(duracao, atracao.getDuracao());
    }

    @Test
    public void setDuracaoTest(){
        int duracao = 178;
        atracao.setDuracao(duracao);
        assertEquals(duracao, atracao.getDuracao());
    }

    @Test
    public void getSinopseTest(){
        String sinopse = "Filme muito bom";
        atracao.setSinopse(sinopse);
        assertEquals(sinopse, atracao.getSinopse());
    }

    @Test
    public void setSinopseTest(){
        String sinopse = "Filme muito bom";
        atracao.setSinopse(sinopse);
        assertEquals(sinopse, atracao.getSinopse());
    }

    @Test
    public void getGeneroTest(){
        String genero = "Terror";
        atracao.setGenero(genero);
        assertEquals(genero, atracao.getGenero());
    }

    @Test
    public void setGeneroTest(){
        String genero = "Terror";
        atracao.setGenero(genero);
        assertEquals(genero, atracao.getGenero());
    }

    @Test
    public void getDataEstreiaTest(){
        Calendar dataEstreia = Calendar.getInstance();
        dataEstreia.getTime();
        atracao.setDataEstreia(dataEstreia);
        assertEquals(dataEstreia, atracao.getDataEstreia());
    }

    @Test
    public void setDataEstreiaTest(){
        Calendar dataEstreia = Calendar.getInstance();
        dataEstreia.getTime();
        atracao.setDataEstreia(dataEstreia);
        assertEquals(dataEstreia, atracao.getDataEstreia());
    }

    @Test
    public void getClassificacaoIndicativaTest(){
        int classificacaoIndicativa = 1;
        atracao.setClassificacaoIndicativa(classificacaoIndicativa);
        assertEquals(classificacaoIndicativa, atracao.getClassificacaoIndicativa());
    }

    @Test
    public void setClassificacaoIndicativaTest(){
        int classificacaoIndicativa = 1;
        atracao.setClassificacaoIndicativa(classificacaoIndicativa);
        assertEquals(classificacaoIndicativa, atracao.getClassificacaoIndicativa());
    }
}
