package com.example.comp4521_project;

public class User {
    private static User instance;
    private User(String username) {
        this.username = username;
    }

    private String username;

    public static User getInstance() {
        if (instance == null) {
            instance = new User("");
        }
        return instance;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
}
