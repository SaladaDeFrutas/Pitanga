package br.ufg.inf.pitanga.entidades;

import br.ufg.inf.pitanga.entidades.enums.TipoFuncionario;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Usuario {

    @NotNull(message = "Um nivel de acesso deve ser atribuido.")
    private TipoFuncionario nivelAcesso;

    @NotNull(message = "A matricula deve ser preenchida.")
    private int matricula;

    @NotEmpty(message = "A funcao deve ser preenchida.")
    @Column(length = 30)
    private String funcao;

    public Funcionario() {
        super();
    }

    public Funcionario(TipoFuncionario nivelAcesso, int matricula, String funcao) {
        super();
        this.nivelAcesso = nivelAcesso;
        this.matricula = matricula;
        this.funcao = funcao;
    }

    public TipoFuncionario getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(TipoFuncionario nivelAcesso) {
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
