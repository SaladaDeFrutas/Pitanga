package br.ufg.inf.pitanga.servicos;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class GeraPDFServico {

    private StringBuilder textoParaPDF;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public GeraPDFServico() {
        this.textoParaPDF = new StringBuilder("");
    }

    public ByteArrayOutputStream gerarPDFComprovante() {
        Document documento = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(documento, baos);
            documento.open();
            Paragraph textoTicket = new Paragraph();
            textoTicket.add("Comprovate de Compra:\n" +
                this.textoParaPDF);
            documento.add(textoTicket);
        } catch (DocumentException e) {
            log.error("ERRO ao escrever no documento.", e);
        } finally {
            documento.close();
        }
        return baos;
    }

    /**
     * Concatena a string para escrever no PDF
     *
     * @param texto O texto que será concatenado
     */
    public void concatenaStringTexto(String texto) {
        this.textoParaPDF.append(texto);
    }
}
