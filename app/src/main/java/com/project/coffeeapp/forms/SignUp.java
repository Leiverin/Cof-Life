package com.project.coffeeapp.forms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.project.coffeeapp.R;
import com.project.coffeeapp.databinding.ActivitySignUpBinding;
import com.project.coffeeapp.models.User;

public class SignUp extends AppCompatActivity implements SignUpInterface{
    private ActivitySignUpBinding binding;
    private SignUpPresenter signUpPresenter;
    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpViewModel =
                new ViewModelProvider(this).get(SignUpViewModel.class);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signUpPresenter = new SignUpPresenter(this);

        signUpPresenter.validateAccount(binding.edAccount);
        signUpPresenter.validatePassword(binding.edPassword);
        signUpPresenter.validateConfirm(binding.edConfirm, binding.tilConfirm);
        signUpPresenter.validateFullName(binding.edFullName, binding.tilFullName);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = binding.edAccount.getText().toString().trim();
                String password = binding.edPassword.getText().toString().trim();
                String pConfirm = binding.edConfirm.getText().toString().trim();
                String fullName = binding.edFullName.getText().toString().trim();
                signUpPresenter.signUp(new User(account, password, fullName), pConfirm, fullName);
            }
        });

        signUpViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){
                    clearText();
                    Toast.makeText(SignUp.this, "Create account successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, SignIn.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void clearText(){
        binding.edAccount.setText("");
        binding.edPassword.setText("");
        binding.edConfirm.setText("");
    }

    @Override
    public void accountInvalid() {
        binding.tilAccount.setHelperText("Account cannot be not empty and must be larger than 6 characters");
        binding.tilAccount.setHelperTextColor(ColorStateList.valueOf(Color.RED));
    }

    @Override
    public void passwordInvalid() {
        binding.tilPassword.setHelperText("Password cannot be not empty and must be larger than 6 characters");
        binding.tilPassword.setHelperTextColor(ColorStateList.valueOf(Color.RED));
    }

    @Override
    public void fullNameInvalid() {
        binding.tilFullName.setHelperText("Please enter full name");
        binding.tilFullName.setHelperTextColor(ColorStateList.valueOf(Color.RED));
    }

    @Override
    public void confirmError() {
        binding.tilConfirm.setHelperText("Confirm password is not the same");
        binding.tilConfirm.setHelperTextColor(ColorStateList.valueOf(Color.RED));
    }

    @Override
    public void validAccount() {
        binding.tilAccount.setHelperText(null);
    }

    @Override
    public void validPassword() {
        binding.tilPassword.setHelperText(null);
    }

    @Override
    public void validFullName() {
        binding.tilFullName.setHelperText(null);
    }

    @Override
    public void signUpSuccess(User user) {
        signUpViewModel.callAPICreateAccount(this, user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}