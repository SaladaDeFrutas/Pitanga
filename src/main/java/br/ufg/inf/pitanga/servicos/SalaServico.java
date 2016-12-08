package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Sala;
import br.ufg.inf.pitanga.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaServico {

    @Autowired
    private SalaRepository salaRepository;

    public Sala buscaSalaPorId(Long idSala) {
        return salaRepository.findOne(idSala);
    }

}
