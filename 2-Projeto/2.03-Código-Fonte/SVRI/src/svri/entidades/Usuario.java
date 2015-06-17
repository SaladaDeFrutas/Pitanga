package svri.entidades;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * Usada para fatorar atributos e metodos getters and setters para 
 * funcionarios e clientes
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {
	
	@NotEmpty(message = "O nome deve ser preenchido.")
	private String nome;
	@NotEmpty(message = "O email deve ser preenchido.")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email no formato incorreto.")
	@Id
	private String email;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Past(message= "Por favor, informe a data de nascimento adequadamente no formato"
			+ "dd/MM/yyyy(Ex: 12/02/1994). Sera permitida apenas idade minima de 13 anos e maxima"
			+ "de 130 anos.")
	private Calendar dataDeNascimento;
	
	@Size(min=6, message="A senha deve conter pelo menos 6 caracteres.")
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Calendar getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(Calendar dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}
