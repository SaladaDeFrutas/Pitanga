package br.ufg.inf.test;

import br.ufg.inf.pitanga.entidades.*;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by gleibson on 02/12/16.
 */
public class IngressoTest {

    private Ingresso ingresso = new Ingresso();

    @Test
    public void getRegistroCompraTest(){
        RegistroCompra registroCompra = new RegistroCompra();
        ingresso.setRegistroCompra(registroCompra);
        assertEquals(registroCompra, ingresso.getRegistroCompra());
    }

    @Test
    public void setRegistroCompraTest(){
        RegistroCompra registroCompra = new RegistroCompra();
        ingresso.setRegistroCompra(registroCompra);
        assertEquals(registroCompra, ingresso.getRegistroCompra());
    }

    @Test
    public void getIdTest(){
        long idIngresso = 898098;
        ingresso.setId(idIngresso);
        assertEquals(idIngresso, ingresso.getId());
    }

    @Test
    public void setIdTest(){
        long idIngresso = 894378;
        ingresso.setId(idIngresso);
        assertEquals(idIngresso, ingresso.getId());
    }

    @Test
    public void getUmClienteTest(){
        Cliente cliente = new Cliente();
        ingresso.setUmCliente(cliente);
        assertEquals(cliente, ingresso.getUmCliente());
    }

    @Test
    public void setUmClienteTest(){
        Cliente cliente = new Cliente();
        ingresso.setUmCliente(cliente);
        assertEquals(cliente, ingresso.getUmCliente());
    }

    @Test
    public void getUmaSessaoTest(){
        Sessao sessao = new Sessao();
        ingresso.setUmaSessao(sessao);
        assertEquals(sessao, ingresso.getUmaSessao());
    }

    @Test
    public void setUmaSessaoTest(){
        Sessao sessao = new Sessao();
        ingresso.setUmaSessao(sessao);
        assertEquals(sessao, ingresso.getUmaSessao());
    }

    @Test
    public void getUmAssentoTest(){
        Assento assento = new Assento();
        ingresso.setUmAssento(assento);
        assertEquals(assento, ingresso.getUmAssento());
    }

    @Test
    public void setUmAssentoTest(){
        Assento assento = new Assento();
        ingresso.setUmAssento(assento);
        assertEquals(assento, ingresso.getUmAssento());
    }

    @Test
    public void getUmTipoIngressoTest(){
        TipoIngresso tipoIngresso = new TipoIngresso();
        ingresso.setUmTipoIngresso(tipoIngresso);
        assertEquals(tipoIngresso, ingresso.getUmTipoIngresso());
    }

    @Test
    public void setUmTipoIngressoTest(){
        TipoIngresso tipoIngresso = new TipoIngresso();
        ingresso.setUmTipoIngresso(tipoIngresso);
        assertEquals(tipoIngresso, ingresso.getUmTipoIngresso());
    }
}
