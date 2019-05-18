package com.example.daytworx;

public class User {
    String userName;
    String geneder;

    public User() {
    }

    public User(String userName, String geneder) {
        this.userName = userName;
        this.geneder = geneder;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGeneder() {
        return geneder;
    }

    public void setGeneder(String geneder) {
        this.geneder = geneder;
    }
}
