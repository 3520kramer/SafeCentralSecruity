package com.example.demo.Model;

/*
Importere entity og id sådan så vi kan forbinde til vores database
Entity bruges i stedet for @Table prøver på at forbinde til mens
id er vores id i de forskellige tabeller

Klasse indeholder desuden en masse fields samt getter og setter
 */

public class DateFromTo {
// Ansvarlige: Oliver


    private String date;
    private String dateTo;

    public DateFromTo() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
