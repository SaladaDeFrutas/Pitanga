package svri.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import svri.entidades.Assento;
import svri.entidades.Cliente;
import svri.entidades.Filme;
import svri.entidades.Funcionario;
import svri.entidades.Peca;
import svri.entidades.Sala;
import svri.entidades.Sessao;
import svri.entidades.TipoIngresso;
import svri.interfaces.dao.InterfaceFuncionarioDao;
import svri.interfaces.dao.InterfaceFilmeDao;
import svri.interfaces.dao.InterfacePecaDao;
import svri.interfaces.dao.InterfaceSalaDao;
import svri.interfaces.dao.InterfaceSessaoDao;
import svri.interfaces.dao.InterfaceTipoIngressoDao;
import svri.servicos.StringAssento;

@Transactional
@Controller
public class FuncionarioController {
	@Autowired
	@Qualifier("FilmeDao")
	private InterfaceFilmeDao filmeDao;
	//private final SessaoDao sessaoDao;
	
	@Autowired
	@Qualifier("FuncionarioDao")
	private InterfaceFuncionarioDao funcionarioDao;	
	
	@Autowired
	@Qualifier("PecaDao")
	private InterfacePecaDao pecaDao;
	
	@Autowired
	@Qualifier("SessaoDao")
	private InterfaceSessaoDao sessaoDao;
	
	@Autowired
	@Qualifier("TipoIngressoDao")
	private InterfaceTipoIngressoDao tipoIngressoDao;
	
	@Autowired
	@Qualifier("SalaDao")
	private InterfaceSalaDao salaDao;
	
	
	@RequestMapping("indexFuncionarios")
	public String retornaPaginaIndex(){
		return "indexFuncionarios";
	}
	
	@RequestMapping("cadastrarFilmeFuncionarios")
	public String cadastrarFilme(@Valid Filme umFilme, BindingResult result){
		if(result.hasErrors()){
			return "cadastroFilme";
		}
		filmeDao.adicionarFilme(umFilme);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("cadastrarPecaFuncionarios")
	public String cadastrarPeca(@Valid Peca umaPeca, BindingResult result){
		if(result.hasErrors()){
			return "cadastroPeca";
		}
		pecaDao.adicionarPeca(umaPeca);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("gerenciarAtracoesFuncionarios")
	public String retornaPaginaGerenciaAtracoes(){
		return "gerenciarAtracoes";
	}
	
	@RequestMapping("gerenciarSessoesFuncionarios")
	public String retornaPaginaGerenciaSessoes(){
		return "gerenciarSessoes";
	}
	
	@RequestMapping("gerenciarSalasFuncionarios")
	public String retornaPaginaGerenciaSalas(){
		return "gerenciarSalas";
	}
	
	
	@RequestMapping("cadastroAtracoesFuncionarios")
	public String retornaPaginaCadastroAtracoes(){
		return "cadastroAtracoes";
	}
	
	@RequestMapping("cadastroFilmeFuncionarios")
	public String retornaPaginaCadastroFilme(){
		return "cadastroFilme";
	}
	
	@RequestMapping("cadastroPecaFuncionarios")
	public String retornaPaginaCadastroPeca(){
		return "cadastroPeca";
	}
	
	@RequestMapping("cadastroSessoesFuncionarios")
	public String retornaPaginaCadastroSessoes(){
		return "cadastroSessoes";
	}
	
	@RequestMapping("cadastroSalasFuncionarios")
	public String retornaPaginaCadastroSalas(){
		return "cadastroSalas";
	}
	
	@RequestMapping("gerenciarTipoIngressoFuncionarios")
	public String retornaPaginaGerenciaTipoIngresso(){
		return "gerenciarTipoIngresso";
	}
	
	@RequestMapping("cadastroTipoIngressoFuncionarios")
	public String retornaPaginaCadastroTipoIngresso(){
		return "cadastroTipoIngresso";
	}
	
	/**
	 * 
	 * @param model adiciona atributos para a pagina JSP que sera retornada
	 * @return pagina JSP de atracoes com botoes de alterar e excluir
	 */
	@RequestMapping("mostrarAtracoesFuncionarios")
	public String retornaPaginaAtracoes(Model model){
		List<Filme> filmes = filmeDao.listarFilmes();	
		List<Peca> pecas = pecaDao.listarPecas();
		
		model.addAttribute("filmes",filmes);
		model.addAttribute("pecas", pecas);
		return "mostrarAtracoesFuncionarios";
	}
	
	/**
	 * 
	 * @param model adiciona atributos para a pagina JSP que sera retornada
	 * @return pagina JSP de atracoes para serem selecionadas e mostradas
	 * suas respectivas sessoes
	 */
	@RequestMapping("mostrarSessoesFuncionarios")
	public String retornaPaginaAtracoesSessoes(Model model){
		List<Filme> filmes = filmeDao.listarFilmes();	
		List<Peca> pecas = pecaDao.listarPecas();
		
		model.addAttribute("filmes",filmes);
		model.addAttribute("pecas", pecas);
		return "atracoesSessoesFuncionarios";
	}
	
	@RequestMapping("cadastroSessoesFilmeFuncionarios")
	public String retornaPaginaCadastroSessaoFilme(Model model){
		List<Filme> filmes = filmeDao.listarFilmes();	
		List<Sala> salas = salaDao.listarSalas();
		
		model.addAttribute("filmes",filmes);
		model.addAttribute("salas", salas);
		return "cadastroSessaoFilme";
	}
	
	@RequestMapping("cadastroSessoesPecaFuncionarios")
	public String retornaPaginaCadastroSessaoPeca(Model model){
		List<Peca> pecas = pecaDao.listarPecas();
		List<Sala> salas = salaDao.listarSalas();
		
		model.addAttribute("pecas", pecas);
		model.addAttribute("salas", salas);
		return "cadastroSessaoPeca";
	}
	
	@RequestMapping("sessoesFilmeFuncionarios")
	public String mostrarSessoesFilme(Filme umFilme, Model model){
		List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umFilme);
		model.addAttribute("sessoes",sessoes);
		umFilme = filmeDao.buscarPorId(umFilme.getIdAtracao());
		model.addAttribute("filme",umFilme);
		return "mostrarSessoesFilmeFuncionarios";
	}
	
	@RequestMapping("sessoesPecaFuncionarios")
	public String mostrarSessoesPeca(Peca umaPeca, Model model){
		List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umaPeca);
		model.addAttribute("sessoes",sessoes);
		umaPeca = pecaDao.buscarPorId(umaPeca.getIdAtracao());
		model.addAttribute("peca",umaPeca);
		return "mostrarSessoesPecaFuncionarios";
	}
	
	@RequestMapping("alteracaoFilmeFuncionarios")
	public String alterarDadosFilme(Filme umFilme, Model model){
		Filme filme = filmeDao.buscarPorId(umFilme.getIdAtracao());
		model.addAttribute("filme",filme);
		return "alteracaoFilme";
	}
	
	@RequestMapping("alterarFilmeFuncionarios")
	public String alterarFilme(@Valid Filme umFilme, BindingResult result){
		if(result.hasErrors()){
			return "alteracaoFilme";
		}
		//System.out.println(umFilme.getId());
		filmeDao.alterarFilme(umFilme);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("exclusaoFilmeFuncionarios")
	public String excluirDadosFilme(Filme umFilme){
		filmeDao.removerFilme(umFilme);
		return "redirect:mostrarAtracoesFuncionarios";
	}
	
	@RequestMapping("alteracaoPecaFuncionarios")
	public String alterarDadosPeca(Peca umaPeca, Model model){
		Peca peca = pecaDao.buscarPorId(umaPeca.getIdAtracao());
		model.addAttribute("peca",peca);
		return "alteracaoPeca";
	}
	
	@RequestMapping("alteracaoSessaoFilmeFuncionarios")
	public String alterarDadosSessaoFilme(Sessao sessao, Model model) {
		sessao = sessaoDao.buscarPorId(sessao.getIdSessao());
		
		List<Filme> filmes = filmeDao.listarFilmes();
		List<Sala> salas = salaDao.listarSalas();
		
		model.addAttribute("filmes", filmes);
		model.addAttribute("salas", salas);
		model.addAttribute("sessao", sessao);
		
		return "alteracaoSessaoFilme";
	}
	
	@RequestMapping("alteracaoSessaoPecaFuncionarios")
	public String alterarDadosSessaoPeca(Sessao sessao, Model model) {
		sessao = sessaoDao.buscarPorId(sessao.getIdSessao());
		
		List<Peca> pecas = pecaDao.listarPecas();
		List<Sala> salas = salaDao.listarSalas();
		
		model.addAttribute("pecas", pecas);
		model.addAttribute("salas", salas);
		model.addAttribute("sessao", sessao);
		
		return "alteracaoSessaoPeca";
	}
	
	
	@RequestMapping("alteracaoTipoIngressoFuncionarios")
	public String alterarDadosPeca(TipoIngresso umTipoIngresso, Model model){
		umTipoIngresso = tipoIngressoDao.buscarPorNome(umTipoIngresso.getNome());
		model.addAttribute("tipoIngresso",umTipoIngresso);
		return "alteracaoTipoIngresso";
	}
	@RequestMapping("cadastrarSessaoPecaFuncionarios")
	public String cadastrarSessaoPeca(@Valid Sessao sessao, 
			BindingResult result,Peca peca){
		//criado para adicionar um objeto sem id
		Sessao umaSessao = new Sessao();
		
		umaSessao.setSala(
				salaDao.buscarPorId(sessao.getSala().getId()));
		umaSessao.setAtracao(
				pecaDao.buscarPorId(peca.getIdAtracao()));	
		umaSessao.setData(sessao.getData());
		
		umaSessao.setAssentosOcupados(" ");
		
		if(result.hasFieldErrors("data")){
			return "redirect:cadastroSessoesPecaFuncionarios";
		}
		sessaoDao.adicionarSessao(umaSessao);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("cadastrarSessaoFilmeFuncionarios")
	public String cadastrarSessaoFilme(@Valid Sessao sessao, 
			BindingResult result,Filme filme){
		//criado para adicionar um objeto sem id
		Sessao umaSessao = new Sessao();
		
		umaSessao.setSala(
				salaDao.buscarPorId(sessao.getSala().getId()));
		umaSessao.setAtracao(
				filmeDao.buscarPorId(filme.getIdAtracao()));	
		umaSessao.setData(sessao.getData());
		umaSessao.setAssentosOcupados(" ");
		
		if(result.hasFieldErrors("data")){
			return "redirect:cadastroSessoesFilmeFuncionarios";
		}
		sessaoDao.adicionarSessao(umaSessao);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("alterarPecaFuncionarios")
	public String alterarPeca(@Valid Peca umaPeca, BindingResult result){
		if(result.hasErrors()){
			return "alteracaoPeca";
		}
		pecaDao.alterarPeca(umaPeca);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("alterarSessaoFilmeFuncionarios")
	public String alterarSessaoFilme(@Valid Sessao sessao, BindingResult result,
			Filme filme){
		//criado para adicionar um objeto sem id
		Sessao umaSessao = sessaoDao.buscarPorId(sessao.getIdSessao());
		//System.out.println(filme.getIdAtracao());
		//System.out.println(sessao.getIdSessao());
		umaSessao.setSala(
				salaDao.buscarPorId(sessao.getSala().getId()));
		
		umaSessao.setAtracao(
				filmeDao.buscarPorId(filme.getIdAtracao()));	
		
		umaSessao.setData(sessao.getData());
		
		if(result.hasFieldErrors("data")){
			return "redirect:alteracaoSessaoFilmeFuncionarios";
		}
		sessaoDao.alterarSessao(umaSessao);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("alterarSessaoPecaFuncionarios")
	public String alterarSessaoPeca(@Valid Sessao sessao, BindingResult result,
			Peca peca){
		//criado para adicionar um objeto sem id
		Sessao umaSessao = sessaoDao.buscarPorId(sessao.getIdSessao());
		//System.out.println(peca.getIdAtracao());
		umaSessao.setSala(
				salaDao.buscarPorId(sessao.getSala().getId()));
		
		umaSessao.setAtracao(
				pecaDao.buscarPorId(peca.getIdAtracao()));	
		
		umaSessao.setData(sessao.getData());
		
		if(result.hasFieldErrors("data")){
			return "redirect:alteracaoSessaoPecaFuncionarios";
		}
		sessaoDao.alterarSessao(umaSessao);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("alterarTipoIngressoFuncionarios")
	public String alterarTipoIngresso(@Valid TipoIngresso umTipoIngresso, BindingResult result){
		if(result.hasErrors()){
			return "alteracaoTipoIngresso";
		}
		tipoIngressoDao.alterarTipoIngresso(umTipoIngresso);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("exclusaoSessaoFuncionarios")
	public String excluirDadosSessao(Sessao sessao){
		//System.out.println(sessao.getIdSessao());
		sessaoDao.removerSessao(sessao);
		return "redirect:gerenciarSessoesFuncionarios";
	}
	
	@RequestMapping("exclusaoTipoIngressoFuncionarios")
	public String excluirDadosTipoIngresso(TipoIngresso umTipoIngresso){
		tipoIngressoDao.removerTipoIngresso(umTipoIngresso);
		return "redirect:mostrarTipoIngressoFuncionarios";
	}
	
	@RequestMapping("exclusaoPecaFuncionarios")
	public String excluirDadosPeca(Peca umaPeca){
		pecaDao.removerPeca(umaPeca);
		return "redirect:mostrarAtracoesFuncionarios";
	}
	
	@RequestMapping("mostrarTipoIngressoFuncionarios")
	public String retornaPaginaTipoIngresso(Model model){
			List<TipoIngresso> tiposIngresso = tipoIngressoDao.listarTipoIngresso();	
			model.addAttribute("tiposIngresso",tiposIngresso);
			return "mostrarTipoIngressoFuncionarios";
	}
	
	@RequestMapping("cadastrarTipoIngressoFuncionarios")
	public String cadastrarTipoIngresso(@Valid TipoIngresso umTipoIngresso, BindingResult result, HttpServletRequest request){
		if(result.hasErrors()){
			return "cadastroTipoIngresso";
		}
		tipoIngressoDao.adicionarTipoIngresso(umTipoIngresso);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("dimensoesSalaFuncionarios")
	public String cadastroSala(@Valid Sala sala, Model model, BindingResult result){	
		if(result.hasErrors()){
			return "cadastroSalas";
		}
		model.addAttribute("sala", sala);
		return "formatarSala";
	}
	
	@RequestMapping("cadastrarSalaFuncionarios")
	public String cadastrarSalaFuncionarios(Sala sala, 
			@RequestParam ArrayList<String> assentos) {
		String assentosInvalidos;
		
		if(assentos != null){
			System.out.println("array assentos:"+assentos.toString());
			StringAssento stringAssento = new StringAssento();
			ArrayList<Assento> arrayAssentosInvalidos = stringAssento.converterArrayStringParaArrayAssento(assentos);
			assentosInvalidos = stringAssento.converterAssentoParaString(arrayAssentosInvalidos);
		}
		else
			assentosInvalidos = " ";
		
		System.out.println("String assentos invalidos:"+assentosInvalidos);
		sala.setAssentosInvalidos(assentosInvalidos);
		salaDao.adicionarSala(sala);
		return "cadastroRestritoSucesso";	
	}
	
	@RequestMapping("mostrarSalasFuncionarios")
	public String retornaPaginaSalas(Model model){
			List<Sala> salas = salaDao.listarSalas();	
			model.addAttribute("salas",salas);
			return "mostrarSalasFuncionarios";
	}
	
	@RequestMapping("alteracaoSalaFuncionarios")
	public String alterarDadosSala(Sala sala, Model model){
		sala = salaDao.buscarPorId(sala.getId());
		StringAssento stringAssento = new StringAssento();
		ArrayList<Assento> assentosInvalidos = stringAssento.converterStringParaAssento(
				sala.getAssentosInvalidos());
		
		model.addAttribute("sala",sala);
		model.addAttribute("assentosInvalidos", assentosInvalidos);
		
		return "alteracaoSala";
	}
	
	@RequestMapping("alterarSalaFuncionarios")
	public String alterarSala(@Valid Sala sala, BindingResult result, 
			@RequestParam ArrayList<String> assentos){
		if(result.hasErrors()){
			return "alteracaoSala";
		}
		//System.out.println("ID da sala:"+sala.getId());
		String assentosInvalidos;
		if(assentos != null){
			StringAssento stringAssento = new StringAssento();
			ArrayList<Assento> arrayAssentosInvalidos = stringAssento.converterArrayStringParaArrayAssento(assentos);
			assentosInvalidos = stringAssento.converterAssentoParaString(arrayAssentosInvalidos);
		}
		else
			assentosInvalidos = " ";
		

		sala.setAssentosInvalidos(assentosInvalidos);
		
		salaDao.alterarSala(sala);
		return "cadastroRestritoSucesso";
	}
	
	@RequestMapping("exclusaoSalaFuncionarios")
	public String excluirSala(Sala sala){
		salaDao.removerSala(sala);
		return "redirect:mostrarSalasFuncionarios";
	}
	
	// Ao usar @Valid, nao usar redirect
	@RequestMapping("cadastrarFuncionarioslogin")
	public String cadastrarCliente(@Valid Funcionario umFuncionario,
			BindingResult result) {
		if (result.hasErrors()) {
			return "cadastroFuncionario";
		}
		funcionarioDao.adicionarFuncionario(umFuncionario);
		return "redirect:loginFuncionarios";
	}

	@RequestMapping("useradmFuncionarioslogin")
	public String retornaPaginaCadastro() {
		return "cadastroFuncionario";
	}
	
}
