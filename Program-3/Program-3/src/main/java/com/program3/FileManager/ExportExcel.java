package com.program3.FileManager;

import com.program3.Data.DataSet;
import com.program3.Data.Student;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExportExcel extends FileLoader implements Exporter<DataSet> {

    public ExportExcel(Stage stage) {
        super(stage, "Export students to Excel", "StudentList.xlsx", save);
    }

    @Override
    public FileChooser.ExtensionFilter[] getFileExtensions() {
        return new FileChooser.ExtensionFilter[] {
                new FileChooser.ExtensionFilter("Excel", "*.xlsx"),
        };
    }

    @Override
    public void exportData(DataSet dataSet){

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Students");
        XSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("Group");
        titleRow.createCell(1).setCellValue("First name");
        titleRow.createCell(2).setCellValue("Last name");

        int rowCount = 0;
        for(Student s : dataSet.getStudents()){
            XSSFRow row = sheet.createRow(rowCount + 1);
            row.createCell(0).setCellValue(s.getGroup());
            row.createCell(1).setCellValue(s.getName());
            row.createCell(2).setCellValue(s.getSurname());

            ++rowCount;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            workbook.close();
        }catch (IOException e){
            System.out.println("Failed to export data to Excel file");
        }
    }
}
