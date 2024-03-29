package com.example.testiq.account;

public class User {
    private String username;
    private String password;
    private String email;
    private String confirmpass;

    public User() {

    }

    public User(String username, String password, String email, String confirmpass) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.confirmpass = confirmpass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }
}
