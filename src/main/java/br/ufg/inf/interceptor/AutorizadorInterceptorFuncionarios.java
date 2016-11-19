package br.ufg.inf.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * Classe para interceptar requisicoes das paginas de acesso
 * a funcionalidades dos funcionarios
 * 
 * Ao se tratar todas as requisicoes pertinentes a parte
 * do funcionario do site, no final se permite
 * passar qualquer outra requisicao desconhecida, para que
 * o outro AutorizadorInterceptor trate 
 *
 */
public class AutorizadorInterceptorFuncionarios extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object controller) throws IOException {
			String uri = request.getRequestURI();
			
			//System.out.println(uri);
			/**
			 * permite o acesso ao caminho de envio dos dados do funcionarios
			 */
			if(uri.endsWith("efetuaLoginFuncionarios"))
				return true;	
			/**
			 * Caso a pagina de login de funcionarios seja acessada por
			 * um usuario funcionario logado, ele sera redirecionado para a index de funcionarios
			 * Caso seja acessada por um usuario cliente, ele podera acessar
			 */
			else if(uri.endsWith("loginFuncionarios")){
				if(request.getSession().getAttribute("funcionarioLogado") != null){
					response.sendRedirect("indexFuncionarios");
					return false;
				}
				else
					return true;
			}
			
			/**
			 * permite o acesso ao caminho de envio dos dados do administrador
			 */
			else if(uri.endsWith("efetuaLoginAdminFuncionarios"))
				return true;	
			/**
			 * Caso a pagina de login de admin seja acessada por
			 * um usuario admin logado, ele sera redirecionado para a gerencia de funcionarios
			 * Caso seja acessada por um usuario funcionario, ele podera acessar
			 */
			else if(uri.endsWith("loginAdminFuncionarios")){
				if(request.getSession().getAttribute("adminLogado") != null){
					response.sendRedirect("gerenciarFuncionarios");
					return false;
				}
				else
					return true;
			}
			
			/**
			 * caso tente acessar a parte restrita sistema
			 * destinada a administradores
			 */
			else if(uri.endsWith("gerenciarFuncionarios") ||
					uri.endsWith("cadastrarFuncionarios") ||
					uri.endsWith("cadastroFuncionarios") ||
					uri.endsWith("mostrarFuncionarios") ||
					uri.endsWith("alteracaoFuncionarios")||
					uri.endsWith("alterarFuncionarios")||
					uri.endsWith("exclusaoFuncionarios")){			
					if(request.getSession().getAttribute("adminLogado") != null)
						return true;
					else{
						response.sendRedirect("loginAdminFuncionarios");
						return false;
					}
			}	
			/**
			 * caso tente acessar a parte restrita sistema
			 * destinada a funcionarios
			 */
			else if(uri.endsWith("Funcionarios")){			
					if(request.getSession().getAttribute("funcionarioLogado") != null)
						return true;
					else{
						response.sendRedirect("loginFuncionarios");
						return false;
					}
			}	
				
			return true;
	}
}
