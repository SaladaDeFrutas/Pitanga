package br.ufg.inf.pitanga.entidades;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Embeddable
public class Assento {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTipoAssento")
    private TipoAssento tipoAssento;


    private int coluna;
    private int fileira;

    @Autowired
    public Assento() {

    }

    public int getFileira() {
        return fileira;
    }

    public void setFileira(int fileira) {
        this.fileira = fileira;
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

}
