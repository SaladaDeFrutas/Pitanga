package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.entidades.Ingresso;
import br.ufg.inf.pitanga.repository.CompraRepository;
import br.ufg.inf.pitanga.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngressoServico {

    @Autowired
    private IngressoRepository ingressoRepository;

    public Ingresso buscarPorId(Long id){
        return ingressoRepository.findOne(id);
    }
}
