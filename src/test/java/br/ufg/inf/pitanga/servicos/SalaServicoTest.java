package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Sala;
import br.ufg.inf.pitanga.repository.SalaRepository;
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
public class SalaServicoTest {

    @Autowired
    private SalaServico salaServico;

    @Autowired
    private SalaRepository salaRepository;

    private Sala sala;

    @Before
    public void criaSala() {
        sala = new Sala();
    }

    @Test
    public void testaObtenhaSalaPeloIdComSucesso() {
        sala = new Sala();
        Sala salaSalva = salaRepository.save(sala);
        Sala salaObtida = salaServico.buscaSalaPorId(salaSalva.getId());
        assertEquals(salaSalva.getId().toString(), salaObtida.getId().toString());
    }

    @Test
    public void testaObtenhaSalaComIdInexistenteELancaIllegalArgumentException() {
        assertNull(salaServico.buscaSalaPorId(2L));
    }

}
