package svri.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="filmes")
public class Filme extends Atracao{
	
	private Boolean legendado;
	
	@Column(length=25)
	@NotEmpty(message = "O modo de exibicao deve ser preenchido.")
	private String modoDeExibicao;
	
	@Column(length=60)
	@NotEmpty(message = "A produtora deve ser preenchido.")
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
