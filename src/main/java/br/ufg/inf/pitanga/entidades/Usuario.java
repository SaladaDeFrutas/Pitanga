package br.ufg.inf.pitanga.entidades;

import java.security.InvalidParameterException;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Calendar;

/**
 * Esta classe define atributos e operações comuns a todos os usuários do sistema.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {

    protected static final String MENSAGEM_NOME_VAZIO = "O nome deve ser preenchido.";
    protected static final String MENSAGEM_EMAIL_VAZIO = "O email deve ser preenchido.";
    protected static final String MENSAGEM_EMAIL_INVALIDO = "O email não tem um formato válidonome deve ser preenchido.";
    protected static final String FORMATO_EMAIL = ".+@.+\\.[a-z]+";
    protected static final String FORMATO_DATA_NASCIMENTO = "dd/MM/yyyy";
    protected static final String MENSAGEM_DATA_NASCIMENTO_FORMATO_INVALIDO = "Por favor, informe a data de nascimento no "
            + "formato correto que é 'dd/MM/yyyy' (Ex: 12/02/1994)";
    protected static final String MENSAGEM_DATA_NASCIMENTO_INVALIDA = "A idade mínima deve ser de 13 anos.";
    protected static final String MENSAGEM_SENHA_VAZIA = "A senha deve conter pelo menos 6 caracteres.";
    protected static final int IDADE_MINIMA = 13;
    protected static final int TAMANHO_MINIMO_SENHA = 6;

    @NotEmpty(message = MENSAGEM_NOME_VAZIO)
    private String nome;
    @NotEmpty(message = MENSAGEM_EMAIL_VAZIO)
    @Pattern(regexp = FORMATO_EMAIL, message = MENSAGEM_EMAIL_INVALIDO)
    @Id
    private String email;

    @DateTimeFormat(pattern = FORMATO_DATA_NASCIMENTO)
    @Past(message = MENSAGEM_DATA_NASCIMENTO_INVALIDA)
    private Calendar dataDeNascimento;

    @Size(min = 6, message = MENSAGEM_SENHA_VAZIA)
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().equals("")) {
            throw new InvalidParameterException(MENSAGEM_NOME_VAZIO);
        } else {
            this.nome = nome.trim();
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().equals("")) {
            throw new InvalidParameterException(MENSAGEM_EMAIL_VAZIO);
        } else if (!email.matches(FORMATO_EMAIL)) {
            throw new InvalidParameterException(MENSAGEM_EMAIL_INVALIDO);
        } else {
            this.email = email.trim();
        }
    }

    public Calendar getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Calendar dataDeNascimento) {
        Calendar dataLimite = Calendar.getInstance();
        dataLimite.add(Calendar.YEAR, -IDADE_MINIMA);
        if (dataDeNascimento == null) {
            throw new InvalidParameterException(MENSAGEM_DATA_NASCIMENTO_INVALIDA);
        } else if (dataDeNascimento.after(dataLimite)) {
            throw new InvalidParameterException(MENSAGEM_DATA_NASCIMENTO_INVALIDA);
        } else {
            this.dataDeNascimento = dataDeNascimento;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.trim().equals("")) {
            throw new InvalidParameterException(MENSAGEM_SENHA_VAZIA);
        } else {
            senha = senha.trim();
            if (senha.length() < TAMANHO_MINIMO_SENHA) {
                throw new InvalidParameterException(MENSAGEM_SENHA_VAZIA);
            } else {
                this.senha = senha.trim();
            }
        }
    }

}
