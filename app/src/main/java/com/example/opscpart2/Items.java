package com.example.opscpart2;

public class Items {

    private String details;
    private String date;

    public Items(){}

    public Items(String details, String date) {
        this.details = details;
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
