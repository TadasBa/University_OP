package com.program3.FileManager;

import com.program3.Controllers.MainController;
import com.program3.Data.DataSet;
import com.program3.Data.Student;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;

public class ImportExcel extends FileLoader implements Importer{

    private String group, name, surname;

    public ImportExcel(Stage stage) {
        super(stage, "Import students from Excel", "studentsExcel.xlsx", save);
    }

    @Override
    public FileChooser.ExtensionFilter[] getFileExtensions() {
        return new FileChooser.ExtensionFilter[] {
                new FileChooser.ExtensionFilter("Excel", "*.xlsx"),
        };
    }

    @Override
    public void importData(){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                group = cellIterator.next().getStringCellValue();
                name = cellIterator.next().getStringCellValue();
                surname = cellIterator.next().getStringCellValue();
                Student s = new Student(group, name, surname);
                DataSet.getInstance().addStudent(s);
            }
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to import data from Excel");
        }

    }


}