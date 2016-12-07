package br.ufg.inf.pitanga.entidades;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CompraTest {

    Compra compra = new Compra();

    @Test
    public void testaSetValorTotalDeveSerCalculadoComSucesso() {

        Cliente cliente = criaClienteParaTeste();

        BigDecimal valor1 = new BigDecimal("10.99");
        BigDecimal valor2 = new BigDecimal("8.57");
        Ingresso ingresso1 = criaIngressoParaTeste(valor1);
        Ingresso ingresso2 = criaIngressoParaTeste(valor2);
        List<Ingresso> ingressos = new ArrayList<Ingresso>() {{
            add(ingresso1);
            add(ingresso2);
        }};

        Compra compra = new Compra(cliente, ingressos);
        BigDecimal valorTotal = valor1.add(valor2);
        assertEquals(valorTotal, compra.getValorTotal());
    }

    @Test
    public void testaSetPagamentoAprovado() {
        boolean pagamentoAprovado = false;
        compra.setPagamentoAprovado(pagamentoAprovado);
        assertEquals(pagamentoAprovado, compra.isPagamentoAprovado());
    }

    @Test
    public void testaSetDataCompra() {
        String dataCompra = "20/10/2016";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = simpleDateFormat.parse(dataCompra);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            compra.setDataCompra(calendar);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(dataCompra, simpleDateFormat.format(compra.getDataCompra().getTime()));
    }

    private Cliente criaClienteParaTeste() {
        return new Cliente();
    }

    private Ingresso criaIngressoParaTeste(BigDecimal precoDoIngresso) {
        Ingresso ingresso = new Ingresso();
        TipoIngresso tipoIngresso = new TipoIngresso();
        tipoIngresso.setPreco(precoDoIngresso);
        ingresso.setUmTipoIngresso(tipoIngresso);
        return ingresso;
    }
}
