package com.program3.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSet {

    private ObservableList<Student> students =  FXCollections.observableArrayList();
    private static DataSet INSTANCE;

    public DataSet() throws Exception{
        if(INSTANCE == null){
            INSTANCE = this;
        }else{
            throw new Exception("Data set already initialized.");
        }
    }

    public static DataSet getInstance(){
        return INSTANCE;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
    }

    public ObservableList<Student> getStudents(){
        return students;
    }

}
