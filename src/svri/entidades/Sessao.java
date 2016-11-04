package svri.entidades;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sessoes")
public class Sessao {

	@Id
	@GeneratedValue
	@Column(name = "idSessao")
	private int idSessao;
	
	@NotNull (message="por favor, digite a data e hora de estreia.")
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	private Calendar data;
	
	@NotNull(message="por favor, selecione a atracao")
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH
	})
	@JoinColumn(name = "idAtracao")
	private Atracao atracao;
	
	@NotNull(message="por favor, selecione a sala")
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH
	})
	@JoinColumn(name = "idSala")
	private Sala sala;
	
	@Lob
	private String assentosOcupados;
	
	public int getIdSessao() {
		return idSessao;
	}
	
	public void setIdSessao(int idSessao) {
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
	
	
	
}
