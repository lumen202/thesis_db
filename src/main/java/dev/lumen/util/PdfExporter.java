package dev.lumen.util;


// import com.itextpdf.kernel.colors.DeviceRgb;
// import com.itextpdf.kernel.pdf.PdfDocument;
// import com.itextpdf.kernel.pdf.PdfWriter;
// import com.itextpdf.layout.Document;
// import com.itextpdf.layout.element.Cell;
// import com.itextpdf.layout.element.Paragraph;
// import com.itextpdf.layout.element.Table;
// import com.itextpdf.layout.property.TextAlignment;
// import com.itextpdf.layout.property.UnitValue;
import dev.lumen.models.Thesis;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PdfExporter {
    // public static void exportThesisReport(List<Thesis> thesisList, String filePath) throws IOException {
    //     PdfWriter writer = new PdfWriter(new File(filePath));
    //     PdfDocument pdf = new PdfDocument(writer);
    //     Document document = new Document(pdf);

    //     // Add title
    //     document.add(new Paragraph("Thesis Report")
    //             .setFontSize(20)
    //             .setBold()
    //             .setTextAlignment(TextAlignment.CENTER));

    //     // Add date
    //     document.add(new Paragraph("Generated: " + LocalDate.now())
    //             .setTextAlignment(TextAlignment.RIGHT)
    //             .setMarginBottom(20));

    //     // Create table with 5 columns
    //     Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 3, 2, 2, 2 }))
    //             .useAllAvailableWidth()
    //             .setMarginTop(10);

    //     // Add header
    //     String[] headers = { "ID", "Title", "Year", "Month", "Degree" };
    //     for (String header : headers) {
    //         table.addHeaderCell(new Cell()
    //                 .add(new Paragraph(header)
    //                         .setBold()
    //                         .setTextAlignment(TextAlignment.CENTER))
    //                 .setBackgroundColor(new DeviceRgb(200, 200, 200)));
    //     }

    //     // Add data rows
    //     for (Thesis thesis : thesisList) {
    //         table.addCell(createCell(String.valueOf(thesis.getThesisID())));
    //         table.addCell(createCell(thesis.getThesisTitle()));
    //         table.addCell(createCell(String.valueOf(thesis.getYear())));
    //         table.addCell(createCell(Month.fromNumber(thesis.getMonth()).toString()));
    //         table.addCell(createCell(getDegreeName(thesis.getDegID())));
    //     }

    //     document.add(table);
    //     document.close();
    // }

    // private static Cell createCell(String content) {
    //     return new Cell()
    //             .add(new Paragraph(content))
    //             .setPadding(5)
    //             .setTextAlignment(TextAlignment.LEFT);
    // }

    // private static String getDegreeName(int degID) {
    //     // Implement your degree name lookup logic here
    //     return "Degree " + degID; // Replace with actual degree name
    // }
}
