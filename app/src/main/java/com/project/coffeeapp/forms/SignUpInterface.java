package com.project.coffeeapp.forms;

import com.project.coffeeapp.models.User;

public interface SignUpInterface {
    void accountInvalid();
    void passwordInvalid();
    void fullNameInvalid();
    void confirmError();
    void signUpSuccess(User user);
    void validAccount();
    void validPassword();
    void validFullName();
}
