package br.ufg.inf.pitanga.repository;

import br.ufg.inf.pitanga.entidades.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Cliente findByEmail(String email);
}


