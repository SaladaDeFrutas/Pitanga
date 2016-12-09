package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Atracao;
import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;
import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.repository.AtracaoRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtracaoServiceTest {

    @Autowired
    private AtracaoService atracaoService;

    @Autowired
    private AtracaoRepository atracaoRepository;

    private List<Object> lista = new ArrayList<>();

    private Long idFilme = 83454365L;

    private Peca peca = new Peca();

    private Sessao sessao = new Sessao();

    @Test
    public void listarFilmesTest () throws Exception {
        Atracao atracao = new Filme();
        lista.add(atracao);
        List<Filme> retorno = atracaoService.listarFilmes();
        assertEquals(lista, retorno);
        lista.clear();
    }

    @Test
    public void listarPecasTest () throws Exception {
        lista.add(peca);
        List<Peca> retorno = atracaoService.listarPecas();
        assertEquals(lista, retorno);
        lista.clear();
    }

    @Test
    public void buscaFilmePorId () throws Exception {
        Atracao filme = new Filme();
        Atracao atracaoSalva = atracaoRepository.save(filme);
        Atracao atracaoObtida = atracaoService.buscarFilmePorId(atracaoSalva.getId());

        assertEquals(atracaoSalva.getId(), atracaoObtida.getId());
    }



}
