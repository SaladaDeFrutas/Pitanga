package svri.dao;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



public class Tarefa {

	private Long id;
	@NotNull(message = "A descrição deve ser preenchida.")
	@Size(min=5, message="A descrição deve ter pelo menos 5 caracteres.")
	private String descricao;
	private boolean finalizado;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataFinalizacao;
	private TesteAutor autor;
	
	public TesteAutor getAutor() {
		return autor;
	}
	public void setAutor(TesteAutor autor) {
		this.autor = autor;
	}
	public Calendar getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(Calendar dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
}
