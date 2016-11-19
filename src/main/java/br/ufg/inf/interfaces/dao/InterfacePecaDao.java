package br.ufg.inf.interfaces.dao;


import br.ufg.inf.entidades.Peca;

import java.util.List;

public interface InterfacePecaDao {

    public void adicionarPeca(Peca umaPeca);

    public void removerPeca(Peca umaPeca);

    public List<Peca> listarPecas();

    public void alterarPeca(Peca umaPeca);

    public Peca buscarPorId(int id);
}
