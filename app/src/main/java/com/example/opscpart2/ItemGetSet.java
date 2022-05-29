package com.example.opscpart2;

public class ItemGetSet {

    private String details;
    private String date;

    public ItemGetSet(){}

    public ItemGetSet(String details, String date) {
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
