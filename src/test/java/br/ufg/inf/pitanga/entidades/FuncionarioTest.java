package br.ufg.inf.pitanga.entidades;

import br.ufg.inf.pitanga.entidades.enums.TipoFuncionario;
import java.security.InvalidParameterException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class FuncionarioTest {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    private Funcionario funcionarioTeste;

    @Before
    public void setUp() {
        int matricula = 123456;
        funcionarioTeste = new Funcionario(TipoFuncionario.GERENTE, matricula);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetNivelAcessoCorreto() {
        TipoFuncionario resultadoEsperado = TipoFuncionario.GERENTE;
        TipoFuncionario resultado = funcionarioTeste.getNivelAcesso();
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testGetNivelAcessoIncorreto() {
        TipoFuncionario resultadoEsperado = TipoFuncionario.AUXILIAR;
        TipoFuncionario resultado = funcionarioTeste.getNivelAcesso();
        assertNotEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testSetNivelAcessoCorreto() {
        TipoFuncionario nivelAcesso = TipoFuncionario.ADMINISTRADOR;
        funcionarioTeste.setNivelAcesso(nivelAcesso);
        assertEquals(nivelAcesso, funcionarioTeste.getNivelAcesso());
    }

    @Test
    public void testSetNivelAcessoNull() {
        excecaoEsperada.expect(InvalidParameterException.class);
        excecaoEsperada.expectMessage(Funcionario.MENSAGEM_NIVEL_ACESSO_VAZIO);

        TipoFuncionario nivelAcesso = null;
        funcionarioTeste.setNivelAcesso(nivelAcesso);
        fail();
    }

    @Test
    public void testGetMatricula() {
        int resultadoEsperado = 123456;
        int resultado = funcionarioTeste.getMatricula();
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testSetMatricula() {
        int matricula = 987654;
        funcionarioTeste.setMatricula(matricula);
        assertEquals(matricula, funcionarioTeste.getMatricula());
    }

}
