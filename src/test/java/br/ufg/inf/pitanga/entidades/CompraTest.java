package br.ufg.inf.pitanga.entidades;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CompraTest {

    @Test
    public void testaSetValorTotalDeveSerCalculadoComSucesso(){

        Cliente cliente = criaClienteParaTeste();

        BigDecimal valor1 = new BigDecimal("10.99");
        BigDecimal valor2 = new BigDecimal("8.57");
        Ingresso ingresso1 = criaIngressoParaTeste(valor1);
        Ingresso ingresso2 = criaIngressoParaTeste(valor2);
        List<Ingresso> ingressos = new ArrayList<Ingresso>(){{
            add(ingresso1);
            add(ingresso2);
        }};

        Compra compra = new Compra(cliente, ingressos);
        BigDecimal valorTotal = valor1.add(valor2);
        assertEquals(valorTotal, compra.getValorTotal());
    }

    private Cliente criaClienteParaTeste(){
        return new Cliente();
    }

    private Ingresso criaIngressoParaTeste(BigDecimal precoDoIngresso){
        Ingresso ingresso = new Ingresso();
        TipoIngresso tipoIngresso = new TipoIngresso();
        tipoIngresso.setPreco(precoDoIngresso);
        ingresso.setUmTipoIngresso(tipoIngresso);
        return ingresso;
    }
}
