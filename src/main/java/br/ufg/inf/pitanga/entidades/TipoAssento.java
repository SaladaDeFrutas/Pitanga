package br.ufg.inf.pitanga.entidades;

import javax.persistence.*;

@Entity
@Table(name = "tiposassento")
public class TipoAssento {

    @Id
    @GeneratedValue
    @Column(name = "idTipoAssento")
    private int id;
    private double preco;
    private String nome;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
