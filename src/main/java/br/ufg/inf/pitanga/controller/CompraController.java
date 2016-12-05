package br.ufg.inf.pitanga.controller;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.enums.TransactionStatus;
import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.interfaces.dao.*;
import br.ufg.inf.pitanga.servicos.CompraServico;
import br.ufg.inf.pitanga.servicos.NotificacaoPagseguro;
import br.ufg.inf.pitanga.servicos.PagamentoPagseguroServico;
import br.ufg.inf.pitanga.servicos.StringAssento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CompraController {

    @Autowired
    private InterfaceTipoIngressoDao tipoIngressoDao;

    @Autowired
    private InterfaceSalaDao salaDao;

    @Autowired
    private InterfaceSessaoDao sessaoDao;

    @Autowired
    private InterfaceCompraDao compraDao;

    @Autowired
    private InterfaceIngressoDao ingressoDao;

    @Autowired
    private InterfaceClienteDao clienteDao;

    @Autowired
    private PagamentoPagseguroServico pagamentoPagseguroServico;

    @Autowired
    private CompraServico compraServico;

    /**
     * @param umaSessao recebe o ID da sessao
     * @param model     adiciona o ID da sessao e a lista de tipo ingressos na pagina
     *                  JSP de retorno
     * @return pagina JSP de escolha de ingressos
     */
    @RequestMapping("escolherIngressos")
    public String escolherTipoIngresso(Sessao umaSessao, Model model) {
        //TODO: Remover após refatoração da classe Tipo Ingresso para um Enum
        List<TipoIngresso> tiposIngressos = tipoIngressoDao
            .listarTipoIngresso();
        model.addAttribute("sessao", umaSessao);
        model.addAttribute("tiposIngressos", tiposIngressos);
        return "escolherIngressos";
    }

    /**
     * @param compra registro da compra do cliente
     * @param model  adiciona na pagina de retorno o registro, os ingressos e a
     *               transacao do pagseguro
     * @return pagina jsp com as informacoes da compra
     */
    @RequestMapping("mostrarInformacoesCompra")
    public String mostrarInformacoesCompra(Compra compra,
                                           Model model) {
        compra = compraDao.buscarPorId(compra.getId());

        String codigoTransacao = compra.getCodigoTransacao();
        Transaction transacaoCompra = pagamentoPagseguroServico.obtenhaTransacao(codigoTransacao);

        List<Ingresso> ingressos = ingressoDao.buscaPorRegistroCompra(compra);

        model.addAttribute("registroCompra", compra);
        model.addAttribute("transacaoCompra", transacaoCompra);
        model.addAttribute("ingressosCompra", ingressos);

        return "mostrarInformacoesCompra";
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
        Assento assento1 = new Assento();
        Assento assento2 = new Assento();
        assento1.setColuna(2);
        assento1.setFileira(0);
        assento2.setColuna(18);
        assento2.setFileira(3);

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
        Compra novoCompra = new Compra();
        novoCompra.setDataCompra(dataCompra);
        novoCompra.setPagamentoAprovado(false);
        novoCompra.setCliente(umCliente);
        novoCompra.setValor(BigDecimal.ZERO);
        compraDao.adicionarCompra(novoCompra);

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
            umIngresso.setCompra(novoCompra);
            ingressoDao.adicionarIngresso(umIngresso);
            ingressos.add(umIngresso);

        }
        CompraServico novaCompraServico = new CompraServico();
        novoCompra = novaCompraServico.calcularTotal(ingressos,
            novoCompra);
        compraDao.alterarCompra(novoCompra);

        return new ModelAndView("redirect:"
            + novaCompraServico.efetuarPagamento(ingressos, novoCompra,
            umCliente));

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

        Compra compra = compraDao.buscarPorId(Long
            .parseLong(respostaConsultaNotificacaoCheckout.getReference()));

        // colocando o codigo da transacao no registro decompra
        compra.setCodigoTransacao(respostaConsultaNotificacaoCheckout
            .getCode());

        // pegando o status da transacao
        TransactionStatus statusTransacao = respostaConsultaNotificacaoCheckout
            .getStatus();

        boolean pagamentoAprovado = new PagamentoPagseguroServico()
            .traduzirStatusTransacaoPagseguro(statusTransacao.getValue()
                .intValue());

        // colocando no registro de compra se o pagamento foi aprovado
        compra.setPagamentoAprovado(pagamentoAprovado);

        compraDao.alterarCompra(compra);
        // usar o respostaConsultaNotificacaoCheckout para atualizar
        // corretamente o registrocompra
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
        Transaction transacaoCompra = new PagamentoPagseguroServico()
            .consultarTransacao(codigoTransacao);

        // busca no BD o registro compra pelo id
        // reference eh o idRegistroCompra do Compra relacionado
        Compra compra = compraDao.buscarPorId(Long
            .parseLong(transacaoCompra.getReference()));

        // coloca o codigo da transacaono objeto registro compra
        compra.setCodigoTransacao(codigoTransacao);

        // atualiza no BD o registro da compra
        compraDao.alterarCompra(compra);

        return "obrigado";
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
        List<CompraDTO> comprasDTO = compraServico.obtenhaComprasDoCliente(cliente.getEmail());

        model.addAttribute("compras", comprasDTO);
        return "mostrarCompras";
    }

}
