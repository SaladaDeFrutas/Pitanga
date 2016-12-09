package br.ufg.inf.pitanga.controllers;

import br.ufg.inf.pitanga.entidades.Compra;
import br.ufg.inf.pitanga.entidades.Ingresso;
import br.ufg.inf.pitanga.servicos.CompraServico;
import br.ufg.inf.pitanga.servicos.GeraPDFServico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class GeradorPDFController {

    GeraPDFServico geradorPDFServico;

    @Autowired
    private CompraServico compraServico;


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public GeradorPDFController() {
        geradorPDFServico = new GeraPDFServico();
    }

    @RequestMapping("gerarComprovante")
    public void gerarComprovantePdf(Compra compraApenasComId, HttpServletResponse response) {
        Compra compra = compraServico.buscarPorId(compraApenasComId.getId());
        List<Ingresso> ingressosCompra = compra.getIngressos();
        escreveDadosGeraisDaCompraNoComprovante(compra);

        for (Ingresso ingresso : ingressosCompra)
            escreveInformacoesSobreIngressoNoComprovante(ingresso);

        try {
            response.getOutputStream().write(geradorPDFServico.gerarPDFComprovante().toByteArray());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=ingresso" + compra.getId() + ".pdf");
            response.flushBuffer();
        } catch (IOException e) {
            log.error("ERRO ao gerar comprovante em PDF: " + e.getMessage());
        }

    }

    private void escreveDadosGeraisDaCompraNoComprovante(Compra compra) {
        geradorPDFServico.concatenaStringTexto("ID da CompraServico: " + compra.getId() + "\n");
        geradorPDFServico.concatenaStringTexto("Data: " + new SimpleDateFormat("dd/MM/yy HH:mm").format(
            compra.getDataCompra().getTime()) + "\n");
        geradorPDFServico.concatenaStringTexto("Status da compra: " + getStatusCompra(compra) + "\n");
        geradorPDFServico.concatenaStringTexto("Nome do Cliente: " + compra.getCliente().getNome() + "\n");
        geradorPDFServico.concatenaStringTexto("--------------------------------------------------------------"
            + "----------------------------------------------------\n");
        geradorPDFServico.concatenaStringTexto("\nIngressos escolhidos:\n");
    }


    private void escreveInformacoesSobreIngressoNoComprovante(Ingresso ingresso) {

        geradorPDFServico.concatenaStringTexto("Tipo: " + ingresso.getUmTipoIngresso().getNome() + "\n");
        geradorPDFServico.concatenaStringTexto("Data de Exibição: " + new SimpleDateFormat("dd/MM/yy HH:mm").format(
            ingresso.getUmaSessao().getData().getTime()) + "\n");
        geradorPDFServico.concatenaStringTexto("Atração: " + ingresso.getUmaSessao().getAtracao().getTitulo() + "\n");
        geradorPDFServico.concatenaStringTexto("Sala: " + ingresso.getUmaSessao().getIdSessao() + "\n");
        geradorPDFServico.concatenaStringTexto("Assento: " + (ingresso.getUmAssento().getFila() + 1) + (ingresso.getUmAssento().getColuna() + 1) + "\n\n");
    }

    private String getStatusCompra(Compra compra) {
        if (!compra.isPagamentoAprovado())
            return "Não concluído";
        else
            return "Concluído";
    }
}
