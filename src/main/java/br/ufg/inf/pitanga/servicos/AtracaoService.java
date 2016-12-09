package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Atracao;
import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;
import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.repository.AtracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class AtracaoService {

    @Autowired
    private AtracaoRepository atracaoRepository;

    private static final String ATRACAO = "atracao";

    public List<Filme> listarFilmes() {
        List<Filme> listaFilmes = (List<Filme>) atracaoRepository.findAll();
        return listaFilmes;
    }

    public List<Peca> listarPecas() {
        List<Peca> listaPecas = (List<Peca>) atracaoRepository.findAll();
        return listaPecas;
    }

    public Filme buscarFilmePorId(Long id) {
        Filme filme = (Filme) atracaoRepository.findById(id);
        if (filme == null) {
            throw new InvalidParameterException(ATRACAO);
        }
        return filme;
    }

    public List<Sessao> buscarPorAtracao(Atracao atracao) {
        List<Sessao> listaSessoes = (List<Sessao>) atracaoRepository.findAll();
    }
}
