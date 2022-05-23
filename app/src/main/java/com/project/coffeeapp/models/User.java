package com.project.coffeeapp.models;

import com.google.gson.annotations.SerializedName;

public class User {
    private String username;
    private String password;
    private String fullName;

    public User() {
    }

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    //    public boolean isValidAccount(){
//        return !TextUtils.isEmpty(account) && (account.length() >= 6);
//    }
//
//    public boolean isValidPassword(){
//        return !TextUtils.isEmpty(password) && (password.length() >= 6);
//    }

//    public boolean isConfirmCorrect(){
//        return confirmPassword.equals(password);
//    }

}
