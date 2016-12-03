package br.ufg.inf.pitanga.entidades;

import br.ufg.inf.pitanga.entidades.enums.TipoFuncionario;
import java.security.InvalidParameterException;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Usuario {

    protected static final String MENSAGEM_NIVEL_ACESSO_VAZIO = "Um nivel de acesso deve ser atribuido.";
    protected static final String MENSAGEM_MATRICULA_VAZIA = "A matricula deve ser preenchida.";

    @NotNull(message = Funcionario.MENSAGEM_NIVEL_ACESSO_VAZIO)
    private TipoFuncionario nivelAcesso;

    @NotNull(message = MENSAGEM_MATRICULA_VAZIA)
    private int matricula;

    public Funcionario() {
        super();
    }

    public Funcionario(TipoFuncionario nivelAcesso, int matricula) {
        super();
        setNivelAcesso(nivelAcesso);
        setMatricula(matricula);
    }

    public TipoFuncionario getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(TipoFuncionario nivelAcesso) {
        if (nivelAcesso == null) {
            throw new InvalidParameterException(MENSAGEM_NIVEL_ACESSO_VAZIO);
        } else {
            this.nivelAcesso = nivelAcesso;
        }
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

}
