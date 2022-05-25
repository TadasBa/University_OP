package com.program3.FileManager;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.program3.Data.Student;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ExportPDF extends FileLoader implements Exporter<TableView<Student>>{

    public ExportPDF(Stage stage) {
        super(stage, "Export students to PDF", "studentsPDF.pdf", save);
    }

    @Override
    public FileChooser.ExtensionFilter[] getFileExtensions() {
        return new FileChooser.ExtensionFilter[] {
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
        };
    }

    @Override
    public void exportData(TableView<Student> table){

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.err.println("Failed to locate the file");
        } catch (DocumentException e) {
            System.err.println("Failed to add the writer to the PDF document");
        }
        document.open();

        PdfPTable pdfPTable = new PdfPTable(table.getColumns().filtered(c -> c.isVisible()).size());

        for(int z = 0; z < table.getColumns().size(); z++) {
            if (table.getColumns().get(z).isVisible()) {
                pdfPTable.addCell(new Phrase(table.getColumns().get(z).getText()));
            }
        }
        pdfPTable.completeRow();

        for(int i = 0; i < table.getItems().size(); i++){
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (!table.getColumns().get(j).isVisible()) {
                    continue;
                }
                if (table.getColumns().get(j).getCellData(i) != null) {
                    pdfPTable.addCell(new Phrase(table.getColumns().get(j).getCellData(i).toString()));
                } else {
                    pdfPTable.addCell(new Phrase(""));
                }
            }
            pdfPTable.completeRow();
        }

        try{
            document.add(pdfPTable);
        } catch (DocumentException e){
            System.out.println("Failed to export data to PDF");
        }

        document.close();
        pdfWriter.close();
    }
}
