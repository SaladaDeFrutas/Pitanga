package br.ufg.inf.pitanga.servicos;

import br.ufg.inf.pitanga.entidades.Ingresso;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class GeraPDF {

    //public static final String RESULT = "C://developer//hello_memory.pdf";
    private String textoParaPDF;

    public GeraPDF() {
        this.textoParaPDF = new String();
        this.textoParaPDF = "";
    }

    /**
     * Gera um Document com as informações do ingresso em PDF
     * Utilizando a API Itext 1.3.1
     *
     * @param ingresso
     * @throws DocumentException
     */
    public static Document gerarPDFIngresso(Ingresso ingresso) throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//Pra escrever in-memory
        Document documento = new Document();
        PdfWriter.getInstance(documento, baos);
        documento.open();
        //documento.addTitle("Ingresso");
        Paragraph textoTicket = new Paragraph();
        textoTicket.add("=====================================\n" +
            "=================CINEMA SVRI=========\n" +
            "Atração: " + ingresso.getUmaSessao().getAtracao().getTitulo() + "\n" +
            "Data: " + ingresso.getUmaSessao().getData() + "\n" +
            "Sala: " + ingresso.getUmaSessao().getSala() +
            " Assento:" + ingresso.getUmAssento().getFila() + ingresso.getUmAssento().getColuna() +
            "Tipo: " + ingresso.getUmTipoIngresso().getNome());
        documento.add(textoTicket);
        documento.close();
//		//Esse é o código de teste, pra ver o que ele deu como output no sistema.
//		try {//Escreve o documento no lugar especificado no RESULT, para ver como foi o output
//			FileOutputStream fos = new FileOutputStream(RESULT);
//			fos.write(baos.toByteArray());
//			fos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//endTeste

        return documento;
    }


    /**
     * Gera um Document com as informações do ingresso em PDF
     * Utilizando a API Itext 1.3.1
     *
     * @throws DocumentException
     */
    public ByteArrayOutputStream gerarPDFComprovante() throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//Pra escrever in-memory
        Document documento = new Document();
        PdfWriter.getInstance(documento, baos);
        documento.open();
        //documento.addTitle("Ingresso");
        Paragraph textoTicket = new Paragraph();
        textoTicket.add("Comprovate de Compra:\n" +
            this.textoParaPDF);
        documento.add(textoTicket);
        documento.close();
        return baos;
    }

    /**
     * Concatena a string para escrever no PDF
     *
     * @param texto O texto que será concatenado
     */
    public void concatenaStringTexto(String texto) {
        this.textoParaPDF = textoParaPDF + texto;
    }
}
