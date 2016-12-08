package br.ufg.inf.pitanga.excecao;

public class SessaoJaExistenteExcecao extends RuntimeException {

    public SessaoJaExistenteExcecao(){
        super("Já existe uma sessao com este id nesta sala.");
    }
}
