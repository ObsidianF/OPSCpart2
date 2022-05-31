package com.example.opscpart2;

public class ItemGetSet {

    private String details;
    private String date;
    private String CollectionID;
    private String imagename;


    public ItemGetSet(){}

    public ItemGetSet(String details, String date, String collectionID, String imagename) {
        this.details = details;
        this.date = date;
        CollectionID = collectionID;
        this.imagename = imagename;
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

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
}
