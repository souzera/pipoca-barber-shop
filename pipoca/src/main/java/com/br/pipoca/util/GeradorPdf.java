package com.br.pipoca.util;

import com.br.pipoca.entity.Atendimento;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;

public class GeradorPdf {

    public static void relatorioAtendimentos(List<Atendimento> lista) throws IOException, DocumentException, URISyntaxException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream("./relatorios/relatorio"+ new java.util.Date().getTime() +".pdf"));

        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        for(Atendimento a: lista){
            document.add(new Paragraph(new Chunk(a.toString(), font)));
        }

        document.close();
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

}
