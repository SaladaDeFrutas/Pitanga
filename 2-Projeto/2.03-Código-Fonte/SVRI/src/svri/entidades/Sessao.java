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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sessoes")
public class Sessao {

	@Id
	@GeneratedValue
	@Column(name = "idSessao")
	private int id;
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	private Calendar data;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAtracao")
	private Atracao atracao;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSala")
	private Sala sala;
	@Lob
	private String assentosOcupados;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
