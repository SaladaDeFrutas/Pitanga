package br.ufg.inf.pitanga.entidades;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Atracao {

    public static String MENSAGEM_ATRIBUTO_INVALIDO = "O valor do atributo não pode ser nulo";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    @Column(length = 100)
    @NotEmpty(message = "O titulo deve ser preenchido.")
    private String titulo;

    @Column(length = 20)
    @NotEmpty(message = "O email deve ser preenchido.")
    private String idioma;

    @NotNull(message = "A duracao deve ser preenchida.")
    private int duracao;

    @NotEmpty(message = "A sinopse deve ser preenchida.")
    @Column(columnDefinition = "TEXT", length = 500)
    private String sinopse;

    @Column(length = 30)
    @NotEmpty(message = "O genero deve ser preenchido.")
    private String genero;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar dataEstreia;

    @NotNull(message = "A classificacao indicativa deve ser preenchida.")
    private int classificacaoIndicativa;

    public Atracao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idAtracao) {
        this.id = idAtracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Calendar getDataEstreia() {
        return dataEstreia;
    }

    public void setDataEstreia(Calendar dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public int getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(int classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

}
