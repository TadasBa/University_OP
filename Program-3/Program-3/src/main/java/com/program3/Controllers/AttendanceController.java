package com.program3.Controllers;

import com.program3.Data.DataSet;
import com.program3.Data.Student;
import com.program3.FileManager.ExportPDF;
import com.program3.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Objects;
import java.util.ResourceBundle;

public class AttendanceController  implements Initializable {

    @FXML
    TableView<Student> attendanceTable;
    @FXML
    Button back;
    @FXML
    TableColumn<Object, Object> groupCol;
    @FXML
    TableColumn<String, Student> nameCol;
    @FXML
    TableColumn<String, Student> surnameCol;
    @FXML
    TableColumn<Integer, Student> nrCol;
    @FXML
    private CheckBox checkBoxPresent;
    @FXML
    private DatePicker datePickerAttendance, datePickerFilterFrom, datePickerFilterTo;

    private static Student student;

    //--------------------------------------------------------------------------------
    // SWITCHING BETWEEN SCENES
    //--------------------------------------------------------------------------------

    public void switchToMainScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
    //--------------------------------------------------------------------------------
    // INITIALIZING VALUES / FILING ATTENDANCE TABLE
    //--------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Main.mainController.initialize(url, resourceBundle);

        nrCol.setCellValueFactory(new PropertyValueFactory<>("nr"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));

        datePickerAttendance.setValue(LocalDate.now());
        datePickerAttendance.setShowWeekNumbers(true);

        int i=1;
        for (Student s : DataSet.getInstance().getStudents()) {
            s.setNr(i);
            i++;
        }
        ObservableList<Student> dataSet = FXCollections.observableArrayList(DataSet.getInstance().getStudents());
        attendanceTable.setItems(dataSet);
    }
    //--------------------------------------------------------------------------------
    // MANAGING STUDENTS ATTENDANCE
    //--------------------------------------------------------------------------------
    @FXML
    protected void updateTableGroup() {

        student = attendanceTable.getSelectionModel().getSelectedItem();

        for (Student s : DataSet.getInstance().getStudents()) {
            if (Objects.equals(s.getGroup(), student.getGroup())) {
                updateTable(s, datePickerAttendance.getValue(), checkBoxPresent.isSelected());
            }
        }
    }
    @FXML
    protected void updateTableStudents(){

        student = attendanceTable.getSelectionModel().getSelectedItem();
        LocalDate date = datePickerAttendance.getValue();
        boolean attendance = checkBoxPresent.isSelected();

        updateTable(student, date, attendance);

    }

    protected void updateTable(Student student, LocalDate date, boolean attendance){

        student.setAttendanceValues(date, attendance);

        boolean checkCol = false;
        for(TableColumn<Student, ?> col : attendanceTable.getColumns()){
            if(col.getText().equals(date.toString())){
                checkCol = true;
            }
        }
        if(!checkCol && attendance){
            TableColumn<Student, String> newCol = new TableColumn<>(date.toString());
            newCol.setStyle("-fx-alignment: CENTER;");
            newCol.setCellValueFactory(data -> {
                String colName = data.getTableColumn().getText();
                LocalDate colDate = LocalDate.parse(colName);
                Boolean attendanceVal = data.getValue().getAttendance(colDate);
                return new SimpleStringProperty(attendanceVal ? "+" : "Absent");
            });
            attendanceTable.getColumns().add(newCol);

            Comparator<TableColumn<Student, ?>> comparator = (TableColumn<Student, ?> c1, TableColumn<Student, ?> c2) -> {
                LocalDate date1, date2;
                try {
                    date1 = LocalDate.parse(c1.getText());
                    date2 = LocalDate.parse(c2.getText());
                } catch (DateTimeParseException e) {
                    return 0;
                }
                return date1.compareTo(date2);
            };
            attendanceTable.getColumns().sort(comparator);
        }
        attendanceTable.refresh();
    }
    //--------------------------------------------------------------------------------
    // EXPORTING ATTENDANCE TABLE TO PDF FORMAT FILE
    //--------------------------------------------------------------------------------
    @FXML
    private void exportStudentsToPDF(){
        try {
            Stage stage = (Stage) attendanceTable.getScene().getWindow();
            new ExportPDF(stage).exportData(attendanceTable);
        }catch (Exception e){
            return;
        }
    }
    //--------------------------------------------------------------------------------
    // MANAGING ATTENDANCE FILTER
    //--------------------------------------------------------------------------------
    @FXML
    private void filter(){
        for(TableColumn c : attendanceTable.getColumns()){
            LocalDate dateCol;
            try {
                dateCol = LocalDate.parse(c.getText());
            } catch (DateTimeParseException e) {
                continue;
            }

            c.setVisible(checkRange(dateCol));
        }
    }

    private boolean checkRange(LocalDate date){
        return !date.isBefore(datePickerFilterFrom.getValue()) && !date.isAfter(datePickerFilterTo.getValue());
    }

    @FXML
    private void resetFilter(){
        datePickerFilterFrom.setValue(null);
        datePickerFilterTo.setValue(null);

        for(TableColumn c : attendanceTable.getColumns()){
            c.setVisible(true);
        }
    }
    //--------------------------------------------------------------------------------
}

