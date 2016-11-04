package svri.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="pecas")
public class Peca extends Atracao{
	@NotEmpty(message = "o nome do diretor deve ser preenchido.")
	private String diretor;

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	
}
