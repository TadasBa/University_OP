package com.program3.Data;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {

    private String group;
    private String name;
    private String surname;
    private int    nr;
    private ArrayList<Day> attendanceValues;

    public Student(String group, String name, String surname, int nr) {
        this.group = group;
        this.name = name;
        this.surname = surname;
        this.nr = nr;
        this.attendanceValues = new ArrayList<>();
    }

    public Student(String group, String name, String surname) {
        this.group = group;
        this.name = name;
        this.surname = surname;
        this.attendanceValues = new ArrayList<>();
    }

    public void updateValues(String group, String name, String surname) {
        this.group = group;
        this.name = name;
        this.surname = surname;
    }

    public void setAttendanceValuesList(ArrayList<Day> attendanceValues) {
        this.attendanceValues = attendanceValues;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getNr(){return nr;}

    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAttendanceValues(LocalDate day, boolean attended) {
        Day d;
        if(findDate(day) != null){
            d = findDate(day);
            d.setAttended(attended);
        }else{
            attendanceValues.add(new Day(day, attended));
        }
    }

    public boolean getAttendance(LocalDate day){
        if(findDate(day) != null){
            return  findDate(day).getAttended();
        }
        return false;
    }


    public Day findDate(LocalDate day) {

        for(Day d : attendanceValues){
            if(d.getDay() != null){
                if(d.getDay().equals(day)) {
                    return d;
                }
            }
        }
        return null;
    }

}
