package svri.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import svri.entidades.Assento;
import svri.entidades.Cliente;
import svri.entidades.Filme;
import svri.entidades.Ingresso;
import svri.entidades.Peca;
import svri.entidades.RegistroCompra;
import svri.entidades.Sala;
import svri.entidades.Sessao;
import svri.entidades.TipoIngresso;
import svri.interfaces.dao.InterfaceClienteDao;
import svri.interfaces.dao.InterfaceFilmeDao;
import svri.interfaces.dao.InterfaceIngressoDao;
import svri.interfaces.dao.InterfacePecaDao;
import svri.interfaces.dao.InterfaceRegistroCompraDao;
import svri.interfaces.dao.InterfaceSalaDao;
import svri.interfaces.dao.InterfaceSessaoDao;
import svri.interfaces.dao.InterfaceTipoIngressoDao;
import svri.servicos.Compra;
import svri.servicos.Notificacao;
import svri.servicos.StringAssento;

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
	
	@Autowired
	@Qualifier("SalaDao")
	private InterfaceSalaDao salaDao;
	
	@Autowired
	@Qualifier("RegistroCompraDao")
	private InterfaceRegistroCompraDao registroCompraDao;
	
	@Autowired
	@Qualifier("IngressoDao")
	private InterfaceIngressoDao ingressoDao;
	
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
		List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umFilme);
		model.addAttribute("sessoes",sessoes);
		model.addAttribute("filme",umFilme);
		return "mostrarSessoesFilme";
	}
	
	@RequestMapping("mostrarSessoesPeca")
	public String mostrarSessoesPeca(Peca umaPeca, Model model){
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
			@RequestParam ArrayList<Integer> quantidadeIngresso,
			@RequestParam ArrayList<String> nomeTipoIngresso){
		
		umaSessao = sessaoDao.buscarPorId(umaSessao.getId());
		Sala umaSala = salaDao.buscarPorId(umaSessao.getSala().getId());
		
		int qntIngressos = 0;
		
		for(Integer umValor : quantidadeIngresso)
			 qntIngressos += umValor; 
		//Assentos invalidos
		Assento assento1 = new Assento();
		Assento assento2 = new Assento();
		assento1.setColuna(2);
		assento1.setFileira(0);
		assento2.setColuna(18);
		assento2.setFileira(3);
		
		ArrayList<Assento> assentosInvalidos = new ArrayList<>();
		assentosInvalidos.add(assento1);
		assentosInvalidos.add(assento2);
		
		//Assentos ocupados
		Assento assento3 = new Assento();
		Assento assento4 = new Assento();
		assento3.setColuna(0);
		assento3.setFileira(0);
		assento4.setColuna(12);
		assento4.setFileira(5);
				
		ArrayList<Assento> assentosOcupados = new ArrayList<>();
		assentosOcupados.add(assento3);
		assentosOcupados.add(assento4);
		
		//Fun��o para transformar string da sala e da sessao em arraylist de assentos
		
		
		model.addAttribute("sala",umaSala);
		model.addAttribute("assentosInvalidos", assentosInvalidos);
		model.addAttribute("assentosOcupados", assentosOcupados);
		model.addAttribute("qntIngressos",qntIngressos);	
		model.addAttribute("quantidadeIngresso",quantidadeIngresso);
		model.addAttribute("nomeTipoIngresso",nomeTipoIngresso);
		model.addAttribute("umaSessao", umaSessao);
		return "mostrarLugares";
	}
	
	@RequestMapping(value = "finalizarCompra")
	public ModelAndView finalizaCompra(@RequestParam ArrayList<String> assentos,
			@RequestParam String quantidadeIngresso,
			@RequestParam String nomeTipoIngresso, Sessao umaSessao, HttpSession sessaoUsuario) {
		
			umaSessao = sessaoDao.buscarPorId(umaSessao.getId());
			
			String[] quantidadeIngressoArray =  quantidadeIngresso.replace("[","").
					replace("]", "").replace(" ","").split(",");
			
			String[] nomeTipoIngressosArray = nomeTipoIngresso.replace("[","").
					replace("]", "").replace(" ","").split(",");
			ArrayList<Integer> quantidadeIngressos = new ArrayList<>();
			ArrayList<String> nomeTipoIngressos = new ArrayList<>();
			
			for(int i = 0; i<quantidadeIngressoArray.length;i++){
				quantidadeIngressos.add(Integer.parseInt(quantidadeIngressoArray[i]));
				nomeTipoIngressos.add(nomeTipoIngressosArray[i]);
			}
			
			for (int i = 0; i< quantidadeIngressos.size(); i++) {
				int quantidade = quantidadeIngressos.get(i);
				if(quantidade == 0){
					quantidadeIngressos.remove(i);
					nomeTipoIngressos.remove(i);
				}
			}
			ArrayList<Assento> assentosEscolhidos = StringAssento.converterArrayStringParaArrayAssento(assentos);
			Cliente umCliente = (Cliente)sessaoUsuario.getAttribute("usuarioLogado");
			umCliente = clienteDao.buscarPorId(umCliente.getEmail());
			Calendar dataCompra = Calendar.getInstance();
			RegistroCompra novoRegistroCompra = new RegistroCompra();
			novoRegistroCompra.setDataCompra(dataCompra);
			novoRegistroCompra.setPagamentoAprovado(false);
			novoRegistroCompra.setUmCliente(umCliente);
			novoRegistroCompra.setValor(0);
			registroCompraDao.adicionarRegistroCompra(novoRegistroCompra);
			
			ArrayList<Ingresso> ingressos = new ArrayList<>();
			ArrayList<TipoIngresso> tiposIngressos = new ArrayList<>();
			for (int i = 0; i<quantidadeIngressos.size();i++) {
				int quantidade = quantidadeIngressos.get(i);
				TipoIngresso umTipoIngresso = tipoIngressoDao.
						buscarPorNome(nomeTipoIngressos.get(i));
				for(int j=0;j<quantidade;j++){
					tiposIngressos.add(umTipoIngresso);
				}
			}
			
			for (int i = 0; i < assentosEscolhidos.size(); i++) {
				Assento umAssento = assentosEscolhidos.get(i);
				
				Ingresso umIngresso = new Ingresso();
				umIngresso.setUmaSessao(umaSessao);
				umIngresso.setUmAssento(umAssento);
				umIngresso.setUmTipoIngresso(tiposIngressos.get(i));
				umIngresso.setUmCliente(umCliente);
				umIngresso.setRegistroCompra(novoRegistroCompra);
				ingressoDao.adicionarIngresso(umIngresso);
				ingressos.add(umIngresso);
				
			}
			Compra novaCompra = new Compra();
			novoRegistroCompra = novaCompra.calcularTotal(ingressos,novoRegistroCompra);
			registroCompraDao.alterarRegistroCompra(novoRegistroCompra);
			
			return new ModelAndView("redirect:"+ novaCompra.efetuarPagamento(ingressos, novoRegistroCompra, umCliente));
			
	}
	
	/**
	 * 
	 * @return caso a requisicao n encontre pagina retorna uma pagina de 404 
	 * para o usuario
	 */
	@RequestMapping("notFound")
	public String retornarPagina404(){
		return "notFound";
	}

	@RequestMapping("obrigado")
	public String retornarPaginaObrigado() {
		return "obrigado";
	}
	
	@RequestMapping("notificacoes")
	public void tratarNotificacao(@RequestParam String notificacao) {
		System.out.println(notificacao);
		
		// pegar a notificacao completa via post e tratar para retirar o codigo da notificacao
		// e passar para o receberNotificacaoCheckout
		/**
		 * O padrao da notificacao enviada pelo pagseguro para a nossa aplicacao eh o seguinte:
		 * 
		 * 	POST http://lojamodelo.com.br/notificacao HTTP/1.1
			Host:pagseguro.uol.com.br
			Content-Length:85
			Content-Type:application/x-www-form-urlencoded
			notificationCode=766B9C-AD4B044B04DA-77742F5FA653-E1AB24
			notificationType=transaction
			
			
		 */
		Notificacao novaNotificacao = new Notificacao();
		String[] respostaConsultaNotificacaoCheckout = novaNotificacao.receberNotificacaoCheckout(notificacao);
	
		// usar o respostaConsultaNotificacaoCheckout para atualizar corretamente o registrocompra
	}
}
