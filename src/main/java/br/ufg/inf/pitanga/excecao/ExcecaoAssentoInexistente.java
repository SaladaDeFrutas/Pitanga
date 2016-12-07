package br.ufg.inf.pitanga.excecao;

public class ExcecaoAssentoInexistente extends RuntimeException {

    public ExcecaoAssentoInexistente(){
        super("O assento já existe nessa sala");
    }
}
