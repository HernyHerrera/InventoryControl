package com.hherrera.inventory.pdf;
import com.hherrera.inventory.response.product.ProductResponseData;
import com.hherrera.inventory.response.product.ProductResponseRest;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class StockPdf{
    private List<ProductResponseData> products;

    public StockPdf(List<ProductResponseData> products) {
        this.products = products;
    }
    private void headerTable(PdfPTable table){
        Color color = new Color(21, 71, 115);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(color);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("N°", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);
        cell.setPhrase(new Phrase("Codígo", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nombre", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);
        cell.setPhrase(new Phrase("Categoria", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);
        cell.setPhrase(new Phrase("Stock", font));
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        table.addCell(cell);
    }
    private void createTable(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        int index =0;

        for (ProductResponseData product : products){

            index++;
            cell.setPhrase(new Phrase(String.valueOf(index), font));
            cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(product.getCode()), font));
            cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(product.getNameProduct()), font));
            cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(product.getCategory()), font));
            cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(product.getStock()), font));
            cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
            table.addCell(cell);

        }
    }
    public void exportReport(HttpServletResponse response) throws IOException {
        Color color = new Color(21, 71, 115);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(color);
        font.setSize(18);
        Paragraph title = new Paragraph("Stock de productos", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.setMargins(20, 20, 20, 20);
        document.add(title);
        PdfPTable table = new PdfPTable(5);
        table.setSpacingAfter(20);
        table.setSpacingBefore(20);
        table.setWidthPercentage(100);
        table.setWidths(new float[] {1f, 2f, 3f, 3f, 2f});
        table.setWidthPercentage(110);
        headerTable(table);
        createTable(table);
        document.add(table);
        document.close();
    }
}
