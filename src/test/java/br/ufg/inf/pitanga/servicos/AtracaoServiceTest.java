package br.ufg.inf.pitanga.servicos;


import br.ufg.inf.pitanga.PitangaTestHelper;
import br.ufg.inf.pitanga.entidades.Atracao;
import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;
import br.ufg.inf.pitanga.repository.AtracaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtracaoServiceTest {

    @Autowired
    private AtracaoService atracaoService;

    @Autowired
    private AtracaoRepository atracaoRepository;


    @Test
    public void listarFilmesTest () throws Exception {
        Atracao filme = PitangaTestHelper.criaFilme();
        Atracao fileSalvo = atracaoRepository.save(filme);
        Filme filmeObtido = atracaoService.listarFilmes().get(0);
        assertEquals(fileSalvo.getId(), filmeObtido.getId());
        atracaoRepository.delete(fileSalvo);
    }

    @Test
    public void testaListarPecasComSucesso () throws Exception {
        Peca peca = PitangaTestHelper.criaPeca();
        Peca pecaSalva = atracaoRepository.save(peca);

        Peca pecaObtida = atracaoService.listarPecas().get(0);
        assertEquals(pecaSalva.getId(), pecaObtida.getId());
        atracaoRepository.delete(pecaSalva);
    }

    @Test
    public void testaBuscaFilmePorIdComSucesso () throws Exception {
        Atracao filme = PitangaTestHelper.criaFilme();

        Atracao atracaoSalva = atracaoRepository.save(filme);
        Atracao atracaoObtida = atracaoService.buscarFilmePorId(atracaoSalva.getId());

        assertEquals(atracaoSalva.getId(), atracaoObtida.getId());
        atracaoRepository.delete(atracaoSalva);
    }

}
