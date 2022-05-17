package com.project.coffeeapp.models;

public class User {
    private String account;
    private String password;

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
