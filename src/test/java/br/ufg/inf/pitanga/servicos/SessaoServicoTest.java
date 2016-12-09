package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Sala;
import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.repository.SessaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class SessaoServicoTest {

    @Autowired
    private SessaoServico sessaoServico;

    @Autowired
    private SessaoRepository sessaoRepository;

    private Sessao sessao;

    @Before
    public void criaSessao() {
        sessao = new Sessao();
    }

    @Test
    public void testaObtenhaSalaPeloIdComSucesso() {
        sessao = new Sessao();
        Sessao sessaoSalva = sessaoRepository.save(sessao);
        Sessao sessaoObtida = sessaoServico.buscaSessaoPorId(sessaoSalva.getId());
        assertEquals(sessaoSalva.getId().toString(), sessaoObtida.getId().toString());
    }

    @Test
    public void testaObtenhaSalaComIdInexistenteELancaIllegalArgumentException() {
        assertNull(sessaoServico.buscaSessaoPorId(2L));
    }
}
