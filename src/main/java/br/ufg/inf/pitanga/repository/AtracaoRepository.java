package br.ufg.inf.pitanga.repository;

import br.ufg.inf.pitanga.entidades.Atracao;
import org.springframework.data.repository.CrudRepository;

public interface AtracaoRepository extends CrudRepository<Atracao, Long> {

    Atracao findById(long id);
}


