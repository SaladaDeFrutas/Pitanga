package br.ufg.inf.pitanga.repository;

import br.ufg.inf.pitanga.entidades.Atracao;
import br.ufg.inf.pitanga.entidades.Sessao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessaoRepository extends CrudRepository<Sessao, Long> {

    List<Sessao> findByAtracao(@Param("atracao")Atracao atracao);
}


