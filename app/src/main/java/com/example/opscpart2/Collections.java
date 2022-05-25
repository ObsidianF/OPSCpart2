package com.example.opscpart2;

//hello

public class Collections {
    private String name;
    private String goal;
    private String uid;

    public Collections(){}

    public Collections(String name, String goal, String uid) {
        this.name = name;
        this.goal = goal;
        this.uid = uid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
