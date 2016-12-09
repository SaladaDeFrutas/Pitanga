package br.ufg.inf.pitanga.repository;

import br.ufg.inf.pitanga.entidades.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

    Funcionario findByEmail(@Param("email") String email);
}


