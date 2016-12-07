package br.ufg.inf.pitanga.controller;

import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.interfaces.dao.*;
import br.ufg.inf.pitanga.servicos.GeraPDF;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class GeradorPDFController {

    @Autowired
    private InterfaceTipoIngressoDao tipoIngressoDao;

    @Autowired
    private InterfaceSalaDao salaDao;

    @Autowired
    private InterfaceSessaoDao sessaoDao;

    @Autowired
    private InterfaceIngressoDao ingressoDao;

    @Autowired
    private InterfaceClienteDao clienteDao;

    @Autowired
    private InterfaceCompraDao compraDao;

    @Autowired
    private InterfaceFilmeDao filmeDao;

    @Autowired
    private InterfacePecaDao pecaDao;

    @RequestMapping("gerarComprovante")
    public void gerarComprovantePdf(Compra compra, HttpServletResponse response) {
        GeraPDF geradorPDF = new GeraPDF();
        compra.getId();

        compra = compraDao.buscarPorId(
            compra.getId());

        /**
         * itens a colocar no pdf
         */
        // id da compra
        geradorPDF.concatenaStringTexto("ID da CompraServico: " + compra.getId() + "\n");

        // data da compra
        geradorPDF.concatenaStringTexto("Data: " + new SimpleDateFormat("dd/MM/yy HH:mm").format(compra.getDataCompra().getTime()) + "\n");

        String status;
        if (!compra.isPagamentoAprovado())
            status = "Não concluído";
        else
            status = "Concluído";
        //status da compra true ou false pro pagamento
        geradorPDF.concatenaStringTexto("Status da compra: " + status + "\n");

        // nome do cliente
        Cliente umCliente = clienteDao.buscarPorId(compra.getCliente().getEmail());
        geradorPDF.concatenaStringTexto("Nome do Cliente: " + umCliente.getNome() + "\n");
        geradorPDF.concatenaStringTexto("--------------------------------------------------------------"
            + "----------------------------------------------------\n");
        geradorPDF.concatenaStringTexto("\nIngressos escolhidos:\n");

        //ingressos da compra
        List<Ingresso> ingressosCompra = ingressoDao.buscaPorRegistroCompra(compra);
        for (Ingresso ingresso : ingressosCompra) {
            // tipo do ingresso
            geradorPDF.concatenaStringTexto("Tipo: " + ingresso.getUmTipoIngresso().getNome() + "\n");

            // sessao com data de exibicao
            Sessao umaSessao = sessaoDao.buscarPorId(ingresso.getUmaSessao().getIdSessao());
            geradorPDF.concatenaStringTexto("Data de Exibição: " + new SimpleDateFormat("dd/MM/yy HH:mm").format(
                umaSessao.getData().getTime()) + "\n");

            Long idAtracao = umaSessao.getAtracao().getId();
            Filme filme = filmeDao.buscarPorId(idAtracao);
            if (filme != null)
                // nome do filme
                geradorPDF.concatenaStringTexto("Filme: " + filme.getTitulo() + "\n");
            else {
                // nome da peca
                Peca peca = pecaDao.buscarPorId(idAtracao);
                geradorPDF.concatenaStringTexto("Peça: " + peca.getTitulo() + "\n");
            }

            // id da sala
            geradorPDF.concatenaStringTexto("Sala: " + umaSessao.getIdSessao() + "\n");

            // fileira e coluna da sala pro ingresso
            geradorPDF.concatenaStringTexto("Assento: " + (ingresso.getUmAssento().getFileira() + 1) + (ingresso.getUmAssento().getColuna() + 1) + "\n\n");
        }
        //retorna o pdf completo
        try {
            response.getOutputStream().write(geradorPDF.gerarPDFComprovante().toByteArray());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=ingresso" + compra.getId() + ".pdf");
            response.flushBuffer();
        } catch (IOException | DocumentException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

    }
}
