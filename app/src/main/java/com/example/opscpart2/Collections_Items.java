package com.example.opscpart2;

public class Collections_Items {

    //this call is here to hold values before they are either stored in the database or taken to the view to be displayed

    private String name;
    private int goal;
    private String uid;
    private String id;
    private int numberOfItems;

    public Collections_Items() {
    }

    public Collections_Items(String name, int goal, String uid, String id, int numberOfItems){

        this.name = name;
        this.goal = goal;
        this.uid = uid;
        this.id = id;
        this.numberOfItems = numberOfItems;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
