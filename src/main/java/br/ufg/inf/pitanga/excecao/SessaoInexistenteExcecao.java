package br.ufg.inf.pitanga.excecao;

public class SessaoInexistenteExcecao extends RuntimeException {

    public SessaoInexistenteExcecao(){
        super("Já existe uma sessão com esse Id nessa sala");
    }
}
