package br.ufg.inf.pitanga.repository;

import br.ufg.inf.pitanga.entidades.TipoIngresso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TipoIngressoRepository extends CrudRepository<TipoIngresso, Long> {

    TipoIngresso findByNome(@Param("nome") String nome);
}


