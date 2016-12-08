package br.ufg.inf.pitanga.controller;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.enums.TransactionStatus;
import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.interfaces.dao.*;
import br.ufg.inf.pitanga.servicos.*;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Transactional
@Controller
public class SistemaController {

    // problemas relacionados ao banco pode estar no fato de que esta aimport
    // svri.interfaces.dao.InterfaceSessaoDao;

    // deveria estar no construtor

    /**
     * injecao de dependencia pelo Spring
     */
    @Autowired
    private InterfaceFilmeDao filmeDao;
    // private final SessaoDao sessaoDao;

    @Autowired
    private InterfaceClienteDao clienteDao;

    @Autowired
    private InterfacePecaDao pecaDao;

    @Autowired
    private InterfaceSessaoDao sessaoDao;

    @Autowired
    private InterfaceTipoIngressoDao tipoIngressoDao;

    @Autowired
    private InterfaceSalaDao salaDao;

    @Autowired
    private InterfaceRegistroCompraDao registroCompraDao;

    @Autowired
    private InterfaceIngressoDao ingressoDao;


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/mostrarFilme")
    public String mostrarFilme(Filme umFilme, Model model) {
        // filmeDao.adicionarFilme(umFilme);
        Filme filmeEscolhido = filmeDao.buscarPorId(umFilme.getId());
        // List<Sessao> listaDeSessoes= ses
        // @AutowiredsaoDao.buscarPorAtracao(umFilme.getId());
        // System.out.println(filmeEscolhido.getTitulo());
        model.addAttribute("filme", filmeEscolhido);
        // model.addAtribute("listaDeSessoes",listaDeSessoes);
        // model.addAttribute("filme",umFilme);
        return "informacoesFilme";
    }

    // Ao usar @Valid, nao usar redirect
    @RequestMapping("cadastrarCliente")
    public String cadastrarCliente(@Valid Cliente umCliente,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro";
        }
        clienteDao.adicionarCliente(umCliente);
        return "cadastroSucesso";
    }

    @RequestMapping("cadastro")
    public String retornaPaginaCadastro() {
        return "cadastro";
    }

    /**
     * @param model adiciona atributos para a pagina JSP que sera retornada
     * @return pagina JSP de atracoes
     */
    @RequestMapping("mostrarAtracoes")
    public String retornaPaginaAtracoes(Model model) {
        List<Filme> filmes = filmeDao.listarFilmes();
        List<Peca> pecas = pecaDao.listarPecas();

        model.addAttribute("filmes", filmes);
        model.addAttribute("pecas", pecas);
        return "mostrarAtracoes";
    }

    /**
     * @param umFilme ID e titulo do filme
     * @param model   adiciona o ID e titulo do filme e a lista de sessoes
     * @return
     */
    @RequestMapping("mostrarSessoesFilme")
    public String mostrarSessoesFilme(Filme umFilme, Model model) {
        List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umFilme);
        model.addAttribute("sessoes", sessoes);
        model.addAttribute("filme", umFilme);
        return "mostrarSessoesFilme";
    }

    @RequestMapping("mostrarSessoesPeca")
    public String mostrarSessoesPeca(Peca umaPeca, Model model) {
        List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umaPeca);
        model.addAttribute("sessoes", sessoes);
        model.addAttribute("peca", umaPeca);
        return "mostrarSessoesPeca";
    }

    /**
     * @param umaSessao recebe o ID da sessao
     * @param model     adiciona o ID da sessao e a lista de tipo ingressos na pagina
     *                  JSP de retorno
     * @return pagina JSP de escolha de ingressos
     */
    @RequestMapping("escolherIngressos")
    public String escolherTipoIngresso(Sessao umaSessao, Model model) {
        List<TipoIngresso> tiposIngressos = tipoIngressoDao
            .listarTipoIngresso();
        model.addAttribute("sessao", umaSessao);
        model.addAttribute("tiposIngressos", tiposIngressos);
        return "escolherIngressos";
    }

    /**
     * @param umaSessao          o ID da sessao
     * @param model
     * @param quantidadeIngresso Arraylist de Integer contendo a quantidade de ingresso
     *                           escolhida para cada TipoIngresso
     * @return a pagina JSP que mostra os lugares para serem escolhidos
     */
    @RequestMapping("lugares")
    public String escolherLugar(Sessao umaSessao, Model model,
                                @RequestParam ArrayList<Integer> quantidadeIngresso,
                                @RequestParam ArrayList<String> nomeTipoIngresso) {

        umaSessao = sessaoDao.buscarPorId(umaSessao.getIdSessao());
        Sala umaSala = salaDao.buscarPorId(umaSessao.getSala().getId());

        int qntIngressos = 0;

        for (Integer umValor : quantidadeIngresso)
            qntIngressos += umValor;

        // Assentos invalidos
        Assento assento1 = new Assento(umaSala);
        Assento assento2 = new Assento(umaSala);
        assento1.setColuna(2);
        assento1.setFila(0);
        assento2.setColuna(18);
        assento2.setFila(3);

        ArrayList<Assento> assentosInvalidos = new ArrayList<>();
        assentosInvalidos.add(assento1);
        assentosInvalidos.add(assento2);

        ArrayList<Assento> assentosOcupados;

        // converte se houver assentos ocupados na sessao
        String assentosOcupadosTexto = umaSessao.getAssentosOcupados();
        if (assentosOcupadosTexto.contains(";")) {
            // Funcao para transformar string da sala e da sessao em arraylist
            // de assentos
            assentosOcupados = new StringAssento()
                .converterStringParaAssento(umaSessao.getAssentosOcupados());
        } else
            assentosOcupados = new ArrayList<Assento>();

        model.addAttribute("sala", umaSala);
        model.addAttribute("assentosInvalidos", assentosInvalidos);
        model.addAttribute("assentosOcupados", assentosOcupados);
        model.addAttribute("qntIngressos", qntIngressos);
        model.addAttribute("quantidadeIngresso", quantidadeIngresso);
        model.addAttribute("nomeTipoIngresso", nomeTipoIngresso);
        model.addAttribute("umaSessao", umaSessao);
        return "mostrarLugares";
    }

    /**
     * @param assentos           assentos escolhidos
     * @param quantidadeIngresso quantidade de ingressos
     * @param nomeTipoIngresso   tipos dos ingressos escolhidos
     * @param umaSessao          sessao do filme/peca escolhida
     * @param sessaoUsuario      dados de sessao do usuario no sistema
     * @return pagina para realizar a compra
     */
    @RequestMapping(value = "finalizarCompra")
    public ModelAndView finalizaCompra(
        @RequestParam ArrayList<String> assentos,
        @RequestParam String quantidadeIngresso,
        @RequestParam String nomeTipoIngresso, Sessao umaSessao,
        HttpSession sessaoUsuario) {

        umaSessao = sessaoDao.buscarPorId(umaSessao.getIdSessao());

        String[] quantidadeIngressoArray = quantidadeIngresso.replace("[", "")
            .replace("]", "").replace(" ", "").split(",");

        String[] nomeTipoIngressosArray = nomeTipoIngresso.replace("[", "")
            .replace("]", "").replace(" ", "").split(",");
        ArrayList<Integer> quantidadeIngressos = new ArrayList<>();
        ArrayList<String> nomeTipoIngressos = new ArrayList<>();

        for (int i = 0; i < quantidadeIngressoArray.length; i++) {
            quantidadeIngressos.add(Integer
                .parseInt(quantidadeIngressoArray[i]));
            nomeTipoIngressos.add(nomeTipoIngressosArray[i]);
        }

        for (int i = 0; i < quantidadeIngressos.size(); i++) {
            int quantidade = quantidadeIngressos.get(i);
            if (quantidade == 0) {
                quantidadeIngressos.remove(i);
                nomeTipoIngressos.remove(i);
            }
        }

        StringAssento stringAssento = new StringAssento();
        ArrayList<Assento> assentosEscolhidos = stringAssento
            .converterArrayStringParaArrayAssento(assentos);
        umaSessao.setAssentosOcupados(umaSessao.getAssentosOcupados()
            + stringAssento.converterAssentoParaString(assentosEscolhidos));

        Cliente umCliente = (Cliente) sessaoUsuario
            .getAttribute("usuarioLogado");
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
        for (int i = 0; i < quantidadeIngressos.size(); i++) {
            int quantidade = quantidadeIngressos.get(i);
            TipoIngresso umTipoIngresso = tipoIngressoDao
                .buscarPorNome(nomeTipoIngressos.get(i));
            for (int j = 0; j < quantidade; j++) {
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
        novoRegistroCompra = novaCompra.calcularTotal(ingressos,
            novoRegistroCompra);
        registroCompraDao.alterarRegistroCompra(novoRegistroCompra);

        return new ModelAndView("redirect:"
            + novaCompra.efetuarPagamento(ingressos, novoRegistroCompra,
            umCliente));

    }

    /**
     * @return caso a requisicao n encontre pagina retorna uma pagina de 404
     * para o usuario
     */
    @RequestMapping("notFound")
    public String retornarPagina404() {
        return "notFound";
    }

    /**
     * funcao chamada pelo pagseguro apos realizacao da compra
     *
     * @param codigoTransacao
     * @return pagina de agradecimento apos a efetuacao da compra
     */
    @RequestMapping("obrigado")
    public String retornarPaginaObrigado(@RequestParam String codigoTransacao) {
        // busca os dados da transacao pelo codigo
        Transaction transacaoCompra = new PagamentoPagseguro()
            .consultarTransacao(codigoTransacao);

        // busca no BD o registro compra pelo id
        // reference eh o idRegistroCompra do RegistroCompra relacionado
        RegistroCompra registroCompra = registroCompraDao.buscarPorId(Integer
            .parseInt(transacaoCompra.getReference()));

        // coloca o codigo da transacaono objeto registro compra
        registroCompra.setCodigoTransacao(codigoTransacao);

        // atualiza no BD o registro da compra
        registroCompraDao.alterarRegistroCompra(registroCompra);

        return "obrigado";
    }

    /**
     * metodo de entrada chamado pela API do pagseguro para notificar mudancas
     * no status das transacoes dos clientes
     *
     * @param notificationCode codigo da notificacao do pagseguro
     * @param notificationType tipo da notificacao
     */
    @RequestMapping("notificacoes")
    public void tratarNotificacaoPagseguro(String notificationCode,
                                           String notificationType) {
        System.out.println(notificationCode);

        // pegar a notificacao completa via post e tratar para retirar o codigo
        // da notificacao
        // e passar para o receberNotificacaoCheckout
        /**
         * O padrao da notificacao enviada pelo pagseguro para a nossa aplicacao
         * eh o seguinte:
         *
         * POST http://lojamodelo.com.br/notificacao HTTP/1.1
         * Host:pagseguro.uol.com.br Content-Length:85
         * Content-Type:application/x-www-form-urlencoded
         * notificationCode=766B9C-AD4B044B04DA-77742F5FA653-E1AB24
         * notificationType=transaction
         */

        NotificacaoPagseguro novaNotificacao = new NotificacaoPagseguro();
        Transaction respostaConsultaNotificacaoCheckout = novaNotificacao
            .receberNotificacaoCheckout(notificationCode);

        RegistroCompra registroCompra = registroCompraDao.buscarPorId(Integer
            .parseInt(respostaConsultaNotificacaoCheckout.getReference()));

        // colocando o codigo da transacao no registro decompra
        registroCompra.setCodigoTransacao(respostaConsultaNotificacaoCheckout
            .getCode());

        // pegando o status da transacao
        TransactionStatus statusTransacao = respostaConsultaNotificacaoCheckout
            .getStatus();

        boolean pagamentoAprovado = new PagamentoPagseguro()
            .traduzirStatusTransacaoPagseguro(statusTransacao.getValue()
                .intValue());

        // colocando no registro de compra se o pagamento foi aprovado
        registroCompra.setPagamentoAprovado(pagamentoAprovado);

        registroCompraDao.alterarRegistroCompra(registroCompra);
        // usar o respostaConsultaNotificacaoCheckout para atualizar
        // corretamente o registrocompra

    }

    /**
     * chamado quando o cliente consulta as compras realizadas por ele
     *
     * @param sessaoUsuario dados da sessao corrente do usuario
     * @param model         adiciona os registros das compras daquele cliente e os dados
     *                      dele na pagina de retorno
     * @return pagina JSP com a lista das compras do cliente
     */
    @RequestMapping("minhasCompras")
    public String retornarCompras(HttpSession sessaoUsuario, Model model) {
        Cliente cliente = (Cliente) sessaoUsuario.getAttribute("usuarioLogado");
        cliente = clienteDao.buscarPorId(cliente.getEmail());
        List<RegistroCompra> registrosCompras = registroCompraDao
            .buscaPorCliente(cliente);

        model.addAttribute("registrosCompras", registrosCompras);
        model.addAttribute("cliente", cliente);

        return "mostrarCompras";
    }

    /**
     * @param registroCompra registro da compra do cliente
     * @param model          adiciona na pagina de retorno o registro, os ingressos e a
     *                       transacao do pagseguro
     * @return pagina jsp com as informacoes da compra
     */
    @RequestMapping("mostrarInformacoesCompra")
    public String mostrarInformacoesCompra(RegistroCompra registroCompra,
                                           Model model) {
        registroCompra = registroCompraDao.buscarPorId(registroCompra
            .getIdRegistroCompra());

        Transaction transacaoCompra = new PagamentoPagseguro()
            .consultarTransacao(registroCompra.getCodigoTransacao());

        List<Ingresso> ingressosCompra = ingressoDao
            .buscaPorRegistroCompra(registroCompra);

        model.addAttribute("registroCompra", registroCompra);
        model.addAttribute("transacaoCompra", transacaoCompra);
        model.addAttribute("ingressosCompra", ingressosCompra);

        return "mostrarInformacoesCompra";
    }

    @RequestMapping("gerarComprovante")
    public void gerarComprovantePdf(RegistroCompra registroCompra, HttpServletResponse response) {
        GeraPDF geradorPDF = new GeraPDF();
        registroCompra.getIdRegistroCompra();

        registroCompra = registroCompraDao.buscarPorId(
            registroCompra.getIdRegistroCompra());

        /**
         * itens a colocar no pdf
         */
        // id da compra
        geradorPDF.concatenaStringTexto("ID da Compra: " + registroCompra.getIdRegistroCompra() + "\n");

        // data da compra
        geradorPDF.concatenaStringTexto("Data: " + new SimpleDateFormat("dd/MM/yy HH:mm").format(registroCompra.getDataCompra().getTime()) + "\n");

        String status = "";
        if (!registroCompra.isPagamentoAprovado())
            status = "Não concluído";
        else
            status = "Concluído";
        //status da compra true ou false pro pagamento
        geradorPDF.concatenaStringTexto("Status da compra: " + status + "\n");

        // nome do cliente
        Cliente umCliente = clienteDao.buscarPorId(registroCompra.getUmCliente().getEmail());
        geradorPDF.concatenaStringTexto("Nome do Cliente: " + umCliente.getNome() + "\n");
        geradorPDF.concatenaStringTexto("--------------------------------------------------------------"
            + "----------------------------------------------------\n");
        geradorPDF.concatenaStringTexto("\nIngressos escolhidos:\n");

        //ingressos da compra
        List<Ingresso> ingressosCompra = ingressoDao.buscaPorRegistroCompra(registroCompra);
        for (Ingresso ingresso : ingressosCompra) {
            // tipo do ingresso
            geradorPDF.concatenaStringTexto("Tipo: " + ingresso.getUmTipoIngresso().getNome() + "\n");

            // sessao com data de exibicao
            Sessao umaSessao = sessaoDao.buscarPorId(ingresso.getUmaSessao().getIdSessao());
            geradorPDF.concatenaStringTexto("Data de Exibição: " + new SimpleDateFormat("dd/MM/yy HH:mm").format(
                umaSessao.getData().getTime()) + "\n");

            Long id = umaSessao.getAtracao().getId();
            Filme filme = filmeDao.buscarPorId(id);
            if (filme != null)
                // nome do filme
                geradorPDF.concatenaStringTexto("Filme: " + filme.getTitulo() + "\n");
            else {
                // nome da peca
                Peca peca = pecaDao.buscarPorId(id);
                geradorPDF.concatenaStringTexto("Peça: " + peca.getTitulo() + "\n");
            }

            // id da sala
            geradorPDF.concatenaStringTexto("Sala: " + umaSessao.getIdSessao() + "\n");

            // fileira e coluna da sala pro ingresso
            geradorPDF.concatenaStringTexto("Assento: " + (ingresso.getUmAssento().getFila() + 1) + (ingresso.getUmAssento().getColuna() + 1) + "\n\n");
        }
        //retorna o pdf completo
        try {
            response.getOutputStream().write(geradorPDF.gerarPDFComprovante().toByteArray());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=ingresso" + registroCompra.getIdRegistroCompra() + ".pdf");
            response.flushBuffer();
        } catch (IOException | DocumentException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

    }
}
