package svri.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import svri.dao.ClienteDao;
import svri.dao.FuncionarioDao;
import svri.entidades.Cliente;
import svri.entidades.Funcionario;

@Controller
public class LoginController {


	@Autowired
	@Qualifier("ClienteDao")
	private ClienteDao clienteDao;

	@Autowired
	@Qualifier("FuncionarioDao")
	private FuncionarioDao funcionarioDao;
	
	/**
	 * 
	 * @return a pagina de login do sistema
	 */
	@RequestMapping("login")
	public String retornaPaginaLogin(){
		return "login";
	}
	
	/**
	 * checa se o usuario esta cadastrado e redireciona ele
	 * @param cliente dados do cliente, email e senha
	 * @param session variavel de sessao para salvar o atributo
	 * de logado na sessao corrente do cliente
	 * @return pagina de login novamente caso senha esteja incorreta
	 * ou nao esteja cadastrado
	 */
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Cliente cliente, HttpSession session){
		if(clienteDao.checarCliente(cliente)){
			session.setAttribute("usuarioLogado", cliente);
			return "redirect:mostrarAtracoes";
		}
		else
			return "redirect:login";
	}
	
	/**
	 * 
	 * @param funcionario dados do funcionario com email e senha
	 * @param session variavel de sessao para salvar o atributo de funcionario
	 * logado na sessao corrente
	 * 
	 * @return pagina inicial do funcionario para realizar as funcoes restritas
	 * ou a pagina de login para funcionarios para ele tentar logar novamente
	 */
	@RequestMapping("efetuaLoginFuncionarios")
	public String efetuaLoginFuncionarios(Funcionario funcionario, HttpSession session){
		if(funcionarioDao.checarFuncionario(funcionario)){
			session.setAttribute("funcionarioLogado", funcionario);
			return "indexFuncionarios";
		}
		else
			//System.out.println("entrou!!!");
			return "redirect:loginFuncionarios";
	}
	

	/**
	 * 
	 * @return a pagina de login para funcionarios do sistema
	 */
	@RequestMapping("loginFuncionarios")
	public String retornaPaginaLoginFuncionarios(){
		return "loginFuncionarios";
	}

	@RequestMapping("logout")
 	public String logout(HttpSession session) {
 		session.invalidate();
 		return "redirect:login";
 	}
}
