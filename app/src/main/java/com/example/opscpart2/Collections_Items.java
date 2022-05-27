package com.example.opscpart2;

//hello

public class Collections_Items {
    private String name;
    private String goal;
    private String uid;
    private String id;

    public Collections_Items(){}

    public Collections_Items(String name, String goal, String uid, String id) {
        this.name = name;
        this.goal = goal;
        this.uid = uid;
        this.id = id;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getUid() { return uid;}

    public void setUid(String uid) {this.uid = uid;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
