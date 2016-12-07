package br.ufg.inf.pitanga.excecao;

public class ExcecaoSessaoInexistente extends RuntimeException {

    public ExcecaoSessaoInexistente(){
        super("Já existe uma sessão com esse Id nessa sala");
    }
}
