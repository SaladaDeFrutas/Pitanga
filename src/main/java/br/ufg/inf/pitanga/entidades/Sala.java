package br.ufg.inf.pitanga.entidades;

import br.ufg.inf.pitanga.excecao.AssentoForaDoTamanhoDaSalaExcecao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue
    @Column(name = "idSala")
    private Long id;


    private int filas;
    private int colunas;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Assento> assentos;

    @OneToMany
    @JoinColumn(name = "idSessao")
    private List<Sessao> sessoes;


    public Sala() {
        this.assentos = new ArrayList<>();
        this.sessoes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null && id < 0)
            throw new IllegalArgumentException();

        this.id = id;
    }

    public List<Assento> getAssentos() {
        return assentos;
    }

    public void setAssentos(List<Assento> assentos) {
        for (Assento assento : assentos) {
            if (assento.getFila() > this.getFilas() || assento.getColuna() > this.getColunas())
                throw new AssentoForaDoTamanhoDaSalaExcecao();
        }
        this.assentos = assentos;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        if (filas <= 0)
            throw new IllegalArgumentException();

        this.filas = filas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        if (colunas <= 0)
            throw new IllegalArgumentException();

        this.colunas = colunas;
    }
}
