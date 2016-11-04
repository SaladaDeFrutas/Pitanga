package svri.interfaces.dao;

import java.util.List;

import svri.entidades.Funcionario;

public interface InterfaceFuncionarioDao {

	public void adicionarFuncionario(Funcionario umFuncionario);
	public void removerFuncionario(Funcionario umFuncionario);
	public List<Funcionario> listarFuncionario();
	public void alterarFuncionario(Funcionario umFuncionario);
	public Funcionario buscarPorId(String email);
	public boolean checarFuncionario(Funcionario umFuncionario);
	public boolean checarFuncionarioAdmin(Funcionario umFuncionario);
}

