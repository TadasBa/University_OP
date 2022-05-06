package com.loancalculator.Action;
//-----------------------------------------------------------------------------------------------------
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//-----------------------------------------------------------------------------------------------------
public abstract class Mortgage {
    protected double loanAmount;
    protected int term;
    protected double interest;
    protected ObservableList<TableData> allPayments;
//-----------------------------------------------------------------------------------------------------
    public Mortgage(double loanAmount, int term, double interest) {
        this.loanAmount= loanAmount;
        this.term = term;
        this.interest = interest;
        allPayments = FXCollections.observableArrayList();
    }
//-----------------------------------------------------------------------------------------------------
    public abstract void calculate(int from, int till, double interestD);
    public abstract ObservableList<TableData> getAllPayments();
    public abstract ObservableList<TableData> getAllPayments(int from, int till);
}
