package svri.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="filmes")
public class Filme extends Atracao{
	
	private Boolean legendado;
	private String modoDeExibicao;
	private String produtora;
	
	public Boolean getLegendado() {
		return legendado;
	}
	public void setLegendado(Boolean legendado) {
		this.legendado = legendado;
	}
	public String getModoDeExibicao() {
		return modoDeExibicao;
	}
	public void setModoDeExibicao(String modoDeExibicao) {
		this.modoDeExibicao = modoDeExibicao;
	}
	public String getProdutora() {
		return produtora;
	}
	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}
	
	
}
