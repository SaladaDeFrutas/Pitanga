package svri.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import svri.entidades.Atracao;
import svri.entidades.Cliente;
import svri.entidades.Filme;
import svri.entidades.Ingresso;
import svri.entidades.Peca;
import svri.entidades.Sessao;
import svri.entidades.TipoIngresso;
import svri.interfaces.dao.InterfaceClienteDao;
import svri.interfaces.dao.InterfaceFilmeDao;
import svri.interfaces.dao.InterfacePecaDao;
import svri.interfaces.dao.InterfaceSessaoDao;
import svri.interfaces.dao.InterfaceTipoIngressoDao;

@Transactional
@Controller
public class SistemaController {

	//problemas relacionados ao banco pode estar no fato de que esta aimport svri.interfaces.dao.InterfaceSessaoDao;

	//deveria estar no construtor
	
	/**
	 * injecao de dependencia pelo Spring
	 */
	@Autowired
	@Qualifier("FilmeDao")
	private InterfaceFilmeDao filmeDao;
	//private final SessaoDao sessaoDao;
	
	@Autowired
	@Qualifier("ClienteDao")
	private InterfaceClienteDao clienteDao;	
	
	@Autowired
	@Qualifier("PecaDao")
	private InterfacePecaDao pecaDao;
	
	@Autowired
	@Qualifier("SessaoDao")
	private InterfaceSessaoDao sessaoDao;
	
	@Autowired
	@Qualifier("TipoIngressoDao")
	private InterfaceTipoIngressoDao tipoIngressoDao;
	
	@RequestMapping("/mostrarFilme")
	public String mostrarFilme(Filme umFilme, Model model){
		//filmeDao.adicionarFilme(umFilme);
		Filme filmeEscolhido = filmeDao.buscarPorId(umFilme.getId());
		//List<Sessao> listaDeSessoes= ses	@AutowiredsaoDao.buscarPorAtracao(umFilme.getId());
		//System.out.println(filmeEscolhido.getTitulo());
		model.addAttribute("filme",filmeEscolhido);
		//model.addAtribute("listaDeSessoes",listaDeSessoes);
		//model.addAttribute("filme",umFilme);
		return "informacoesFilme";
	}
	
	//Ao usar @Valid, nao usar redirect
	@RequestMapping("cadastrarCliente")
	public String cadastrarCliente(@Valid Cliente umCliente, BindingResult result){
		if(result.hasErrors()){
			return "cadastro";
		}
		clienteDao.adicionarCliente(umCliente);
		return "cadastroSucesso";
	}
	
	@RequestMapping("cadastro")
	public String retornaPaginaCadastro(){
		return "cadastro";
	}
	
	/**
	 * 
	 * @param model adiciona atributos para a pagina JSP que sera retornada
	 * @return pagina JSP de atracoes
	 */
	@RequestMapping("mostrarAtracoes")
	public String retornaPaginaAtracoes(Model model){
		List<Filme> filmes = filmeDao.listarFilmes();	
		List<Peca> pecas = pecaDao.listarPecas();
		
		model.addAttribute("filmes",filmes);
		model.addAttribute("pecas", pecas);
		return "mostrarAtracoes";
	}
	/**
	 * 
	 * @param umFilme ID e titulo do filme
	 * @param model adiciona o ID e titulo do filme e a lista de sessoes
	 * @return
	 */
	@RequestMapping("mostrarSessoesFilme")
	public String mostrarSessoesFilme(Filme umFilme, Model model){
		System.out.println("O titulo do filme: " + umFilme.getTitulo());
		List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umFilme);
		System.out.println("A lista de sessoes" + sessoes);
		model.addAttribute("sessoes",sessoes);
		model.addAttribute("filme",umFilme);
		return "mostrarSessoesFilme";
	}
	
	@RequestMapping("mostrarSessoesPeca")
	public String mostrarSessoesPeca(Peca umaPeca, Model model){
		System.out.println("O TITULO DA PECA: " + umaPeca.getTitulo());
		List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umaPeca);
		model.addAttribute("sessoes",sessoes);
		model.addAttribute("peca",umaPeca);
		return "mostrarSessoesPeca";
	}

	/**
	 * 
	 * @param umaSessao recebe o ID da sessao
	 * @param model adiciona o ID da sessao e a 
	 * lista de tipo ingressos na pagina JSP de retorno
	 * @return pagina JSP de escolha de ingressos
	 */
	@RequestMapping("escolherIngressos")
	public String escolherTipoIngresso(Sessao umaSessao, Model model){
		
		System.out.println("O ID da sessao " + umaSessao.getId());
		List<TipoIngresso> tiposIngressos = tipoIngressoDao.listarTipoIngresso();
		model.addAttribute("sessao",umaSessao);
		model.addAttribute("tiposIngressos",tiposIngressos);
		return "escolherIngressos";
	}
	/**
	 * 
	 * @param umaSessao o ID da sessao
	 * @param model
	 * @param quantidadeIngresso Arraylist de Integer contendo 
	 * a quantidade de ingresso escolhida para cada TipoIngresso
	 * @return a pagina JSP que mostra os lugares para serem escolhidos
	 */
	@RequestMapping("lugares")
	public String escolherLugar(Sessao umaSessao, Model model,
			@RequestParam ArrayList<Integer> quantidadeIngresso){
		System.out.println("quantidadeIngresso[0]" + quantidadeIngresso.get(0));
		System.out.println("quantidadeIngresso[1]" + quantidadeIngresso.get(1));
		System.out.println("quantidadeIngresso[2]" + quantidadeIngresso.get(2));
		
		return "mostrarLugares";
	}
	
	/**@RequestMapping("lugaresInteira")
	public String escolherLugarInteira(Sessao umaSessao, Model model,
			TipoIngresso tipoIngresso){
			
	}*/
	
	
	
	/**
	 * 
	 * @return caso a requisicao n encontre pagina retorna uma pagina de 404 
	 * para o usuario
	 */
	@RequestMapping("notFound")
	public String retornarPagina404(){
		return "notFound";
	}


}
