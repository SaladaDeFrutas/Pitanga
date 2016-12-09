package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testeCadastrarClienteValido() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1990, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456");

        clienteService.cadastrarCliente(cliente);

        Cliente clienteSalvo = clienteService.recuperarClientePorEmail(cliente.getEmail());

        assertEquals(cliente.getNome(), clienteSalvo.getNome());
        assertEquals(cliente.getEmail(), clienteSalvo.getEmail());
        assertEquals(cliente.getDataDeNascimento(), clienteSalvo.getDataDeNascimento());
        assertEquals(cliente.getSenha(), clienteSalvo.getSenha());
    }

    @Test(expected = InvalidParameterException.class)
    public void testeCadastrarClienteNull() {
        Cliente cliente = null;
        clienteService.cadastrarCliente(cliente);
    }

    @Test
    public void testeCadastroClienteNomeGrande() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1990, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456");

        Cliente clienteSalvo = clienteService.cadastrarCliente(cliente);

        assertNull(clienteSalvo);
    }

    @Test
    public void testeCadastroClienteNomePequeno() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1990, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("aaa");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456");

        Cliente clienteSalvo = clienteService.cadastrarCliente(cliente);

        assertNull(clienteSalvo);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeCadastrarClienteNomeNull() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1990, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome(null);
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456");

        clienteService.cadastrarCliente(cliente);
    }

    @Test
    public void testeCadastroClienteDataDeNascimentoAnoInvalido() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1500, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456");

        Cliente clienteSalvo = clienteService.cadastrarCliente(cliente);
        assertNull(clienteSalvo);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeCadastroClienteDataDeNascimentoInvalida() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(2030, 5, 20);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456");

        clienteService.cadastrarCliente(cliente);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeCadastroClienteDataDeNascimentoNull() {
        Calendar dataNascimento = null;

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456");

        clienteService.cadastrarCliente(cliente);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeCadastroClienteEmailSemArrouba() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1992, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva.live.com");
        cliente.setSenha("123456");

        clienteService.cadastrarCliente(cliente);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeCadastroClienteEmailSemPontoCom() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1992, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@livecom");
        cliente.setSenha("123456");

        clienteService.cadastrarCliente(cliente);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeCadastroClienteEmailNull() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1992, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail(null);
        cliente.setSenha("123456");

        clienteService.cadastrarCliente(cliente);
    }

    @Test
    public void testeCadastroClienteSenhaGrande() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1992, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456789012345678901234567890");

        Cliente clienteGravado = clienteService.cadastrarCliente(cliente);
        assertNull(clienteGravado);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeCadastroClienteSenhaPequena() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1992, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123");

        clienteService.cadastrarCliente(cliente);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeCadastroClienteSenhaNull() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1992, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha(null);

        clienteService.cadastrarCliente(cliente);
    }

    @Test
    public void testeRecuperarClientePorEmail() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1992, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456");

        clienteService.cadastrarCliente(cliente);
        Cliente clienteRecuperadoPorEmail = clienteService.recuperarClientePorEmail(cliente.getEmail());
        assertEquals(cliente.getNome(), clienteRecuperadoPorEmail.getNome());
        assertEquals(cliente.getSenha(), clienteRecuperadoPorEmail.getSenha());
        assertEquals(cliente.getDataDeNascimento(), clienteRecuperadoPorEmail.getDataDeNascimento());
        assertEquals(cliente.getEmail(), clienteRecuperadoPorEmail.getEmail());
    }

    @Test(expected = InvalidParameterException.class)
    public void testeRecuperarClientePorEmailEmBranco() {
        String email = "";
        clienteService.recuperarClientePorEmail(email);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeRecuperarClientePorEmailNull() {
        String email = null;
        clienteService.recuperarClientePorEmail(email);
    }

    @Test
    public void testeDeletarCliente() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1992, 11, 6);

        Cliente cliente = new Cliente();
        cliente.setNome("Marcos Vinicius Ribeiro Silva");
        cliente.setDataDeNascimento(dataNascimento);
        cliente.setEmail("marcos.v.silva@live.com");
        cliente.setSenha("123456");

        clienteService.cadastrarCliente(cliente);
        clienteService.deletarCliente(cliente);
        Cliente clienteRecuperadoPorEmailAposExclusao = clienteService.recuperarClientePorEmail(cliente.getEmail());
        assertNull(clienteRecuperadoPorEmailAposExclusao);
    }

    @Test(expected = InvalidParameterException.class)
    public void testeDeletarClienteNull() {
        Cliente cliente = null;
        clienteService.deletarCliente(cliente);
    }

    @Test
    public void testeAlterarCliente() {
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.set(1992, 11, 6);
        String nomeOriginal = "Marcos Vinicius Ribeiro Silva";
        String nomeAlterado = "João da Silva";
        String email = "marcos.v.silva@live.com";
        String senha = "123456";

        Cliente clientePrincipal = new Cliente();
        clientePrincipal.setNome(nomeOriginal);
        clientePrincipal.setDataDeNascimento(dataNascimento);
        clientePrincipal.setEmail(email);
        clientePrincipal.setSenha(senha);
        clienteService.cadastrarCliente(clientePrincipal);

        Cliente clienteAlterado = new Cliente();
        clienteAlterado.setNome(nomeAlterado);
        clienteAlterado.setDataDeNascimento(dataNascimento);
        clienteAlterado.setEmail(email);
        clienteAlterado.setSenha(senha);
        clienteService.alterarCliente(clienteAlterado);

        Cliente clienteGravado = clienteService.recuperarClientePorEmail(email);

        assertEquals(clienteAlterado.getNome(), clienteGravado.getNome());
        assertEquals(clienteAlterado.getSenha(), clienteGravado.getSenha());
        assertEquals(clienteAlterado.getDataDeNascimento(), clienteGravado.getDataDeNascimento());
        assertEquals(clienteAlterado.getEmail(), clienteGravado.getEmail());
    }

    @Test(expected = InvalidParameterException.class)
    public void testeAlterarClienteNull() {
        Cliente cliente = null;
        clienteService.alterarCliente(cliente);
    }

}
