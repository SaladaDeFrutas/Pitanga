package br.ufg.inf.pitanga.entidades;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Table(name = "sessoes")
public class Sessao {

    @Id
    @GeneratedValue
    @Column(name = "idSessao")
    private long idSessao;

    @NotNull(message = "por favor, digite a data e hora de estreia.")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Calendar data;

    @NotNull(message = "por favor, selecione a atracao")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "idAtracao")
    private Atracao atracao;

    @NotNull(message = "por favor, selecione a sala")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "idSala")
    private Sala sala;

    @Lob
    @Deprecated
    private String assentosOcupados;

    public long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(long idSessao) {
        this.idSessao = idSessao;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Atracao getAtracao() {
        return atracao;
    }

    public void setAtracao(Atracao umaAtracao) {
        this.atracao = umaAtracao;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala umaSala) {
        this.sala = umaSala;
    }

    public String getAssentosOcupados() {
        return assentosOcupados;
    }

    public void setAssentosOcupados(String assentosOcupados) {
        this.assentosOcupados = assentosOcupados;
    }

    public String obtenhaDescricao() {
        String tituloAtracao = getAtracao().getTitulo();
        String dataSessao = formataData(getData());
        return tituloAtracao + " : " + dataSessao;
    }

    private String formataData(Calendar data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data.getTime());
    }
}
