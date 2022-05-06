package com.loancalculator.Action;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Type_Linear extends Mortgage{

    public Type_Linear(double loanAmount, int term, double interest){
        super(loanAmount, term, interest);
    }

    @Override
    public void calculate(int from, int till, double interestD) {
        double principal = loanAmount / term;
        double tempLoanAmount = loanAmount;

        for(int i = 0; i < term; i++){

            if(i + 1 == from){

                for(int j = 0; j < till; j++){

                    double tempInterest = tempLoanAmount * interestD;
                    TableData paymentD = new TableData(i + 1, tempInterest, 0, tempInterest, tempLoanAmount);
                    allPayments.add(paymentD);
                    i++;
                }
            }

            double paymentAmount = principal + (tempLoanAmount * interest);
            TableData payment = new TableData(i + 1, paymentAmount, principal, (tempLoanAmount * interest), tempLoanAmount);
            tempLoanAmount -= principal;
            allPayments.add(payment);
        }
    }

    @Override
    public ObservableList<TableData> getAllPayments() {
        return super.allPayments;
    }

    @Override
    public ObservableList<TableData> getAllPayments(int from, int till) {
        return FXCollections.observableArrayList(super.allPayments.subList(from, till));
    }
}
