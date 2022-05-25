package com.program3.Controllers;

import com.program3.Data.DataSet;
import com.program3.Data.Student;
import com.program3.FileManager.ExportCSV;
import com.program3.FileManager.ExportExcel;
import com.program3.FileManager.ImportCSV;
import com.program3.FileManager.ImportExcel;
import com.program3.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private DataSet dataSet;
    private String group, name, surname;
    public String fullName;
    int i=0;

    @FXML
    private TableColumn<Student, Integer> COLgroup;
    @FXML
    private TableColumn<Student, String> COLname;
    @FXML
    private TableColumn<Student, String> COLsurname;
    @FXML
    private TableView<Student> TVstudents;
    @FXML
    private TextField TFgroup;
    @FXML
    private TextField TFname;
    @FXML
    private TextField TFsurname;
    @FXML
    private ChoiceBox<String> choiceBoxChangeValue;
    @FXML
    private TextField textFieldNewValue;

    //--------------------------------------------------------------------------------
    // WORKING WITH STUDENT INFORMATION
    //--------------------------------------------------------------------------------

    @FXML
    void addStudent(ActionEvent event) {
        dataSet = DataSet.getInstance();
        group = TFgroup.getText();
        name = TFname.getText();
        surname = TFsurname.getText();

        // ADDING STUDENT TO TABLE
        dataSet.addStudent(new Student(group, name, surname));
        TVstudents.refresh();
        clear();
    }

    @FXML
    protected void ApplyChanges(ActionEvent event) throws IOException {
        Student student = TVstudents.getSelectionModel().getSelectedItem();
        String fieldToChange = choiceBoxChangeValue.getValue();
        Object newValue;

        switch (fieldToChange) {
            case "Group": {
                newValue = textFieldNewValue.getText();
                student.setGroup((String) newValue);
                break;
            }
            case "First name": {
                newValue = textFieldNewValue.getText();
                student.setName((String) newValue);
                break;
            }
            case "Last name": {
                newValue = textFieldNewValue.getText();
                student.setSurname((String) newValue);
                break;
            }
        }
        textFieldNewValue.clear();
        choiceBoxChangeValue.setValue("");
        TVstudents.refresh();
}


    @FXML
    void deleteStudent(ActionEvent event) {
        Student student = TVstudents.getSelectionModel().getSelectedItem();
        dataSet.removeStudent(student);
        TVstudents.refresh();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dataSet = new DataSet();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        COLgroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        COLname.setCellValueFactory(new PropertyValueFactory<>("name"));
        COLsurname.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TVstudents.setItems(dataSet.getStudents());


        if(i==0) {
            choiceBoxChangeValue.getItems().addAll("Group", "First name", "Last name");
            i++;
        }
    }

    //--------------------------------------------------------------------------------
    // SWITCHING BETWEEN SCENES
    //--------------------------------------------------------------------------------

    public void switchToAttendanceScene(ActionEvent event) throws IOException {
        WindowManager.newWindow("Attendance List", "attendance.fxml");
    }

    //--------------------------------------------------------------------------------
    // CLEANING UP
    //--------------------------------------------------------------------------------

    public void clear()
    {
        TFname.clear();
        TFgroup.clear();
        TFsurname.clear();
    }

    //--------------------------------------------------------------------------------
    // MANAGING EXCEL/CSV FILES
    //--------------------------------------------------------------------------------
    @FXML
    private void exportStudentsToExcel(){
        try {
            Stage stage = (Stage) TVstudents.getScene().getWindow();
            new ExportExcel(stage).exportData(dataSet);
        }catch (Exception e){
            return;
        }
    }
    @FXML
    private void importStudentsFromExcel(){
        try {
            Stage stage = (Stage) TVstudents.getScene().getWindow();
            new ImportExcel(stage).importData();
        }catch (Exception e){
           e.printStackTrace();
        }
    }
    @FXML
    private void exportStudentsToCSV(){
        try {
            Stage stage = (Stage) TVstudents.getScene().getWindow();
            new ExportCSV(stage).exportData(dataSet);
        }catch (Exception e){
            return;
        }
    }
    @FXML
    private void importStudentsFromCSV(){
        try {
            Stage stage = (Stage) TVstudents.getScene().getWindow();
            new ImportCSV(stage).importData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
