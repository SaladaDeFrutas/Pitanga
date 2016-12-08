package br.ufg.inf.pitanga.excecao;

public class AssentoForaDoTamanhoDaSalaExcecao extends RuntimeException {

    public AssentoForaDoTamanhoDaSalaExcecao(){
        super("O assento está fora do tamanho da sala");
    }
}
