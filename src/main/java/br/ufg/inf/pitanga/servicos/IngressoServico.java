package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngressoServico {

    @Autowired
    private IngressoRepository ingressoRepository;

    public Ingresso salvaIngresso(Cliente cliente, Sessao sessao, Assento assento, TipoIngresso tipoIngresso) {
        Ingresso ingresso = new Ingresso(cliente, sessao, assento, tipoIngresso);
        return ingressoRepository.save(ingresso);
    }
}
