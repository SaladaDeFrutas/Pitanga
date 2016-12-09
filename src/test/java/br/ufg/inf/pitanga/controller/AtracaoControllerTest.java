package br.ufg.inf.pitanga.controller;

import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;
import org.junit.Before;
import org.springframework.ui.Model;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public abstract class AtracaoControllerTest {

    private AtracaoController atracaoController;

    private Filme filme = new Filme();

    private Peca peca = new Peca();

    protected Model model;

    @Before
    public void instanciaModel(){
        this.model = obtenhaModel();
    }

    protected abstract Model obtenhaModel();

    @Test
    public void mostrarFilmeTest () throws Exception {
        String informacoesFilme = "informacoesFilme";
        String retorno = atracaoController.mostrarFilme(filme, model);
        assertEquals(informacoesFilme, retorno);
    }

    @Test
    public void retornaPaginaAtracoesTest() throws Exception {
        String mostrarAtracoes = "mostrarAtracoes";
        String retorno = atracaoController.retornaPaginaAtracoes(model);
        assertEquals(mostrarAtracoes, retorno);
    }

    @Test
    public void mostrarSessoesFilmeTest () throws Exception {
        String mostrarSessoesFilme = "mostrarSessoesFilme";
        String retorno = atracaoController.mostrarSessoesFilme(filme, model);
        assertEquals(mostrarSessoesFilme, retorno);
    }

    @Test
    public void mostrarSessoesPecaTest () throws Exception {
        String mostrarSessoesPeca = "mostrarSessoesPeca";
        String retorno = atracaoController.mostrarSessoesPeca(peca, model);
        assertEquals(mostrarSessoesPeca, retorno);
    }
}
