package br.ufg.inf.interfaces.dao;

import br.ufg.inf.entidades.TipoAssento;

import java.util.List;


public interface InterfaceAssentoDao {

    public void adicionarAssento(TipoAssento umAssento);

    public void removerAssento(TipoAssento umAssento);

    public List<TipoAssento> listarAssentos();

    public void alterarAssento(TipoAssento umAssento);

    public TipoAssento buscarPorId(int Id);
}
