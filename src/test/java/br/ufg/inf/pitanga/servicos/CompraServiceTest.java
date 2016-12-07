package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.CalendarHelperTest;
import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.repository.ClienteRepository;
import br.ufg.inf.pitanga.repository.CompraRepository;
import br.ufg.inf.pitanga.repository.IngressoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompraServiceTest {

    @Autowired
    private CompraServico compraServico;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    @Test
    public void testaObtenhaComprasDeUmClienteComSucesso() {
        Cliente cliente = criarCliente();
        String dataCompra = "04/12/2016";

        Compra compra = adicionarCompraParaCliente(cliente, dataCompra);
        CompraDTO compraDTO = compraServico.obtenhaComprasDoCliente(cliente.getEmail()).get(0);

        assertEquals(compraDTO.getDataCompra(), dataCompra);
        assertEquals(compraDTO.getNomeCliente(), compra.getCliente().getNome());
        assertEquals(compraDTO.getIdCompra(), compra.getId());
        assertEquals(compraDTO.getValorCompra(), compra.getValorTotal());
    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setEmail("teste@email.com");
        cliente.setNome("Nome do cliente");
        return clienteRepository.save(cliente);
    }

    private Compra adicionarCompraParaCliente(Cliente cliente, String dataCompra) {
        String codigoTransacao = "12345";
        BigDecimal valor = new BigDecimal("35.99");
        Calendar data = CalendarHelperTest.converteStringParaCalendar(dataCompra, "dd/MM/yyyy");

        Compra compra = new Compra();
        compra.setCodigoTransacao(codigoTransacao);
        compra.setCliente(cliente);
        compra.setValorTotal(valor);
        compra.setPagamentoAprovado(false);
        compra.setDataCompra(data);
        List<Ingresso> ingressos = criaListaDeIngressos(compra);
        compra.setIngressos(ingressos);

        return compraRepository.save(compra);
    }

    private List<Ingresso> criaListaDeIngressos(Compra compra) {
        Ingresso ingresso = new Ingresso();

        Assento assento = new Assento();
        assento.setColuna(0);
        ingresso.setUmAssento(assento);
        return new ArrayList<Ingresso>() {{
            add(ingresso);
        }};
    }

}
