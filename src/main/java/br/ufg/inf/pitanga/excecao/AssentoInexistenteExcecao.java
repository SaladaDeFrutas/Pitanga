package br.ufg.inf.pitanga.excecao;

public class AssentoInexistenteExcecao extends RuntimeException {

    public AssentoInexistenteExcecao(){
        super("O assento já existe nessa sala");
    }
}
