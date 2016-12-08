package br.ufg.inf.pitanga.entidades;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "filmes")
public class Filme extends Atracao {

    private Boolean legendado;

    @Column(length = 25)
    @NotEmpty(message = "O modo de exibicao deve ser preenchido.")
    private String modoDeExibicao;

    @Column(length = 60)
    @NotEmpty(message = "A produtora deve ser preenchido.")
    private String produtora;

    public Filme() {
        //Necessário para o JPA instanciar o objeto
    }
    
    public Boolean getLegendado() {
        return legendado;
    }

    public void setLegendado(Boolean legendado) {
        if(legendado == null) {
            throw new IllegalArgumentException(MENSAGEM_ATRIBUTO_INVALIDO);
        } else {
            this.legendado = legendado;
        }
    }

    public String getModoDeExibicao() {
        return modoDeExibicao;
    }

    public void setModoDeExibicao(String modoDeExibicao) {
        if (modoDeExibicao == null || "".equals(modoDeExibicao)){
            throw new IllegalArgumentException(MENSAGEM_ATRIBUTO_INVALIDO);
        } else {
            this.modoDeExibicao = modoDeExibicao;
        }
    }

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        if (produtora == null || "".equals(produtora)){
            throw new IllegalArgumentException(MENSAGEM_ATRIBUTO_INVALIDO);
        } else {
            this.produtora = produtora;
        }
    }
}
