package svri.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 * metodo que vai checar, antes de uma acao, a requisicao do usuario e ver se ele esta indo 
	 * para redireciona-lo para a página de login
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object controller) throws IOException {
			String uri = request.getRequestURI();
			
			/**
			 * caminhos que serao acessados sem necessidade de login
			 */
			if(uri.endsWith("login") ||
					uri.endsWith("efetuaLogin") ||
						uri.contains("resources") || 
						uri.endsWith("/SVRIPrivate") ||
						uri.endsWith("cadastro") || 
						uri.endsWith("mostrarAtracoes") ||
						uri.endsWith("cadastrarCliente") ||
						uri.endsWith("notificacoes")) {
				if(uri.endsWith("login") && 
					request.getSession().getAttribute("usuarioLogado") != null) {
					response.sendRedirect("mostrarAtracoes");
					return false;
				}else
					return true;
			}
			
			/**
			 * permite acesso as paginas liberadas caso o usuario ja esteja
			 * logado
			 */
			if(request.getSession().getAttribute("usuarioLogado") != null) {
				return true;
			}
			
			/**
			 * o usuario nao esta logado e esta tentando acessar uma pagina
			 * restrita. logo, sera redirecionado para a pagina de login
			 */
			response.sendRedirect("login");
			return false;
		
	}
}
