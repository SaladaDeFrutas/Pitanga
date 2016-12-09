package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Atracao;
import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;
import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.repository.AtracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtracaoService {

    @Autowired
    private AtracaoRepository atracaoRepository;

    private static final String ATRACAO = "atracao";

    public List<Filme> listarFilmes() {

    }

    public List<Peca> listarPecas() {
    }

    public Filme buscarFilmePorId(Long id) {
    }

    public List<Sessao> buscarPorAtracao(Atracao atracao) {
    }
}
