package com.project.coffeeapp.forms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.coffeeapp.databinding.ActivitySignUpBinding;
import com.project.coffeeapp.models.User;

public class SignUp extends AppCompatActivity implements SignUpInterface{
    private ActivitySignUpBinding binding;
    private FirebaseDatabase fd;
    private SignUpPresenter signUpPresenter;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fd = FirebaseDatabase.getInstance();
        ref = fd.getReference("Users");

        signUpPresenter = new SignUpPresenter(this);

        signUpPresenter.validateAccount(binding.edAccount);
        signUpPresenter.validatePassword(binding.edPassword);
        signUpPresenter.validateConfirm(binding.edConfirm, binding.tilConfirm);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = binding.edAccount.getText().toString().trim();
                String password = binding.edPassword.getText().toString().trim();
                String pConfirm = binding.edConfirm.getText().toString().trim();
                ProgressDialog mDialog = new ProgressDialog(getApplicationContext());
                mDialog.setMessage("Please waiting...");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(snapshot.child(account).exists() && !account.isEmpty()){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Account already exists", Toast.LENGTH_SHORT).show();
                        }else{
                            mDialog.dismiss();
                            signUpPresenter.signUp(new User(account, password), pConfirm);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
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
        binding.tilAccount.setError("Account cannot be not empty and must be larger than 6 characters");
    }

    @Override
    public void passwordInvalid() {
        binding.tilPassword.setError("Password cannot be not empty and must be larger than 6 characters");
    }

    @Override
    public void confirmError() {
        binding.tilConfirm.setError("Confirm password is not the same");
    }

    @Override
    public void validAccount() {
        binding.tilAccount.setError(null);
    }

    @Override
    public void validPassword() {
        binding.tilPassword.setError(null);
    }

    @Override
    public void signUpSuccess(User user) {
        ref.child(user.getAccount()).setValue(user);
        clearText();
        Toast.makeText(this, "Create account successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}