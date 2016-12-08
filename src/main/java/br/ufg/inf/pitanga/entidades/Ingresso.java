package br.ufg.inf.pitanga.entidades;

import javax.persistence.*;

@Entity
@Table(name = "ingressos")
public class Ingresso {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "email")
    private Cliente umCliente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSessao")
    private Sessao umaSessao;
    @OneToOne
    private Assento umAssento;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeTipoIngresso")
    private TipoIngresso umTipoIngresso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getUmCliente() {
        return umCliente;
    }

    public void setUmCliente(Cliente umCliente) {
        this.umCliente = umCliente;
    }

    public Sessao getUmaSessao() {
        return umaSessao;
    }

    public void setUmaSessao(Sessao umaSessao) {
        this.umaSessao = umaSessao;
    }

    public Assento getUmAssento() {
        return umAssento;
    }

    public void setUmAssento(Assento umAssento) {
        this.umAssento = umAssento;
    }

    public TipoIngresso getUmTipoIngresso() {
        return umTipoIngresso;
    }

    public void setUmTipoIngresso(TipoIngresso umTipoIngresso) {
        this.umTipoIngresso = umTipoIngresso;
    }

}
