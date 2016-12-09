package br.ufg.inf.pitanga.repository;

import br.ufg.inf.pitanga.entidades.Assento;
import br.ufg.inf.pitanga.entidades.Sala;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AssentoRepository extends CrudRepository<Assento, Long> {

    Assento findByNomeAndSala(@Param("nome") String nomeAssento, @Param("sala") Sala sala);
}


