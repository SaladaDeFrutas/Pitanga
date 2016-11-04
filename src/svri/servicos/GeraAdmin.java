package svri.servicos;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import svri.entidades.Cliente;
import svri.entidades.Funcionario;

/**
 * 
 * Classe criada para gerar um registro de admin para desenvolver as 
 * funcionalidades que apenas o admin pode desempenhar
 * 
 * colocamos o admin registrado como cliente tambem
 *
 */
public class GeraAdmin {

	public static void main(String[] args) {
	
		Cliente umCliente = new Cliente();
		Funcionario umFuncionario = new Funcionario();
		
		Calendar dataNasc = Calendar.getInstance();
		dataNasc.set(Calendar.YEAR, 1994);
		dataNasc.set(Calendar.MONTH, Calendar.OCTOBER);
		dataNasc.set(Calendar.DAY_OF_MONTH, 21);
		
		umFuncionario.setDataDeNascimento(dataNasc);
		umFuncionario.setEmail("lucasassis413@svri.com.br");
		umFuncionario.setNome("Lucas de Assis Rosa");
		umFuncionario.setSenha(new FuncaoHash().gerarHash("admin123"));
		
		umFuncionario.setFuncao("Administrador");
		umFuncionario.setMatricula(131562);
		umFuncionario.setNivelAcesso(Funcionario.ADMIN);
		
		umCliente.setDataDeNascimento(dataNasc);
		umCliente.setEmail("lucasassis413@gmail.com");
		umCliente.setNome("Lucas de Assis Rosa");
		umCliente.setSenha(new FuncaoHash().gerarHash("admin123"));
		
		Funcionario outroFuncionario = new Funcionario();
		
		outroFuncionario.setDataDeNascimento(dataNasc);
		outroFuncionario.setEmail("admin@admin.com");
		outroFuncionario.setNome("Administrador");
		outroFuncionario.setSenha(new FuncaoHash().gerarHash("admin123"));
		outroFuncionario.setFuncao("Administrador");
		outroFuncionario.setMatricula(1);
		outroFuncionario.setNivelAcesso(Funcionario.ADMIN);
		
		
		EntityManagerFactory factory = Persistence.
				createEntityManagerFactory("SVRIUnit");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		//manager.persist(umFuncionario);	
		//manager.persist(umCliente);
		manager.persist(outroFuncionario);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();

	}

}
