package br.ufg.inf.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="funcionarios")
public class Funcionario extends Usuario {
	public static final int ADMIN = 3;
	public static final int GERENTE = 2;
	public static final int ATENDENTE = 4;
	public static final int AUXILIAR = 5;
	
	@NotNull(message = "Um nivel de acesso deve ser atribuido.")
	private int nivelAcesso;
	
	@NotNull(message = "A matricula deve ser preenchida.")
	private int matricula;
	
	@NotEmpty(message = "A funcao deve ser preenchida.")
	@Column(length=30)
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
