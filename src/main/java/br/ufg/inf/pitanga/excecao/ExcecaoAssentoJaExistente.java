package br.ufg.inf.pitanga.excecao;

public class ExcecaoAssentoJaExistente extends RuntimeException {

    public ExcecaoAssentoJaExistente(){
        super("Já existe este assento na sala.");
    }
}
