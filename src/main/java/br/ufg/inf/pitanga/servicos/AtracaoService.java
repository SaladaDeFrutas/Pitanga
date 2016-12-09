package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Atracao;
import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;
import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.repository.AtracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtracaoService {

    @Autowired
    private AtracaoRepository atracaoRepository;

    private static final String ATRACAO = "atracao";

    public List<Filme> listarFilmes() {
        Iterable<Atracao> lista = atracaoRepository.findAll();
        List<Filme> filmes = new ArrayList<>();
        for (Atracao a : lista){
            filmes.add((Filme) a);
        }
        return filmes;
    }

    public List<Peca> listarPecas() {
        Iterable<Atracao> lista = atracaoRepository.findAll();
        List<Peca> pecas = new ArrayList<>();
        for (Atracao a : lista){
            pecas.add((Peca) a);
        }
        return pecas;
    }

    public Filme buscarFilmePorId(Long id) {
        Filme filme = (Filme) atracaoRepository.findById(id);
        if (filme == null) {
            throw new InvalidParameterException(ATRACAO);
        }
        return filme;
    }

    public List<Sessao> buscarPorAtracao(Atracao atracao) {
        if (atracao == null) {
            throw new InvalidParameterException(ATRACAO);
        }
        List<Sessao> listaSessoes = (List<Sessao>) atracaoRepository.findById(atracao.getId());
        return listaSessoes;
    }
}
