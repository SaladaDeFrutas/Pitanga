package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.TipoIngresso;
import br.ufg.inf.pitanga.repository.TipoIngressoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TipoIngressoServicoTest {

    @Autowired
    private TipoIngressoServico tipoIngressoServico;

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    @Test
    public void testaObterUmTipoIngressoInexistente() {
        String nomeIngressoInexistente = UUID.randomUUID().toString();
        TipoIngresso tipoIngressoEncontrado = tipoIngressoServico.obtenhaTipoIngressoPorNome(nomeIngressoInexistente);
        assertNull(tipoIngressoEncontrado);
    }

    @Test
    public void testaListarUmIngresso() {
        TipoIngresso tipoIngresso = new TipoIngresso();
        BigDecimal preco = new BigDecimal(1.20);
        tipoIngresso.setNome("normal");
        tipoIngresso.setPreco(preco);
        tipoIngressoRepository.save(tipoIngresso);
        TipoIngresso tipoIngressoEncontrado = tipoIngressoServico.obtenhaTipoIngressoPorNome(tipoIngresso.getNome());
        assertEquals(tipoIngresso.getNome(), tipoIngressoEncontrado.getNome());
        assertEquals(tipoIngresso.getPreco().floatValue(), tipoIngressoEncontrado.getPreco().floatValue(), 0.05);
    }

    @Test
    public void testaListarTodosIngressos() {
        TipoIngresso tipoIngresso1 = new TipoIngresso();
        BigDecimal preco1 = new BigDecimal(11);
        tipoIngresso1.setNome("diferente");
        tipoIngresso1.setPreco(preco1);
        tipoIngresso1 = tipoIngressoRepository.save(tipoIngresso1);

        TipoIngresso tipoIngresso2 = new TipoIngresso();
        BigDecimal preco2 = new BigDecimal(35);
        tipoIngresso2.setNome("normal");
        tipoIngresso2.setPreco(preco2);
        tipoIngresso2 = tipoIngressoRepository.save(tipoIngresso2);

        Iterable<TipoIngresso> tiposIngressos = tipoIngressoServico.listarTodosTiposIngresso();
        List<TipoIngresso> listaTipoIngressos = new ArrayList<>();
        for (TipoIngresso tipoIngressoEncontrado : tiposIngressos) {
            listaTipoIngressos.add(tipoIngressoEncontrado);
        }
        listaTipoIngressos.sort((o1, o2) -> o1.getNome().compareTo(o2.getNome()));

        TipoIngresso tipoIngressoEncontrado1 = listaTipoIngressos.get(0);
        assertEquals(tipoIngresso1.getNome(), tipoIngressoEncontrado1.getNome());
        assertEquals(tipoIngresso1.getPreco().floatValue(), tipoIngressoEncontrado1.getPreco().floatValue(), 0.05);
        TipoIngresso tipoIngressoEncontrado2 = listaTipoIngressos.get(1);
        assertEquals(tipoIngresso2.getNome(), tipoIngressoEncontrado2.getNome());
        assertEquals(tipoIngresso2.getPreco().floatValue(), tipoIngressoEncontrado2.getPreco().floatValue(), 0.05);
    }

}
