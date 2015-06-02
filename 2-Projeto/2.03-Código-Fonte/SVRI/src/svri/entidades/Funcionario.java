package svri.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="funcionarios")
public class Funcionario extends Usuario {
	public static final int ADMIN = 3;
	public static final int GERENTE = 2; 
	private int nivelAcesso;
	private int matricula;
	private String funcao;
	
	public int getNivelAcesso() {
		return nivelAcesso;
	}
	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
}
