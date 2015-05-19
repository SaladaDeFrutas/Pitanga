package svri.entidades;


import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class Assento {
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTipoAssento")
	TipoAssento tipoAssento;
	
	private int fileira;
	private int coluna;
	
	public int getFileira() {
		return fileira;
	}
	public void setFileira(int fileira) {
		this.fileira = fileira;
	}
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
}
