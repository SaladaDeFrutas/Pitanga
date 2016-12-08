package br.ufg.inf.pitanga.entidades;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tiposingresso")
public class TipoIngresso {
    @Id
    @Column(name = "nomeTipoIngresso")
    @NotEmpty(message = "O nome do tipo de ingresso deve ser preenchido.")
    private String nome;

    @NotNull(message = "O preco deve ser preenchido.")
    private double preco;

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0)
            throw new IllegalArgumentException();
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty() || !nome.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException();

        this.nome = nome;
    }


}
