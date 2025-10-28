package com.miniproject2.model;

public class User {
    private final String username;
    private final String password;
    private final String fullName;

    public User(String username, String password, String fullName) {
        this.username = username; this.password = password; this.fullName = fullName;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
}
