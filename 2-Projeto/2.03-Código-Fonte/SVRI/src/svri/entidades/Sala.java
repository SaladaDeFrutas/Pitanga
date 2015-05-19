package svri.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name= "salas")
public class Sala {

	@Id
	@GeneratedValue
	@Column(name = "idSala")
	private int id;
	private int qntFileiras;
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
