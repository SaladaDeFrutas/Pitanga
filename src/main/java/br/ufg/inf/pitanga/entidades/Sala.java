package br.ufg.inf.pitanga.entidades;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue
    @Column(name = "idSala")
    private int id;

    @NotNull(message = "A quantidade de fileiras deve ser preenchida.")
    private int qntFileiras;
    @NotNull(message = "A quantidade de colunas deve ser preenchida.")
    private int qntColunas;
    @Lob
    private String assentosInvalidos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQntFileiras() {
        return qntFileiras;
    }

    public void setQntFileiras(int qntFileiras) {
        this.qntFileiras = qntFileiras;
    }

    public int getQntColunas() {
        return qntColunas;
    }

    public void setQntColunas(int qntColunas) {
        this.qntColunas = qntColunas;
    }

    public String getAssentosInvalidos() {
        return assentosInvalidos;
    }

    public void setAssentosInvalidos(String assentosInvalidos) {
        this.assentosInvalidos = assentosInvalidos;
    }


}
