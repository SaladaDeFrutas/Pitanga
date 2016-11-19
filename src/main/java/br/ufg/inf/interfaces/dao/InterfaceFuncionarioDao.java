package br.ufg.inf.interfaces.dao;

import br.ufg.inf.entidades.Funcionario;

import java.util.List;


public interface InterfaceFuncionarioDao {

    public void adicionarFuncionario(Funcionario umFuncionario);

    public void removerFuncionario(Funcionario umFuncionario);

    public List<Funcionario> listarFuncionario();

    public void alterarFuncionario(Funcionario umFuncionario);

    public Funcionario buscarPorId(String email);

    public boolean checarFuncionario(Funcionario umFuncionario);

    public boolean checarFuncionarioAdmin(Funcionario umFuncionario);
}

