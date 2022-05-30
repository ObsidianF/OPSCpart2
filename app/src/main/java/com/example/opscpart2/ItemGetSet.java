package com.example.opscpart2;

public class ItemGetSet {

    private String details;
    private String date;
    private String CollectionID;

    public ItemGetSet(){}

    public ItemGetSet(String details, String date, String collectionID) {
        this.details = details;
        this.date = date;
        CollectionID = collectionID;
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

    public String getCollectionID() {
        return CollectionID;
    }

    public void setCollectionID(String collectionID) {
        CollectionID = collectionID;
    }
}
