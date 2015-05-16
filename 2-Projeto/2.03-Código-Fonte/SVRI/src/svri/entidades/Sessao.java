package svri.entidades;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sessoes")
public class Sessao {

	@Id
	@GeneratedValue
	private int id;
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	private Calendar data;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private Atracao umaAtracao;
	//private Sala umaSala;
	
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
	public Atracao getUmaAtracao() {
		return umaAtracao;
	}
	public void setUmaAtracao(Atracao umaAtracao) {
		this.umaAtracao = umaAtracao;
	}
	
//	public Sala getUmaSala() {
//		return umaSala;
//	}
//	public void setUmaSala(Sala umaSala) {
//		this.umaSala = umaSala;
//	}
	
	
}
