package br.ufg.inf.pitanga;

import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PitangaTestHelper {

    public static Calendar converteStringParaCalendar(String data, String formato) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
        Calendar dataCalendar = Calendar.getInstance();
        try {
            dataCalendar.setTime(simpleDateFormat.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataCalendar;
    }

    public static Filme criaFilme() {
        Filme filme = new Filme();
        filme.setModoDeExibicao("3d");
        filme.setProdutora("produtora");
        filme.setTitulo("titulo");
        filme.setIdioma("idioma");
        filme.setDuracao(1);
        filme.setSinopse("sinopse");
        filme.setGenero("genero");
        filme.setClassificacaoIndicativa(10);
        return filme;
    }

    public static Peca criaPeca() {
        Peca filme = new Peca();
        filme.setDiretor("Tarantino");
        filme.setTitulo("titulo");
        filme.setIdioma("idioma");
        filme.setDuracao(1);
        filme.setSinopse("sinopse");
        filme.setGenero("genero");
        filme.setClassificacaoIndicativa(10);
        return filme;
    }


}
