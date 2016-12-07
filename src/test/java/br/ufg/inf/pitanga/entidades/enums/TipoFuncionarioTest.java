package br.ufg.inf.pitanga.entidades.enums;

import org.junit.Test;
import static org.junit.Assert.*;

public class TipoFuncionarioTest {

    @Test
    public void testValueOfGerente() {
        String nome = "GERENTE";
        TipoFuncionario resultadoEsperado = TipoFuncionario.GERENTE;
        TipoFuncionario resultado = TipoFuncionario.valueOf(nome);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testValueOfAdministrador() {
        String nome = "ADMINISTRADOR";
        TipoFuncionario resultadoEsperado = TipoFuncionario.ADMINISTRADOR;
        TipoFuncionario resultado = TipoFuncionario.valueOf(nome);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testValueOfAtendente() {
        String nome = "ATENDENTE";
        TipoFuncionario resultadoEsperado = TipoFuncionario.ATENDENTE;
        TipoFuncionario resultado = TipoFuncionario.valueOf(nome);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testValueOfAuxiliar() {
        String nome = "AUXILIAR";
        TipoFuncionario resultadoEsperado = TipoFuncionario.AUXILIAR;
        TipoFuncionario resultado = TipoFuncionario.valueOf(nome);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testGetValorNumericoGerente() {
        TipoFuncionario instancia = TipoFuncionario.GERENTE;
        int resultadoEsperado = 2;
        int resultado = instancia.getValorNumerico();
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testGetValorNumericoAdministrador() {
        TipoFuncionario instancia = TipoFuncionario.ADMINISTRADOR;
        int resultadoEsperado = 3;
        int resultado = instancia.getValorNumerico();
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testGetValorNumericoAtendente() {
        TipoFuncionario instancia = TipoFuncionario.ATENDENTE;
        int resultadoEsperado = 4;
        int resultado = instancia.getValorNumerico();
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testGetValorNumericoAuxiliar() {
        TipoFuncionario instancia = TipoFuncionario.AUXILIAR;
        int resultadoEsperado = 5;
        int resultado = instancia.getValorNumerico();
        assertEquals(resultadoEsperado, resultado);
    }

}
