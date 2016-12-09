package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Atracao;
import br.ufg.inf.pitanga.repository.AtracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtracaoServico {

    @Autowired
    AtracaoRepository atracaoRepository;

    public Atracao buscarPorId(Long id) {
        return atracaoRepository.findOne(id);
    }
}
