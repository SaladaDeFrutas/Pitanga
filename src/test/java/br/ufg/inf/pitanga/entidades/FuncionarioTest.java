package br.ufg.inf.pitanga.entidades;

import br.ufg.inf.pitanga.entidades.enums.TipoFuncionario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FuncionarioTest {

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
    public void testGetNivelAcesso() {
        TipoFuncionario resultadoEsperado = TipoFuncionario.GERENTE;
        TipoFuncionario resultado = funcionarioTeste.getNivelAcesso();
        assertEquals(resultadoEsperado, resultado);
    }
    
    @Test
    public void testSetNivelAcesso() {
        TipoFuncionario nivelAcesso = TipoFuncionario.ADMINISTRADOR;
        funcionarioTeste.setNivelAcesso(nivelAcesso);
        assertEquals(nivelAcesso, funcionarioTeste.getNivelAcesso());
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
