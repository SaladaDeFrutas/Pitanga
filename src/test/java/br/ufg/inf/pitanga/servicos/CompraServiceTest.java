package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.entidades.CompraDTO;
import br.ufg.inf.pitanga.repository.ClienteRepository;
import br.ufg.inf.pitanga.repository.CompraRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    @Test
    public void testaObtenhaComprasDeUmClienteComSucesso() {
        Cliente cliente = criarCliente();
        String dataCompra = "04/12/2016";

        Compra compra = adicionarCompraParaCliente(cliente, dataCompra);
        CompraDTO compraDTO = compraServico.obtenhaComprasDoCliente(cliente.getEmail()).get(0);

        assertEquals(compraDTO.getDataCompra(), dataCompra);
        assertEquals(compraDTO.getNomeCliente(), compra.getCliente().getNome());
        assertEquals(compraDTO.getIdCompra(), compra.getId());
        assertEquals(compraDTO.getValorCompra(), compra.getValor());
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
        Calendar data = converteStringParaCalendar(dataCompra, "dd/MM/yyyy");

        Compra compra = new Compra();
        compra.setCodigoTransacao(codigoTransacao);
        compra.setCliente(cliente);
        compra.setValor(valor);
        compra.setPagamentoAprovado(false);
        compra.setDataCompra(data);

        return compraRepository.save(compra);
    }

    private Calendar converteStringParaCalendar(String data, String formato) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
        Calendar dataCalendar = Calendar.getInstance();
        try {
            dataCalendar.setTime(simpleDateFormat.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataCalendar;
    }
}
