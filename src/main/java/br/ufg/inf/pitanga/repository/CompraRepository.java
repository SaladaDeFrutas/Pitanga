package br.ufg.inf.pitanga.repository;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.entidades.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompraRepository extends CrudRepository<Compra, Long> {

    List<Compra> findByCliente(@Param("cliente") Cliente cliente);
}


