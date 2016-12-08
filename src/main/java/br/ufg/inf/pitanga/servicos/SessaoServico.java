package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessaoServico {

    @Autowired
    private SessaoRepository sessaoRepository;

    public Sessao buscarPorId(Long id){
        return sessaoRepository.findOne(id);
    }
}
