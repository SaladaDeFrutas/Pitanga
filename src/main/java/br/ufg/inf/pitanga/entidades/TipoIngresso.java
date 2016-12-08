package br.ufg.inf.pitanga.entidades;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name = "tiposingresso")
public class TipoIngresso {
    @Id
    @Column(name = "nomeTipoIngresso")
    @NotEmpty(message = "O nome do tipo de ingresso deve ser preenchido.")
    private String nome;

    @NotNull(message = "O preco deve ser preenchido.")
    private BigDecimal preco;

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
