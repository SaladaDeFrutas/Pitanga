package svri.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "tiposIngresso")
public class TipoIngresso {
	@Id
	@Column(name = "nomeTipoIngresso")
	@NotEmpty(message = "O nome do tipo de ingresso deve ser preenchido.")
	private String nome;
	
	@NotNull(message = "O preco deve ser preenchido.")
	private double preco;
	
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
