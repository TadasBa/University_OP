package com.program3.FileManager;

import com.program3.Data.DataSet;
import com.program3.Data.Student;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class ImportCSV extends FileLoader implements Importer {

    String group, name, surname;

    public ImportCSV(Stage stage) {
        super(stage, "Import students from CSV", "StudentList.csv", save);
    }

    @Override
    public FileChooser.ExtensionFilter[] getFileExtensions() {
        return new FileChooser.ExtensionFilter[] {
                new FileChooser.ExtensionFilter("CSV", "*.csv"),
        };
    }

    @Override
    public void importData(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e1) {
            System.err.println("File not found");
        }

        try{
            int linesRead = 0;
            String line;
            while((line = reader.readLine()) != null){
                if(linesRead == 0)
                {
                    ++linesRead;
                    continue;
                }
                String[] col = line.split(",");
                Student s = new Student(col[0], col[1], col[2]);
                DataSet.getInstance().addStudent(s);
            }
        }catch (IOException e2){
            System.err.println("Failed to read the file");
        }

        try{
            reader.close();
        }catch (IOException e){
            System.err.println("Failed to close the reader");
        }

    }
    }
