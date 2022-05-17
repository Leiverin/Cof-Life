package com.project.coffeeapp.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    private ActivitySignInBinding binding;
    private FirebaseDatabase fd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fd = FirebaseDatabase.getInstance();
        DatabaseReference ref = fd.getReference("Users");

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = binding.edAccount.getText().toString().trim();
                String password = binding.edPassword.getText().toString().trim();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(snapshot.child(account).exists()){
                            User user = snapshot.child(account).getValue(User.class);
                            if(user.getPassword().equals(password)){
                                startActivity(new Intent(SignIn.this, HomeActivity.class));
                                CommonUtil.sCurrentUser = user;
                                finish();
                            }else{
                                Toast.makeText(SignIn.this, "Password is not correct", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignIn.this, "User not exist in database", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}