package com.project.coffeeapp.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.project.coffeeapp.HomeActivity;
import com.project.coffeeapp.databinding.ActivitySignInBinding;
import com.project.coffeeapp.models.User;
import com.project.coffeeapp.utils.CommonUtil;

public class SignIn extends AppCompatActivity {
    private SignInViewModel signInViewModel;
    private ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = binding.edAccount.getText().toString().trim();
                String password = binding.edPassword.getText().toString().trim();
                signInViewModel.callAPIUser(account, password);
            }
        });

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        signInViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){
                    Intent intent = new Intent(SignIn.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    CommonUtil.sCurrentUser = user;
                }else{
                    Toast.makeText(SignIn.this, "Account or password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}