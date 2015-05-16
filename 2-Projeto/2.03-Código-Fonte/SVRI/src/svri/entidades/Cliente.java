package svri.entidades;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="clientes")
public class Cliente {
	
	@NotEmpty(message = "O nome deve ser preenchido.")
	private String nome;
	@NotEmpty(message = "O email deve ser preenchido.")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email no formato incorreto.")
	@Id
	private String email;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataDeNascimento;
	@Size(min=6, message = "A senha deve conter pelo menos 6 caracteres.")
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
