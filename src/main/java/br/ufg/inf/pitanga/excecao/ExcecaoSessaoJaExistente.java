package br.ufg.inf.pitanga.excecao;

public class ExcecaoSessaoJaExistente extends RuntimeException {

    public ExcecaoSessaoJaExistente(){
        super("Já existe uma sessao com este id nesta sala.");
    }
}
