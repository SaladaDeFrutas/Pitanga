package svri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import svri.entidades.Funcionario;
import svri.interfaces.dao.InterfaceFuncionarioDao;
import svri.servicos.FuncaoHash;

public class FuncionarioDao implements InterfaceFuncionarioDao{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adicionarFuncionario(Funcionario umFuncionario) {
		umFuncionario.setSenha(new FuncaoHash().gerarHash(umFuncionario.getSenha()));
		manager.persist(umFuncionario);
	}

	@Override
	public void removerFuncionario(Funcionario umFuncionario) {
		Funcionario funcionarioARemover = buscarPorId(umFuncionario.getEmail());
		manager.remove(funcionarioARemover);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> listarFuncionario() {
		return manager.createQuery("from Funcionario").getResultList();
	}

	@Override
	public void alterarFuncionario(Funcionario umFuncionario) {
		manager.merge(umFuncionario);
	}

	@Override
	public Funcionario buscarPorId(String email) {
		return manager.find(Funcionario.class, email);
	}

	@Override
	public boolean checarFuncionario(Funcionario umFuncionario) {
		Funcionario funcionarioBuscado = buscarPorId(umFuncionario.getEmail());
		if(null != funcionarioBuscado){
			if (funcionarioBuscado.getEmail().equals(umFuncionario.getEmail())){
				if(funcionarioBuscado.getSenha().equals(new FuncaoHash().gerarHash((umFuncionario.getSenha())))){
					return true;
				}
			}
			
		}
		
		return false;
	}

	@Override
	public boolean checarFuncionarioAdmin(Funcionario umFuncionario) {
		Funcionario funcionarioBuscado = buscarPorId(umFuncionario.getEmail());
		
		if(null != funcionarioBuscado){
			if (funcionarioBuscado.getEmail().equals(umFuncionario.getEmail()) &&
				funcionarioBuscado.getNivelAcesso() == Funcionario.ADMIN){
				if(funcionarioBuscado.getSenha().equals(new FuncaoHash().gerarHash((umFuncionario.getSenha())))){
					return true;
				}
			}
			
		}
		
		return false;
	}

}
