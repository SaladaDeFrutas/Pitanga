package br.ufg.inf.pitanga.excecao;

public class ExcecaoAssentoForaDoTamanhoDaSala extends RuntimeException {

    public ExcecaoAssentoForaDoTamanhoDaSala(){
        super("O assento está fora do tamanho da sala");
    }
}
