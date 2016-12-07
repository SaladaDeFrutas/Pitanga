package br.ufg.inf.pitanga.entidades;

import javax.persistence.*;

@Entity
@Table(name = "assento")
public class Assento {

    @Id
    @GeneratedValue
    @Column(name = "idAssento")
    private Long id;

    private TipoAssento tipoAssento;

    @ManyToOne
    @JoinColumn(name = "idSala")
    private Sala sala;

    private int coluna;
    private int fila;
    private String nome;

    public Assento(Sala sala) {
        this.sala = sala;
    }

    public Assento(){

    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public TipoAssento getTipoAssento() {
        return tipoAssento;
    }

    public void setTipoAssento(TipoAssento tipoAssento) {
        this.tipoAssento = tipoAssento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
