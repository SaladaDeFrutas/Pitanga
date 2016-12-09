package br.ufg.inf.pitanga.controller;

import br.com.uol.pagseguro.domain.Transaction;
import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.servicos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.ufg.inf.pitanga.controller.Paginas.*;

@Controller
public class CompraController {

    @Autowired
    private TipoIngressoServico tipoIngressoServico;

    @Autowired
    private SalaServico salaServico;

    @Autowired
    private SessaoServico sessaoServico;

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
        Iterable<TipoIngresso> tiposIngressos = tipoIngressoServico.listarTodosTiposIngresso();
        model.addAttribute("sessao", umaSessao);
        model.addAttribute("tiposIngressos", tiposIngressos);
        return ESCOLHER_INGRESSO;
    }

    /**
     * @param compra registro da compra do cliente
     * @param model  adiciona na pagina de retorno o registro, os ingressos e a
     *               transacao do pagseguro
     * @return pagina jsp com as informacoes da compra
     */
    @RequestMapping("mostrarInformacoesCompra")
    public String mostrarInformacoesCompra(Compra compra, Model model) {
        Compra compraObtida = compraServico.buscarPorId(compra.getId());
        String codigoTransacao = compra.getCodigoTransacao();
        Transaction transacaoCompra = pagamentoPagseguroServico.obtenhaTransacao(codigoTransacao);

        model.addAttribute("registroCompra", compraObtida);
        model.addAttribute("transacaoCompra", transacaoCompra);
        return INFORMACOES_COMPRA;
    }

    /**
     * @param umaSessao o ID da sessao
     * @param model
     * @return a pagina JSP que mostra os lugares para serem escolhidos
     */
    @RequestMapping("lugares")
    public String escolherLugar(Sessao umaSessao, Model model,
                                @RequestParam Map<String, Integer> qntIngressosPorTipo) {

        Long idSessao = umaSessao.getIdSessao();
        Sessao sessao = sessaoServico.buscaSessaoPorId(idSessao);
        Long idSala = sessao.getSala().getId();
        Sala umaSala = salaServico.buscaSalaPorId(idSala);

        model.addAttribute("sala", umaSala);
        model.addAttribute("qntIngressosPorTipo", qntIngressosPorTipo);
        model.addAttribute("umaSessao", sessao);
        return MOSTRAR_LUGARES;
    }

    /**
     * @param assentoPorTipoIngresso é uma mapa com o nome do assento escolhido e o tipo do ingresso
     * @param sessao                 sessao do filme/peca escolhida
     * @param httpSession            dados de sessao do usuario no sistema
     * @return pagina para realizar a compra
     */
    @RequestMapping(value = "finalizarCompra")
    public ModelAndView finalizaCompra(@RequestParam HashMap<String, String> assentoPorTipoIngresso,
                                       Sessao sessao, HttpSession httpSession) {

        Cliente cliente = (Cliente) httpSession.getAttribute("usuarioLogado");
        String email = cliente.getEmail();
        Long idSessao = sessao.getIdSessao();
        compraServico.finalizarCompra(idSessao, email, assentoPorTipoIngresso);
        return new ModelAndView("redirect:" + OBRIGADO);
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

        compraServico.registrarPagamento(notificationCode);
    }

    /**
     * funcao chamada pelo pagseguro apos realizacao da compra
     *
     * @param codigoTransacao
     * @return pagina de agradecimento apos a efetuacao da compra
     */
    @RequestMapping("obrigado")
    public String retornarPaginaObrigado(@RequestParam String codigoTransacao) {
        compraServico.registrarCodigoTransacao(codigoTransacao);
        return OBRIGADO;
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
        return MOSTRAR_COMPRAS;
    }

}
