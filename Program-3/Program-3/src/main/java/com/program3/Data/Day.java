package com.program3.Data;

import java.time.LocalDate;

public class Day {

    private LocalDate day;
    private boolean attended = false;

    public Day(LocalDate day, boolean attended) {
        this.day = day;
        this.attended = attended;
    }

    public Day(LocalDate day) {
        this.day = day;
        this.attended = false;
    }

    public LocalDate getDay() {
        return day;
    }

    public boolean getAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
