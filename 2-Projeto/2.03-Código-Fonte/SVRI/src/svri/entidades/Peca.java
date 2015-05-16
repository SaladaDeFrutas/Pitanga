package svri.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pecas")
public class Peca extends Atracao{
	
	private String diretor;

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	
}
