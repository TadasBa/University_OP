package com.loancalculator.Action;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;


public class Type_Annuity extends Mortgage {

    public Type_Annuity(double amount, int term, double interest) {
        super(amount, term, interest);
    }

    @Override
    public void calculate(int from, int till, double interestD) {
        double n = interest * Math.pow(interest + 1, term);
        double d = Math.pow(interest + 1, term) - 1;
        double paymentAmount = loanAmount * ( n / d);
        double tempLoanAmount = loanAmount;
        for (int i = 0; i < term + till; i++){

            if(i + 1 == from){

                for(int j = 0; j < till; j++){

                    double tempInterest = tempLoanAmount * interestD;
                    TableData paymentD = new TableData(i + 1, tempInterest, 0, tempInterest, tempLoanAmount);
                    allPayments.add(paymentD);
                    i++;
                }
            }

            double tempInterest = tempLoanAmount * interest;
            double tempPrincipal = paymentAmount - tempInterest;
            TableData payment = new TableData(i + 1, paymentAmount, tempPrincipal, tempInterest, tempLoanAmount);
            tempLoanAmount -= tempPrincipal;
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

