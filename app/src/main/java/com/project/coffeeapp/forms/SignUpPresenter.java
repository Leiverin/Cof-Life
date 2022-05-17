package com.project.coffeeapp.forms;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.project.coffeeapp.models.User;

// Handling business logic
public class SignUpPresenter {

    private SignUpInterface signUpInterface;

    public SignUpPresenter(SignUpInterface signUpInterface) {
        this.signUpInterface = signUpInterface;
    }

    public void validateAccount(EditText edAccount){
        edAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {
                if(i == 0 && i2 == 0){
                    signUpInterface.validAccount();
                }else if(c.toString().trim().isEmpty() || c.toString().trim().length() <= 6){
                    signUpInterface.accountInvalid();
                }else{
                    signUpInterface.validAccount();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    signUpInterface.validAccount();
                }
            }
        });
    }

    public void validatePassword(EditText edPassword){
        edPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {
                if(i == 0 && i2 == 0){
                    signUpInterface.validPassword();
                }else if(c.toString().trim().isEmpty() || c.toString().trim().length() <= 6){
                    signUpInterface.passwordInvalid();
                }else{
                    signUpInterface.validPassword();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    signUpInterface.validPassword();
                }
            }
        });
    }

    public void validateConfirm(EditText edConfirm, TextInputLayout tilConfirm){
        edConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tilConfirm.setError(null);
            }
        });
    }

    public void signUp(User user, String pConfirm){
        if(user.getAccount().trim().isEmpty() || user.getAccount().length() <= 6){
            signUpInterface.accountInvalid();
        }else if(user.getPassword().trim().isEmpty() || user.getAccount().length() <= 6){
            signUpInterface.passwordInvalid();
        }else if(!pConfirm.equals(user.getPassword())){
            signUpInterface.confirmError();
        }else{
            signUpInterface.signUpSuccess(user);
        }
    }
}
