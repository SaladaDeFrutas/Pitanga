package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Assento;

import java.util.ArrayList;

public class StringAssento {

    /*Fun��o utilizada para converter uma string do banco de dados (Entidades sala e sessao)
     * em um arrayList de Assento. */
    public ArrayList<Assento> converterStringParaAssento(String string) {
        ArrayList<Assento> assentos = new ArrayList<>();

        String[] paresDePosicoes = string.trim().split(";");
        for (int i = 0; i < paresDePosicoes.length; i++) {
            String[] numeros = paresDePosicoes[i].split(",");
            Assento assento = new Assento();
            assento.setFila(Integer.parseInt(numeros[0]));
            assento.setColuna(Integer.parseInt(numeros[1]));
            assentos.add(assento);
        }
        return assentos;
    }

    public String converterAssentoParaString(ArrayList<Assento> assentos) {
        String string = "";

        for (Assento assento : assentos) {
            string += String.valueOf(assento.getFila());
            string += ",";
            string += String.valueOf(assento.getColuna());
            string += ";";
        }

        return string;
    }

    public ArrayList<Assento> converterArrayStringParaArrayAssento(ArrayList<String> assentosString) {
        ArrayList<Assento> assentos = new ArrayList<>();

        for (int i = 0; i < assentosString.size(); i += 2) {
            Assento umAssento = new Assento();
            umAssento.setColuna(Integer.parseInt(assentosString.get(i)));
            umAssento.setFila(Integer.parseInt(assentosString.get(i + 1)));
            assentos.add(umAssento);
        }
        return assentos;
    }
}
