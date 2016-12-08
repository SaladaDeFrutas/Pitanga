package br.ufg.inf.pitanga.entidades;

import java.math.BigDecimal;

public class CompraDTO {

    private String nomeCliente;
    private Long idCompra;
    private String dataCompra;
    private BigDecimal valorCompra;

    public CompraDTO(String nomeCliente, Long idCompra, String dataCompra, BigDecimal valorCompra) {
        this.nomeCliente = nomeCliente;
        this.idCompra = idCompra;
        this.dataCompra = dataCompra;
        this.valorCompra = valorCompra;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }
}
