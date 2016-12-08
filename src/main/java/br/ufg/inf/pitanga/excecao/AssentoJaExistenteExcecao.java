package br.ufg.inf.pitanga.excecao;

public class AssentoJaExistenteExcecao extends RuntimeException {

    public AssentoJaExistenteExcecao(){
        super("Já existe este assento na sala.");
    }
}
