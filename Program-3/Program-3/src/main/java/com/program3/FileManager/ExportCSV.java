package com.program3.FileManager;

import com.program3.Data.DataSet;
import com.program3.Data.Student;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ExportCSV extends FileLoader implements Exporter<DataSet> {

    public ExportCSV(Stage stage) {
        super(stage, "Export students to CSV", "StudentList.csv", save);
    }

    @Override
    public FileChooser.ExtensionFilter[] getFileExtensions() {
        return new FileChooser.ExtensionFilter[]{
                new FileChooser.ExtensionFilter("CSV", "*.csv"),
        };
    }

    @Override
    public void exportData(DataSet dataSet) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write("Group,First name, Last name");
            writer.newLine();

            for (Student s : dataSet.getStudents()) {
                writer.write(s.getGroup() + ",");
                writer.write(s.getName() + ",");
                writer.write(s.getSurname() + ",");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to export students to CSV");
        }
    }
}

