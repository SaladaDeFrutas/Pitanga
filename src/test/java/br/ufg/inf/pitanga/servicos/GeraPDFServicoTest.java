package br.ufg.inf.pitanga.servicos;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeraPDFServicoTest {

    GeraPDFServico geraPDFServico = new GeraPDFServico();
    public static final String RESULT = "C://developer//hello_memory.pdf";

    @Test
    public void testaGerarComprovante() throws IOException, DocumentException {

        FileOutputStream fos = new FileOutputStream(RESULT);
        ByteArrayOutputStream byteArrayOutputStream;
        Document documento = new Document();
        geraPDFServico.concatenaStringTexto("Ingresso : ingresso\n");
        geraPDFServico.concatenaStringTexto("Atração : atração\n");
        geraPDFServico.concatenaStringTexto("Sala : sala\n");
        geraPDFServico.concatenaStringTexto("Assento : assento\n");
        byteArrayOutputStream = geraPDFServico.gerarPDFComprovante();
        PdfWriter.getInstance(documento, byteArrayOutputStream);
        fos.write(byteArrayOutputStream.toByteArray());
        fos.close();
    }


}

