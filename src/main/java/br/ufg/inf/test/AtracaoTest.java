package br.ufg.inf.test;

import br.ufg.inf.pitanga.entidades.Atracao;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by gleibson on 02/12/16.
 */

public class AtracaoTest {

    private Atracao atracao = (Atracao) new Object();

    @Test
    public void getIdAtracaoTest(){
        int idAtracao = 178;
        atracao.setIdAtracao(idAtracao);
        assertEquals(idAtracao, atracao.getIdAtracao());
    }

    @Test
    public void setIdAtracaoTest(){
        int idAtracao = 178;
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
}
