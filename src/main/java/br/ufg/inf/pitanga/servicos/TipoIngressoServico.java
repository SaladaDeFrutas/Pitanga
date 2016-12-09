package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.TipoIngresso;
import br.ufg.inf.pitanga.repository.TipoIngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoIngressoServico {

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    public Iterable<TipoIngresso> listarTodosTiposIngresso() {
        return tipoIngressoRepository.findAll();
    }

    public TipoIngresso obtenhaTipoIngressoPorNome(String nomeTipoIngresso) {
        return tipoIngressoRepository.findByNome(nomeTipoIngresso);
    }

}
