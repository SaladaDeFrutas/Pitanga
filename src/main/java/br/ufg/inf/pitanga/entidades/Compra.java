package br.ufg.inf.pitanga.entidades;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "registroscompra")
public class Compra {

    @Id
    @GeneratedValue
    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Calendar dataCompra;
    @OneToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    @NotNull
    private BigDecimal valorTotal;

    @NotNull
    @OneToMany
    @JoinColumn(name = "id")
    private List<Ingresso> ingressos;
    private boolean pagamentoAprovado;
    private String codigoTransacao;

    public Compra() {
    }

    public Compra(Cliente cliente, List<Ingresso> ingressos) {
        this.cliente = cliente;
        this.ingressos = ingressos;
        this.valorTotal = calcularTotal(ingressos);
    }

    private BigDecimal calcularTotal(List<Ingresso> ingressos) {
        BigDecimal valor = BigDecimal.ZERO;

        for (Ingresso ingresso : ingressos) {
            valor = valor.add(ingresso.getUmTipoIngresso().getPreco());
        }
        return valor;
    }

    public Long getId() {
        return id;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente umCliente) {
        this.cliente = umCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor total de uma compra não pode ser nulo");
        } else {
            this.valorTotal = valor;
        }
    }

    public boolean isPagamentoAprovado() {
        return pagamentoAprovado;
    }

    public void setPagamentoAprovado(boolean pagamentoAprovado) {
        this.pagamentoAprovado = pagamentoAprovado;
    }

    public String getCodigoTransacao() {
        return codigoTransacao;
    }

    public void setCodigoTransacao(String codigoTransacao) {
        this.codigoTransacao = codigoTransacao;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
}
