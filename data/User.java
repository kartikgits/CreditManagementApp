package com.northwindlabs.kartikeya.creditmanagement.data;

public class User {
    private String userName;
    private int userCredits;

    public User(String userName, int userCredits) {
        this.userName = userName;
        this.userCredits = userCredits;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserCredits() {
        return userCredits;
    }
}
