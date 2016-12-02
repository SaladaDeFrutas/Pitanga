package br.ufg.inf.pitanga.entidades.enums;

import br.ufg.inf.pitanga.entidades.Funcionario;

/**
 * Esta classe define os tipos de funcionário, também definidos como nível de acesso, possíveis para a classe
 * {@link Funcionario}.
 */
public enum TipoFuncionario {
    GERENTE(2),
    ADMINISTRADOR(3),
    ATENDENTE(4),
    AUXILIAR(5);

    private final int valorNumerico;

    private TipoFuncionario(int valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public int getValorNumerico() {
        return valorNumerico;
    }
}
