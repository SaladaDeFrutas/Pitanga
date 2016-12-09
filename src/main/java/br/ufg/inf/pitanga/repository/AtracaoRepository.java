package br.ufg.inf.pitanga.repository;

import br.ufg.inf.pitanga.entidades.Atracao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtracaoRepository extends CrudRepository<Atracao, Long> {
}


