package br.ufg.inf.pitanga.entidades;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class UsuarioTest {
    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();
            
    private Usuario usuario;
    
    @Before
    public void setUp() throws ParseException {
        usuario = new UsuarioImpl();
        usuario.setNome("Usuario de Teste");
        usuario.setEmail("usuario@teste.com");
        usuario.setSenha("minhasenha");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Usuario.FORMATO_DATA_NASCIMENTO);
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(simpleDateFormat.parse("02/10/1990"));
        usuario.setDataDeNascimento(dataNascimento);
    }

    @Test
    public void testGetNomeCorreto() {
        String resultadoEsperado = "Usuario de Teste";
        String resultado = usuario.getNome();
        assertEquals(resultadoEsperado, resultado);
    }
    
    @Test
    public void testGetNomeIncorreto() {
        String resultadoEsperado = "Usuario Apenas";
        String resultado = usuario.getNome();
        assertNotEquals(resultadoEsperado, resultado);
    }
    
    @Test
    public void testGetNomeNull() {
        String resultadoEsperado = null;
        String resultado = usuario.getNome();
        assertNotEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testSetNomeCorreto() {
        String nome = "Novo Nome Usuário";
        usuario.setNome(nome);
        String resultado = usuario.getNome();
        assertEquals(nome, resultado);
    }
        
    @Test
    public void testSetNomeNull() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_NOME_VAZIO);
        
        String nome = null; 
        usuario.setNome(nome);
        fail();
    }
    
    @Test
    public void testSetNomeVazio() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_NOME_VAZIO);
        
        String nome = ""; 
        usuario.setNome(nome);
        fail();
    }
    
    @Test
    public void testSetNomeSoEspacos() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_NOME_VAZIO);
        
        String nome = "     "; 
        usuario.setNome(nome);
        fail();
    }

    @Test
    public void testGetEmail() {
        String expResult = "usuario@teste.com";
        String result = usuario.getEmail();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetEmailIncorreto() {
        String expResult = "outro.usuario@teste.com";
        String result = usuario.getEmail();
        assertNotEquals(expResult, result);
    }

    @Test
    public void testSetEmailCorreto() {
        String email = "novo.usuario@email.com";
        usuario.setEmail(email);
        assertEquals(email, usuario.getEmail());
    }
    
    @Test
    public void testSetEmailNull() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_EMAIL_VAZIO);
        
        String email = null; 
        usuario.setEmail(email);
        fail();
    }
    
    
    @Test
    public void testSetEmailVazio() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_EMAIL_VAZIO);
        
        String email = ""; 
        usuario.setEmail(email);
        fail();
    }
    
    
    @Test
    public void testSetEmailSoEspacos() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_EMAIL_VAZIO);
        
        String email = "     "; 
        usuario.setEmail(email);
        fail();
    }
    
    
    @Test
    public void testSetEmailInvalido() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_EMAIL_INVALIDO);
        
        String email = "email.nao.valido"; 
        usuario.setEmail(email);
        fail();
    }

    @Test
    public void testGetDataDeNascimentoCorreta() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Usuario.FORMATO_DATA_NASCIMENTO);
        Calendar resultadoEsperado = Calendar.getInstance();
        resultadoEsperado.setTime(simpleDateFormat.parse("02/10/1990"));
        Calendar resultado = usuario.getDataDeNascimento();
        assertEquals(resultadoEsperado, resultado);
    }
    
    @Test
    public void testGetDataDeNascimentoIncorreta() {
        Calendar resultadoEsperado = Calendar.getInstance();
        Calendar resultado = usuario.getDataDeNascimento();
        assertNotEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testSetDataDeNascimento() throws ParseException {
        int idadeAnos = 19;
        Calendar dataDeNascimento = Calendar.getInstance();
        dataDeNascimento.add(Calendar.YEAR, -idadeAnos);
        usuario.setDataDeNascimento(dataDeNascimento);
        
        assertEquals(dataDeNascimento, usuario.getDataDeNascimento());
    }
    
    @Test
    public void testSetDataDeNascimentoNull() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_DATA_NASCIMENTO_INVALIDA);
        
        Calendar dataDeNascimento = null;
        usuario.setDataDeNascimento(dataDeNascimento);
        fail();
    }

    @Test
    public void testSetDataDeNascimentoMenorMinimo() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_DATA_NASCIMENTO_INVALIDA);
        
        Calendar dataDeNascimento = Calendar.getInstance();
        dataDeNascimento.add(Calendar.YEAR, -Usuario.IDADE_MINIMA);
        dataDeNascimento.add(Calendar.DAY_OF_MONTH, 1);
        
        usuario.setDataDeNascimento(dataDeNascimento);
        fail();
    }
    
    @Test
    public void testSetDataDeNascimentoIgualMinimo() {
        Calendar dataDeNascimento = Calendar.getInstance();
        dataDeNascimento.add(Calendar.YEAR, -Usuario.IDADE_MINIMA);
        
        usuario.setDataDeNascimento(dataDeNascimento);
        
        assertFalse(usuario.getDataDeNascimento().after(dataDeNascimento));
    }
    
    @Test
    public void testSetDataDeNascimentoMaiorMinimo() {
        Calendar dataLimite = Calendar.getInstance();
        dataLimite.add(Calendar.YEAR, -Usuario.IDADE_MINIMA);
        Calendar dataNascimento = (Calendar) dataLimite.clone();
        dataNascimento.add(Calendar.DAY_OF_MONTH, -1);
        
        usuario.setDataDeNascimento(dataNascimento);
        
        assertTrue(usuario.getDataDeNascimento().before(dataLimite));
    }
    
    
    @Test
    public void testGetSenhaCorreta() {
        String resultadoEsperado = "minhasenha";
        String resultado = usuario.getSenha();
        assertEquals(resultadoEsperado, resultado);
    }
    
    @Test
    public void testGetSenhaIncorreta() {
        String resultadoEsperado = "outrasenha";
        String resultado = usuario.getSenha();
        assertNotEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testSetSenha() {
        String senha = "novasenha";
        usuario.setSenha(senha);
        
        assertEquals(senha, usuario.getSenha());
    }
    
     @Test
    public void testSetSenhaNull() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_SENHA_VAZIA);
        
        String senha = null; 
        usuario.setSenha(senha);
        fail();
    }
    
    
     @Test
    public void testSetSenhaVazia() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_SENHA_VAZIA);
        
        String senha = ""; 
        usuario.setSenha(senha);
        fail();
    }
    
    
     @Test
    public void testSetSenhaSoEspacos() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_SENHA_VAZIA);
        
        String senha = "     "; 
        usuario.setSenha(senha);
        fail();
    }
    
    
     @Test
    public void testSetSenhaTamanhoMenor() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Usuario.MENSAGEM_SENHA_VAZIA);
        
        String senha = "minha"; 
        usuario.setSenha(senha);
        fail();
    }
    
    
     @Test
    public void testSetSenhaTamanhoMinimo() {
        String senha = "minhas"; 
        usuario.setSenha(senha);
        
         assertEquals(senha, usuario.getSenha());
    }

    public class UsuarioImpl extends Usuario {}
    
}
