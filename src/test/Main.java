package test;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        String name = "recibo.pdf"; 
        PdfWriter pdfWriter = new PdfWriter(name);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);
        
        //720
        float twocol = 285f;
        float twocol150 = twocol + 150f;
        float twocolumnWidth[] = {520f, 200f};
        float productosModel[] = {60f, 660f};
        
        //HEADER
        Table table1 = new Table(twocolumnWidth);
        table1.addCell(new Cell().add("ENTRADA ALMACÉN \nSEÑALAMIENTO Y DISPOSITIVOS VIALES").setBold().setBorder(Border.NO_BORDER));
        Table table1_1  = new Table(new float[]{100, 100}); 
        ImageData imageData = ImageDataFactory.create("src\\img\\monterrey.png"); 
        ImageData imageData2 = ImageDataFactory.create("src\\img\\monterrey2.png"); 
        table1_1.addCell(new Cell().add(new Image(imageData).setWidth(70).setMarginLeft(40)).setBorder(Border.NO_BORDER)); 
        table1_1.addCell(new Cell().add(new Image(imageData2).setWidth(70)).setMarginRight(40).setBorder(Border.NO_BORDER)); 
        table1.addCell(new Cell().add(table1_1).setBorder(Border.NO_BORDER));
        document.add(table1);
        
        Table table2 = new Table(twocolumnWidth);
        Table table2_1 = new Table(new float[]{520});
        table2_1.addCell(new Cell().add("Nombre: ").setBorder(Border.NO_BORDER).setFontSize(10f));
        table2_1.addCell(new Cell().add("Proovedor: ").setBorder(Border.NO_BORDER).setFontSize(10f));
        table2_1.addCell(new Cell().add("Almacenista: ").setBorder(Border.NO_BORDER).setFontSize(10f));   
        table2.addCell(new Cell().add(table2_1).setBorder(Border.NO_BORDER));
        DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String date = dateFormat.format(Calendar.getInstance().getTime()); 
        table2.addCell(new Cell().add("Fecha: " + date).setBorder(Border.NO_BORDER).setFontSize(10f));
        document.add(table2);
        
        //PRODUCTS
        document.add(new Paragraph("\n"));
        Table table3 = new Table(productosModel);
        table3.addCell(new Cell().add("Cantidad").setTextAlignment(TextAlignment.CENTER).setFontSize(10f));
        table3.addCell(new Cell().add("Material").setTextAlignment(TextAlignment.CENTER).setFontSize(10f));
        document.add(table3);

        //OBSERVACIONES
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Observaciones").setFontSize(10f));
        
        //FOOTER
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        Table table4 = new Table(new float[]{360,360});
        table4.addCell(new Cell().add("_________________").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER)); 
        table4.addCell(new Cell().add("_________________").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER)); 
        table4.addCell(new Cell().add("Firma almacenista").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(10f)); 
        table4.addCell(new Cell().add("Firma proovedor").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(10f)); 
        document.add(table4);
        
         document.close();
        
        
       
    }
}
