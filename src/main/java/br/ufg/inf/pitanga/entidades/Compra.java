package br.ufg.inf.pitanga.entidades;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "compra")
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
    private List<Ingresso> ingressos;
    private boolean pagamentoAprovado;
    private String codigoTransacao;

    public Compra() {
        this.ingressos = new ArrayList<>();
        this.valorTotal = new BigDecimal(0);
    }

    public Compra(Cliente cliente, List<Ingresso> ingressos, Calendar dataCompra) {
        this.cliente = cliente;
        this.ingressos = ingressos;
        this.valorTotal = calcularTotal(ingressos);
        this.dataCompra = dataCompra;
        this.pagamentoAprovado = false;
        this.ingressos = new ArrayList<>();
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

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        if (dataCompra == null) {
            throw new IllegalArgumentException();
        } else {
            this.dataCompra = dataCompra;
        }
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
            throw new IllegalArgumentException();
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
        if (codigoTransacao == null || "".equals(codigoTransacao)) {
            throw new IllegalArgumentException();
        } else {
            this.codigoTransacao = codigoTransacao;
        }
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        if (ingressos == null || ingressos.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.ingressos = ingressos;
        }
    }
}
