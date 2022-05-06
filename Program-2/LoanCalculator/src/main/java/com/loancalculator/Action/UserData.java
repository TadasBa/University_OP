package com.loancalculator.Action;
//-----------------------------------------------------------------------------------------------------
public class UserData {
    private final int endMonth;
    private final int startMonth;

    private final int fromMonth;
    private final int tillMonth;
    private final double deferInterest;

    private final double amount;
    private final double interest;
    private final int termYears;
    private final int termMonths;
    private final String type;
    //-----------------------------------------------------------------------------------------------------
    public UserData(int endMonth, int startMonth, int fromMonth, int tillMonth, double deferInterest, double amount, double interest, int termYears, int termMonths, String type) {
        this.endMonth = endMonth;
        this.startMonth = startMonth;
        this.fromMonth = fromMonth;
        this.tillMonth = tillMonth;
        this.deferInterest = deferInterest;
        this.amount = amount;
        this.interest = interest;
        this.termYears = termYears;
        this.termMonths = termMonths;
        this.type = type;
    }
    //-----------------------------------------------------------------------------------------------------
    public int getFromMonth() {
        return fromMonth;
    }
    //-----------------------------------------------------------------------------------------------------
    public int getTillMonth() {
        return tillMonth;
    }
    //-----------------------------------------------------------------------------------------------------
    public double getDeferInterest() {
        return Math.round(deferInterest * 100d) / 100d;
    }
    //-----------------------------------------------------------------------------------------------------
    public int getEndMonth() {
        return endMonth;
    }

    //-----------------------------------------------------------------------------------------------------
    public int getStartMonth() {
        return startMonth;
    }
    //-----------------------------------------------------------------------------------------------------
    public double getAmount() {
        return Math.round(amount * 100d) / 100d;
    }
    //-----------------------------------------------------------------------------------------------------
    public double getInterest() {
        return Math.round(interest * 100d) / 100d;
    }
    //-----------------------------------------------------------------------------------------------------
    public int getTermYears() {
        return termYears;
    }
    //-----------------------------------------------------------------------------------------------------
    public int getTermMonths() {
        return termMonths;
    }
    //-----------------------------------------------------------------------------------------------------
    public String getType() {
        return type;
    }
//-----------------------------------------------------------------------------------------------------
}
