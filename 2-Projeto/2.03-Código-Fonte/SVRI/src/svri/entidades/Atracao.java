package svri.entidades;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Atracao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String titulo;
	private String idioma;
	private int duracao;
	private String sinopse;
	private String genero;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataEstreia;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Calendar getDataEstreia() {
		return dataEstreia;
	}
	public void setDataEstreia(Calendar dataEstreia) {
		this.dataEstreia = dataEstreia;
	}
}
