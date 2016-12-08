package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.dao.ClienteDao;
import br.ufg.inf.pitanga.entidades.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteDao clienteDao;

    @Test
    public void testeCadastrarCliente(){
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1990,11,06);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123");

        clienteService.cadastrarCliente(cliente);
        assertEquals(clienteService.cadastrarCliente(cliente), clienteDao.buscarPorId(cliente.getEmail()))
    }

}
